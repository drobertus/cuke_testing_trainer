package com.cuketest.reporting.steps;

import com.cuketest.reporting.fixtures.ClientFixture;
import com.google.inject.Inject;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class ClientSteps {

    private ClientFixture clientFixture;

    @Inject
    public ClientSteps(ClientFixture fixture) {
        clientFixture = fixture;
    }

//    @When("^\"(.*?)\" requests the \"(.*?)\" report to be run with values$")
//    public void requests_the_report_be_run_with_values(String user, String reportName, DataTable arg3) throws Throwable {
//        throw new PendingException();
//    }
//
//    @Then("^the response should be a unique report id \"(.*?)\"$")
//    public void the_response_should_be_a_unique_report_id(String internalReportRef) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @When("^\"(.*?)\" requests report \"(.*?)\"$")
//    public void requests_report(String userName, String reportName) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Then("^the response will indicate it is \"(.*?)\"$")
//    public void the_response_will_indicate_it_is(String expectedResponse) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Then("^the response will contain the report body$")
//    public void the_response_will_contain_the_report_body() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Given("^that report \"(.*?)\" has been requested by user \"(.*?)\"$")
//    public void that_report_has_been_requested_by_user(String reportName, String arg2) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Then("^the response message will be \"(.*?)\"$")
//    public void the_response_message_will_be(String arg1) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
}
