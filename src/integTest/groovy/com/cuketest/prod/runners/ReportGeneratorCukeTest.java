package com.cuketest.prod.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import java.util.logging.Logger;

@Ignore
@RunWith(Cucumber.class)
@CucumberOptions(strict=true,   features={"classpath:features/report_generator.feature"},
        glue={"com.cuketest.prod"} )
public class ReportGeneratorCukeTest {

    private static Logger log = Logger.getLogger(ReportGeneratorCukeTest.class.getName());

    public ReportGeneratorCukeTest() {

        log.info("Running " + this.getClass().getName());
    }
}
