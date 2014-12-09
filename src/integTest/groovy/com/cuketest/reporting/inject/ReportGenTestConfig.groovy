package com.cuketest.reporting.inject

import com.cuketest.reporting.service.DataService
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.servlet.GuiceServletContextListener
import com.google.inject.util.Modules


class ReportGenTestConfig extends GuiceServletContextListener {


    def testDataService

    private Injector injector

    ReportGenTestConfig(DataService testDataService){
        this.testDataService = testDataService
    }

    @Override
    protected Injector getInjector() {

        // use the same injector as production ReportGenConfig, but
        // then override the service module
        injector = Guice.createInjector(Modules.override(new ServicesModule(), new ReportGenServletModule())
            .with(new ServicesTestModule(testDataService)));

        return injector;
    }

}
