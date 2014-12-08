package com.cuketest.prod.steps;

import com.cuketest.prod.fixtures.ClientFixture;
import com.cuketest.prod.steps.dto.ReportParameter;
import com.cuketest.prod.util.ParameterGenerator;
import com.google.inject.Inject;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 12/5/2014.
 */
public class ClientSteps {

    private ClientFixture clientFixture;

    @Inject
    public ClientSteps(ClientFixture fixture) {
        clientFixture = fixture;
    }

    @When("^\"(.*?)\" requests the \"(.*?)\" report be run with values$")
    public void requests_the_report_be_run_with_values(String user, String reportName, List<ReportParameter> params) throws Throwable {

        clientFixture.generateReport(user, reportName, params);

    }

    @Then("^the response should be a unique report id \"(.*?)\"$")
    public void the_response_should_be_a_unique_report_id(String internalReportRef) throws Throwable {
        clientFixture.assignLastResponseToReportId(internalReportRef);
    }

    @When("^\"(.*?)\" requests report \"(.*?)\"$")
    public void requests_report(String userName, String reportName) throws Throwable {
        clientFixture.requestReport(userName, reportName);
    }

    @Then("^the response will indicate it is \"(.*?)\"$")
    public void the_response_will_indicate_it_is(String expectedResponse) throws Throwable {
        assertEquals(expectedResponse, clientFixture.getLastResponse());
    }

    @Then("^the response will contain the report body$")
    public void the_response_will_contain_the_report_body() throws Throwable {
        assertEquals(ClientFixture.SAMPLE_REPORT_CONTENT, clientFixture.getLastResponse());
    }

    @Then("^the response message will be \"(.*?)\"$")
    public void the_response_message_will_be(String expectedResponse) throws Throwable {
        assertEquals(expectedResponse, clientFixture.getLastResponse());
    }
}
