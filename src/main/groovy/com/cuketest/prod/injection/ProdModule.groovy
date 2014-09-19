package com.cuketest.prod.injection

import com.cuketest.prod.services.ReportService
import com.cuketest.prod.services.ReportServiceImpl
import com.cuketest.prod.services.UserService
import com.cuketest.prod.services.UserServiceImpl
import com.google.inject.AbstractModule


class ProdModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ReportService.class).to(ReportServiceImpl.class).asEagerSingleton()
        bind(UserService.class).to(UserServiceImpl.class).asEagerSingleton()

    }
}
