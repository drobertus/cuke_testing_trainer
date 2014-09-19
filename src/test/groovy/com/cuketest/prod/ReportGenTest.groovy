package com.cuketest.prod

import com.cuketest.prod.services.TestReportService
import com.cuketest.prod.services.TestUserService
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNotNull


class ReportGenTest {

    def reportGen

    final String userId = 'billTester@test.org'

    @Before
    public void setup() {

        def goodList = [userId]
        def userService = new TestUserService(goodList)
        def reportService = new TestReportService()

        reportGen = new ReportGenerator(userService, reportService)
    }

    @Test
    public void testShowAReport() {

        final showVal = 'This_is_a_test'
        def result = reportGen.show(showVal)
        assertEquals 'ta-da!! Here is the report named ' + showVal, result
    }


    @Test
    public void test_request_report_creation_success() {

        final reportType = 'basicReport'
        final parameters = '[param1=12,param2=bob,param3=Feb.12.1973]'
        def result = reportGen.generateReport(userId, reportType, parameters)
        assertFalse ReportGenerator.INVALID_USER_ERROR == result
        assertFalse ReportGenerator.INVALID_REPORT_REQUEST == result
        assertNotNull result
        assertFalse result.isEmpty()

    }
}
