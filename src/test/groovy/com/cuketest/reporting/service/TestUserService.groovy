package com.cuketest.reporting.service

import groovy.util.logging.Log

@Log
class TestUserService implements UserService {

    def validUsers = []

    TestUserService(List<String> usersList) {
        log.info ("test user service with ${usersList.size()} users")
        validUsers.addAll(usersList)
    }

    @Override
    boolean isValidUser(String userId) {
        return validUsers.contains(userId)
    }
}
