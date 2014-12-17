package com.cuketest.reporting.cukeframework.fixtures

import com.cuketest.reporting.dto.ReportDefinition
import com.cuketest.reporting.inject.ReportGenTestConfig
import com.cuketest.reporting.service.testImpl.TestDataService
import com.google.inject.Singleton
import com.google.inject.servlet.GuiceFilter
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.FilterHolder
import org.eclipse.jetty.webapp.WebAppContext

@Singleton
class ReportGeneratorFixture {

    private Server jetty
    private TestDataService testDataService


    void setValidUsers(List<String> validUsers) {
        this.testDataService.setValidUsers(validUsers)
    }

    void addValidReports(List<ReportDefinition> validReports) {
        validReports.each { aRpt ->
            this.testDataService.recognizedReports.put(aRpt.name, aRpt)
        }
    }

    public void setup() {

        jetty = new Server(9105)

        WebAppContext context = new WebAppContext();

        context.setContextPath("/");


        //TODO: Build a test framework that provides a test DataProvider but uses the real User and Report service classes
        testDataService = new TestDataService()

        context.addEventListener(new ReportGenTestConfig(testDataService))

        FilterHolder guiceHolder = new FilterHolder();
        guiceHolder.setFilter(new GuiceFilter());
        guiceHolder.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        context.addFilter(guiceHolder, "/*", null);

        context.setResourceBase("./src/main/groovy");
        context.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        jetty.setHandler(context);

        jetty.start();
    }

    public void teardown() {
        jetty.stop()
    }


}
