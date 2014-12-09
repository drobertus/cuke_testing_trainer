package com.cuketest.reporting.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;


public class ReportGenConfig  extends GuiceServletContextListener {

    protected Injector injector;

    @Override
    protected Injector getInjector() {
        injector = Guice.createInjector(new ServicesModule(), new ReportGenServletModule());
        return injector;
    }
}
