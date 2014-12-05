package com.cuketest.prod.inject

import com.cuketest.prod.DefaultReportGenerator
import com.cuketest.prod.ReportGenerator
import com.cuketest.prod.ReportGeneratorService
import com.google.inject.Singleton;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer
import groovy.util.logging.Log;

import static com.sun.jersey.api.core.PackagesResourceConfig.PROPERTY_PACKAGES;

@Log
public class ReportGenServletModule extends JerseyServletModule {


    @Override
    protected void configureServlets() {
        log.info("Configuring");

        bind(ReportGeneratorService.class);
        bind(ReportGenerator.class).to(DefaultReportGenerator.class).in(Singleton.class)

        Map<String, String> params = new HashMap<String, String>();
        params.put(PROPERTY_PACKAGES, 'com.cuketest.prod');

        serve("/*").with(GuiceContainer.class, params);
    }

}
