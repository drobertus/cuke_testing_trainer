package com.cuketest.prod

import com.cuketest.prod.injector.TestInjectionModule
import com.cuketest.prod.services.TestDef
import spock.lang.Specification

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer

import static org.junit.Assert.assertEquals


class ReportGenIntegTest extends Specification {

    def jetty

    def testUserId = '514Jks'
    def testName1 = 'Usage Data'
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

        def http = new HTTPBuilder( 'http://restmirror.appspot.com/' )
        def postBody = [name: 'bob', title: 'construction worker'] // will be url-encoded

        http.post( path: '/', body: postBody,
            requestContentType: URLENC ) { resp ->

            println "POST Success: ${resp.statusLine}"
            assert resp.statusLine.statusCode == 201
        }
            def url = "http://localhost:9105/reports/generateRpt/${testUserId}/${testName1}/${test1ParamsNames}".toURL().getText()

        then:
            assertEquals 'ta-da!! Here is the report named ' + reportId, url
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
