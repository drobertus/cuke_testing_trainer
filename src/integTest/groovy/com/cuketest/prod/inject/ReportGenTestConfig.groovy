package com.cuketest.prod.inject

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.servlet.GuiceServletContextListener

/**
 * Created by drobertu on 12/3/14.
 */
class ReportGenTestConfig extends GuiceServletContextListener {


    List<String> userList
    Map<String, List<String>> reports

    private Injector injector

    ReportGenTestConfig(List<String> users, Map<String, List<String>> reportList){
        userList = users
        reports = reportList
    }

    @Override
    protected Injector getInjector() {

        injector = Guice.createInjector(new ServicesTestModule(userList, reports), new ReportGenServletTestModule());
        return injector;
    }

}
