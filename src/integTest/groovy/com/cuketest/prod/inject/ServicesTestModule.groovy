package com.cuketest.prod.inject

import com.cuketest.prod.service.DataService

import com.cuketest.prod.service.ReportService
import com.cuketest.prod.service.UserService
import com.cuketest.prod.service.impl.DefaultReportService
import com.cuketest.prod.service.impl.DefaultUserService
import com.google.inject.AbstractModule
import com.google.inject.Singleton

/**
 * Created by drobertu on 12/3/14.
 */
public class ServicesTestModule extends AbstractModule {

    def testDataService

    ServicesTestModule(DataService ds) {
        this.testDataService = ds
    }

    @Override
    public void configure() {

        bind(DataService.class).toInstance(testDataService)
        bind(ReportService.class).to(DefaultReportService.class).in(Singleton.class)
        bind(UserService.class).to(DefaultUserService.class).in(Singleton.class)

    }
}
