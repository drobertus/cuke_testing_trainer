package com.cuketest.prod

import com.cuketest.prod.injector.TestInjectionModule
import com.cuketest.prod.services.TestDef
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import net.sf.json.JSON
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

        setup:
            def reportId= '1265'

        when:
            def url = "http://localhost:9105/reports/show/${reportId}".toURL().getText()

        then:
            assertEquals 'ta-da!! Here is the report named ' + reportId, url
    }


    def "test the ability to request a report from a valid user"() {
        when:

        def url = "http://localhost:9105/reports"
        //def path = "/generateRpt/${testUserId}/${testName1}/${test1ParamsNames}"

        def formattedParams = ""
        test1ParamsNames.each {
            formattedParams = formattedParams + it + '_'
        }

        def path = "/generateRpt/${testUserId}/${URLEncoder.encode(testName1, 'UTF-8')}/${formattedParams}" //, 'UTF-8')}"
        println "thePath= ${path}"

        HttpClient client = new DefaultHttpClient();

        def thePost = new HttpPost(url + path)

        def response = client.execute(thePost)

        def responseContent = response.getEntity().getContent().getText()
        println responseContent

        then:
            assertNotNull responseContent
            assertFalse responseContent.empty
            assertEquals 11, responseContent.length()
    }


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
        ReportGenerator.injectorFactory = null
    }


}
