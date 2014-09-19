package com.cuketest.prod

import com.sun.jersey.spi.container.servlet.ServletContainer
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.ContextHandler
import spock.lang.Specification

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue;

/**
 * Created by David on 9/17/2014.
 */
class ReportGenIntegTest extends Specification {

    def jetty

    def "test output of query"() {

        when:

            def url = "http://localhost:9105/reportGenerator/show".toURL().getText()
        then:
            assertEquals 'ta-da service!!', url
    }


    void setup() {

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
    }
}
