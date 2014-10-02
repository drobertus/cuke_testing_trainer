package com.cuketest.prod

import com.cuketest.prod.injector.TestInjectionModule
import com.cuketest.prod.services.TestDef
import com.google.inject.Injector
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import spock.lang.Specification

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNotNull


class ReportGenIntegTest extends Specification {

    def jetty

    def testUserId = '514Jks'
    def testName1 = 'Usage_Data'
    def test1ParamsNames = ['startDate','periodInDays']


    def "test output of show"() {

        setup: "given the ID of a known report that has been generated"
            def reportId= '1265'

        when: "that report is requested"
            def url = "http://localhost:9105/reports/show/${reportId}".toURL().getText()

        then: "we should get back the report"
            assertEquals 'ta-da!! Here is the report named ' + reportId, url
    }


    def "a user gets back a report ID when he requests a valid report"() {

        setup:
            def url = "http://localhost:9105/reports"

            def formattedParams = ""
            test1ParamsNames.each {
                formattedParams = formattedParams + it + '_'
            }

            def path = "/generateRpt/${testUserId}/${URLEncoder.encode(testName1, 'UTF-8')}/${formattedParams}" //, 'UTF-8')}"
            println "thePath= ${path}"

            HttpClient client = new DefaultHttpClient();

        when: "a valid user makes a request for a type of report he is allowed to create"

            def thePost = new HttpPost(url + path)
            def response = client.execute(thePost)

        then: "the ID for that report should be returned to the user"
            def responseContent = response.getEntity().getContent().getText()
            println responseContent

            assertNotNull responseContent
            assertFalse responseContent.empty
            assertEquals 11, responseContent.length()
    }


    def "an invalid user requests a report he should receive a warning message"() {

        setup:
            def invalidUserId= 'Bob-Smith'
            def url = "http://localhost:9105/reports"

            def formattedParams = ""
            test1ParamsNames.each {
                formattedParams = formattedParams + it + '_'
            }

            def path = "/generateRpt/${invalidUserId}/${URLEncoder.encode(testName1, 'UTF-8')}/${formattedParams}" //, 'UTF-8')}"
            println "thePath= ${path}"

            HttpClient client = new DefaultHttpClient();

        when: "an invalid user makes a report request"

            def thePost = new HttpPost(url + path)
            def response = client.execute(thePost)

        then: "an error message should be returned"
            def responseContent = response.getEntity().getContent().getText()
            println responseContent

            assertEquals ReportGenerator.INVALID_USER_ERROR, responseContent
    }

    /**
     * The setup for an integration test requires running components in their "natural environments"
     * In this case we need to run a REST service in a web container, but should not be
     * tied to a remote resource- have the test framework internalize as many aspects and dependencies
     * of a deployed product as possible.
     */
    void setup() {

        def validUserList = []
        validUserList << testUserId

        def validTestSets = []

        def aTest = new TestDef(name: testName1, paramNames: test1ParamsNames)
        validTestSets << aTest

        ReportGenerator.injectorFactory = new TestInjectionModule(validUserList, validTestSets)

        jetty = new Server(9105)
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
        sh.setInitParameter("com.sun.jersey.config.property.packages", "com.cuketest.prod");//Set the package where the services reside
        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        ServletContextHandler context = new ServletContextHandler(jetty, "/", ServletContextHandler.SESSIONS);
        context.addServlet(sh, "/*");
        jetty.start();
    }

    void cleanup() {
        jetty.stop()
        //because this is static, clean it up!
        ReportGenerator.injectorFactory = null
    }


}
