package com.cuketest.prod.service.impl

import com.cuketest.prod.service.DataService
import com.cuketest.prod.service.UserService
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
