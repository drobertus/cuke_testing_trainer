package com.cuketest.reporting.cukeframework.steps;

import com.cuketest.reporting.dto.ReportDefinition;
import com.cuketest.reporting.dto.ReportStatus;
import com.cuketest.reporting.cukeframework.fixtures.ReportGeneratorFixture;
import com.cuketest.reporting.cukeframework.steps.dto.ReportDef;
import com.google.inject.Inject;
import cucumber.api.Delimiter;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReportGeneratorSteps {

    private ReportGeneratorFixture serverFixture;

    @Inject
    public ReportGeneratorSteps(ReportGeneratorFixture serverFixture) {
        this.serverFixture = serverFixture;
    }

    @Given("^these valid users \"(.*?)\"$")
    public void these_valid_users(@Delimiter(", ")List<String> validUsers) throws Throwable {
        serverFixture.setValidUsers(validUsers);
    }

    @Given("^these valid reports$")
    public void these_valid_reports(List<ReportDef> validReports) throws Throwable {
        List<ReportDefinition> valReports = new ArrayList<ReportDefinition>();
        for(ReportDef rd : validReports) {
            valReports.add(rd.toReportDefinition());
        }

        serverFixture.addValidReports(valReports);
    }

    @Given("^the default settings$")
    public void the_default_settings() throws Throwable {
        // is there anything to do here?  Should we remove this step?
    }



    @Given("^the Report Processor will set the state of report \"(.*?)\" to \"(.*?)\"$")
    public void the_server_will_set_the_state_of_report_to(String testRptId, ReportStatus newStatus) throws Throwable {
        serverFixture.setStatusOfReport(testRptId, newStatus);

    }

    @Then("^the status of report \"(.*?)\" should be \"(.*?)\"$")
    public void the_status_of_the_report_should_be(String reportId, ReportStatus expectedStatus) throws Throwable {
        ReportStatus theRptStatus = this.serverFixture.getStatusOfReport(reportId);
        assertEquals (expectedStatus, theRptStatus);
    }

    @Given("^the Report Processor completes report \"(.*?)\"$")
    public void creates_a_dummy_report(String testName) throws Throwable {


        serverFixture.completeReport(testName);
    }




}
