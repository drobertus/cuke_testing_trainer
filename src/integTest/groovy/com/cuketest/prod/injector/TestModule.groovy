package com.cuketest.prod.injector

import com.cuketest.prod.services.ReportService
import com.cuketest.prod.services.ReportServiceImpl
import com.cuketest.prod.services.TestUserService
import com.cuketest.prod.services.UserService
import com.cuketest.prod.services.UserServiceImpl
import com.google.inject.AbstractModule

/**
 * Created by drobertu on 9/19/14.
 */
class TestModule extends AbstractModule {

    def validUsers = []
    def
    TestModule(userList, validTests) {
        validUsers = userList
    }
    @Override
    protected void configure() {

        bind(ReportService.class).toInstance(new TestUserService(validUsers)).to(ReportServiceImpl.class).asEagerSingleton()
        bind(UserService.class).to(UserServiceImpl.class).asEagerSingleton()

    }
}