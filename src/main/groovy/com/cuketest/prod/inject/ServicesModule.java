package com.cuketest.prod.inject;

import com.cuketest.prod.service.DataService;
import com.cuketest.prod.service.ReportService;
import com.cuketest.prod.service.impl.DefaultDataService;
import com.cuketest.prod.service.impl.DefaultReportService;
import com.cuketest.prod.service.UserService;
import com.cuketest.prod.service.impl.DefaultUserService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;


public class ServicesModule extends AbstractModule{

    @Override
    protected void configure() {

        bind(DataService.class).to(DefaultDataService.class).in(Singleton.class);
        bind(ReportService.class).to(DefaultReportService.class).in(Singleton.class);
        bind(UserService.class).to(DefaultUserService.class).in(Singleton.class);

    }
}
