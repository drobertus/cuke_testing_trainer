package com.cuketest.prod.inject

import com.cuketest.prod.service.DataService
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.servlet.GuiceServletContextListener

/**
 * Created by drobertu on 12/3/14.
 */
class ReportGenTestConfig extends GuiceServletContextListener {


    def testDataService

    private Injector injector

    ReportGenTestConfig(DataService testDataService){
        this.testDataService = testDataService
    }

    @Override
    protected Injector getInjector() {

        injector = Guice.createInjector(new ServicesTestModule(testDataService), new ReportGenServletTestModule());
        return injector;
    }

}
