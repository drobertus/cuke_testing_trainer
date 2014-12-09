package com.cuketest.reporting.service.impl

import com.cuketest.reporting.service.DataService
import com.cuketest.reporting.service.UserService
import com.google.inject.Inject

class DefaultUserService implements UserService {

    DataService dataService

    @Inject
    public DefaultUserService(DataService ds){
        dataService = ds;
    }

    @Override
    boolean isValidUser(String userId) {
        dataService.userInSystem(userId)
    }
}
