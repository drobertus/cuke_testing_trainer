package com.cuketest.reporting.inject

import com.cuketest.reporting.DefaultReportGenerator
import com.cuketest.reporting.ReportGenerator
import com.cuketest.reporting.ReportGeneratorService
import com.google.inject.Singleton
import com.sun.jersey.guice.JerseyServletModule
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer
import groovy.util.logging.Log

import static com.sun.jersey.api.core.PackagesResourceConfig.PROPERTY_PACKAGES

@Log
class ReportGenServletTestModule extends JerseyServletModule {



    @Override
    protected void configureServlets() {
        log.info("Mock Configuring");

        bind(ReportGeneratorService.class);
        bind(ReportGenerator.class).to(DefaultReportGenerator.class).in(Singleton.class)

        Map<String, String> params = new HashMap<String, String>();
        params.put(PROPERTY_PACKAGES, 'com.cuketest.reporting');

        serve("/*").with(GuiceContainer.class, params);
        log.info('servlet config complete')
    }
}
