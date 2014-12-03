package com.cuketest.prod.inject;

//import com.cuketest.prod.services.DataProvider;
//import com.cuketest.prod.services.DefaultDataProvider;
import com.cuketest.prod.services.ReportService;
import com.cuketest.prod.services.ReportServiceImpl;
import com.cuketest.prod.services.UserService;
import com.cuketest.prod.services.UserServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Created by drobertu on 12/3/14.
 */
public class ServicesModule extends AbstractModule{

    @Override
    protected void configure() {

       // bind(DataProvider.class).to(DefaultDataProvider.class).in(Singleton.class);
        bind(ReportService.class).to(ReportServiceImpl.class).in(Singleton.class);
        bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);

    }
}
