package com.cuketest.reporting.steps;

import com.cuketest.reporting.dto.ReportDefinition;
import com.cuketest.reporting.dto.ReportStatus;
import com.cuketest.reporting.fixtures.ReportGeneratorFixture;
import com.google.inject.Inject;
import cucumber.api.Delimiter;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.List;


public class ReportGeneratorSteps {

    private ReportGeneratorFixture serverFixture;

    @Inject
    public ReportGeneratorSteps(ReportGeneratorFixture serverFixture) {
        this.serverFixture = serverFixture;
    }

//    @Given("^these valid users \"(.*?)\"$")
//    public void these_valid_users(@Delimiter(", ")List<String> validUsers) throws Throwable {
//        serverFixture.setValidUsers(validUsers);
//    }
//
//    @Given("^these valid reports$")
//    public void these_valid_reports(List<ReportDefinition> validReports) throws Throwable {
//        serverFixture.addValidReports(validReports);
//    }
//
//    @Given("^the default settings$")
//    public void the_default_settings() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Given("^the Report Processor will set the state of report \"(.*?)\" to \"(.*?)\"$")
//    public void the_server_will_set_the_state_of_report_to(String arg1, String arg2) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Then("^the status of report \"(.*?)\" should be \"(.*?)\"$")
//    public void the_status_of_the_report_should_be(String reportId, ReportStatus expectedStatus) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Given("^the Report Processor completes report \"(.*?)\"$")
//    public void creates_a_dummy_report() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//



}
