package com.cuketest.prod

import com.cuketest.prod.injection.ProdModule
import com.cuketest.prod.injector.TestInjectionModule
import com.google.inject.Guice
import com.google.inject.Injector
import spock.lang.Specification

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer

import static org.junit.Assert.assertEquals


class ReportGenIntegTest extends Specification {

    def jetty

    def testUserId = '514Jks'


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
        def url = "http://localhost:9105/reports/show/${reportId}".toURL().getText()
        then:
        assertEquals 'ta-da!! Here is the report named ' + reportId, url
    }


    void setup() {

        def validUserList = []
        validUserList << testUserId


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
