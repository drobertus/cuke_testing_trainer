package com.cuketest.reporting.cukeframework.steps;

import com.cuketest.reporting.cukeframework.fixtures.ReportGeneratorFixture;
import com.google.inject.Inject;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by David on 12/5/2014.
 */
public class BeforeAfterSteps {

    private ReportGeneratorFixture fixture;

    @Inject
    public BeforeAfterSteps(ReportGeneratorFixture fixture) {
        this.fixture = fixture;
    }

    @Before
    public void setup() {
        fixture.setup();
    }

    @After
    public void teardown() {
        fixture.teardown();
    }
}
