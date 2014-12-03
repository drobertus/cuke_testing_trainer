package com.cuketest.prod.inject

import com.cuketest.prod.DefaultReportGenerator
import com.cuketest.prod.ReportGenerator
import com.cuketest.prod.ReportGeneratorService
import com.cuketest.prod.services.ReportService
import com.cuketest.prod.services.ReportServiceImpl
import com.cuketest.prod.services.UserService
import com.cuketest.prod.services.UserServiceImpl
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
        params.put(PROPERTY_PACKAGES, 'com.cuketest.prod');

        serve("/*").with(GuiceContainer.class, params);
        log.info('servlet config complete')
    }
}
