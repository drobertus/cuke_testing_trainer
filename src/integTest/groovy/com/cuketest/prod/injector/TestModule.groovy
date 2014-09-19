package com.cuketest.prod.injector

import com.cuketest.prod.services.ReportService
import com.cuketest.prod.services.ReportServiceImpl
import com.cuketest.prod.services.TestDef
import com.cuketest.prod.services.TestReportService
import com.cuketest.prod.services.TestUserService
import com.cuketest.prod.services.UserService
import com.cuketest.prod.services.UserServiceImpl
import com.google.inject.AbstractModule

/**
 * Created by drobertu on 9/19/14.
 */
class TestModule extends AbstractModule {

    def validUsers = []
    def validReports = [:]

    TestModule(userList, List<TestDef> reports) {
        validUsers = userList

        for(TestDef td : reports) {
            validReports.put(td.name, td.paramNames)
        }
    }
    @Override
    protected void configure() {

        bind(UserService.class).toInstance(new TestUserService(validUsers))
        bind(ReportService.class).toInstance(new TestReportService(validReports))

    }
}