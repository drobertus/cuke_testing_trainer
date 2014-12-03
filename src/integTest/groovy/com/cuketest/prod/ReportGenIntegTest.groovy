package com.cuketest.prod

import com.cuketest.prod.inject.ReportGenConfig
import com.cuketest.prod.inject.ReportGenTestConfig
import com.google.inject.servlet.GuiceFilter

//import com.cuketest.prod.inject.TestInjectionModule
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import org.eclipse.jetty.servlet.FilterHolder
import org.eclipse.jetty.webapp.WebAppContext
import spock.lang.Specification

import org.eclipse.jetty.server.Server

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNotNull


class ReportGenIntegTest extends Specification {

    def jetty

    def testUserId = '514Jks'
    def testName1 = 'Usage_Data'
    def test1ParamsNames = ['startDate','periodInDays']

    def testConfig

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

            assertEquals ReportGeneratorService.INVALID_USER_ERROR, responseContent
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

        def validTestSets = [:]

        validTestSets << [testName1: test1ParamsNames]


        jetty = new Server(9105)

        WebAppContext context = new WebAppContext();

        context.setContextPath("/");


        //TODO: Build a test framework that provides a test DataProvider but uses the real User and Report service classes
       // testConfig = new ReportGenConfig()
        testConfig = new ReportGenTestConfig(validUserList, validTestSets);


        context.addEventListener(testConfig);

        FilterHolder guiceHolder = new FilterHolder();
        guiceHolder.setFilter(new GuiceFilter());
        guiceHolder.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        context.addFilter(guiceHolder, "/*", null);

        context.setResourceBase("./src/main/groovy");
        context.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");


        jetty.setHandler(context);


        jetty.start();
    }

    void cleanup() {
        jetty.stop()
    }


}
