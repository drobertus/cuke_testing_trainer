package com.cuketest.prod.steps;

import com.cuketest.prod.dto.ReportDefinition;
import com.cuketest.prod.fixtures.ReportGeneratorFixture;
import com.google.inject.Inject;
import cucumber.api.DataTable;
import cucumber.api.Delimiter;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;


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
    public void these_valid_reports(List<ReportDefinition> validReports) throws Throwable {
        serverFixture.addValidReports(validReports);
    }

    @Given("^the default settings$")
    public void the_default_settings() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }



    @Given("^the server will set the state of report \"(.*?)\" to \"(.*?)\"$")
    public void the_server_will_set_the_state_of_report_to(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    @Given("^creates a dummy report$")
    public void creates_a_dummy_report() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }




}
