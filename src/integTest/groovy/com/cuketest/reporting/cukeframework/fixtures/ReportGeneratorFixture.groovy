package com.cuketest.reporting.cukeframework.fixtures

import com.cuketest.reporting.dto.ReportStatus
import com.google.inject.Singleton
import com.cuketest.reporting.dto.ReportDefinition
import com.cuketest.reporting.inject.ReportGenTestConfig
import com.cuketest.reporting.service.testImpl.TestDataService
import com.google.inject.servlet.GuiceFilter
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.FilterHolder
import org.eclipse.jetty.webapp.WebAppContext

@Singleton
class ReportGeneratorFixture {

    private Server jetty
    private TestDataService testDataService
    public static int serverPort = 9108

    TestReportMapping reportState = TestReportMapping.instance

    void setValidUsers(List<String> validUsers) {
        this.testDataService.setValidUsers(validUsers)
    }

    void addValidReports(List<ReportDefinition> validReports) {
        validReports.each { aRpt ->
            this.testDataService.recognizedReports.put(aRpt.name, aRpt)
        }
    }

    public void setup() {

        jetty = new Server(serverPort)

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


    ReportStatus getStatusOfReport(String internalReportId) {
        def reportId = this.reportState.responseReportMap.get(internalReportId)
        //println('now setting report status for ' + reportId + ' to ' + newStatus)
        return testDataService.getReportById(reportId).status //= newStatus

    }

    /**
     * This mocks the behavior of the Report Processor System acting on the report
     * @param testRptId
     * @param newStatus
     */
    void setStatusOfReport(String testRptId, ReportStatus newStatus) {
        def internalRptId = this.reportState.getReportIdFromTestId(testRptId)
        testDataService.getReportById(internalRptId).status = newStatus
    }

    void completeReport(String testReportId) {
        def internalRptId = this.reportState.getReportIdFromTestId(testReportId)
        def rpt = testDataService.getReportById(internalRptId)
        rpt.status = ReportStatus.Complete
        rpt.theReport = ClientFixture.SAMPLE_REPORT_CONTENT

    }
}
