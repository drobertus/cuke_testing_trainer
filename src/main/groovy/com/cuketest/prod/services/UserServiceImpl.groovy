package com.cuketest.prod.services

import com.google.inject.Inject

/**
 * Created by drobertu on 9/19/14.
 */
class UserServiceImpl implements UserService {

//    DataProvider dataProvider;
//
//    @Inject
//    public UserServiceImpl(DataProvider dataProvider) {
//        this.dataProvider = dataProvider
//    }


    @Override
    boolean isValidUser(String userId) {
        return false
    }
}
