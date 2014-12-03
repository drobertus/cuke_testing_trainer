package com.cuketest.prod.inject

//import com.cuketest.prod.services.DataProvider
import com.cuketest.prod.services.ReportService
//import com.cuketest.prod.services.TestDataProvider
import com.cuketest.prod.services.TestReportService
import com.cuketest.prod.services.TestUserService
import com.cuketest.prod.services.UserService
import com.google.inject.AbstractModule;

import java.util.List;
import java.util.Map;

/**
 * Created by drobertu on 12/3/14.
 */
public class ServicesTestModule extends AbstractModule {

    List<String> userList = []
    Map<String, List<String>> reportMap = [:]



    ServicesTestModule(List<String> userList, Map<String, List<String>> reportMap) {
        this.userList = userList
        this.reportMap = reportMap
    }

    @Override
    public void configure() {

       // bind(DataProvider.class).toInstance(new TestDataProvider())
        bind(ReportService.class).toInstance(new TestReportService(reportMap))
        bind(UserService.class).toInstance(new TestUserService(userList))


    }
}
