package com.cuketest.prod.services

import spock.util.mop.Use

/**
 * Created by drobertu on 9/19/14.
 */
class TestUserService implements UserService {

    def validUsers = []

    TestUserService(usersList) {
        validUsers.addAll(usersList)
    }

    @Override
    boolean isValidUser(String userId) {
        return validUsers.contains(userId)
    }
}
