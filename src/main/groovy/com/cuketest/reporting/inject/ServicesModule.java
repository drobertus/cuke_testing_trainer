package com.cuketest.reporting.inject;

import com.cuketest.reporting.service.DataService;
import com.cuketest.reporting.service.ReportService;
import com.cuketest.reporting.service.impl.DefaultDataService;
import com.cuketest.reporting.service.impl.DefaultReportService;
import com.cuketest.reporting.service.UserService;
import com.cuketest.reporting.service.impl.DefaultUserService;
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
