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
    final String reportName = 'Usage Data'
    final List<String> rptParams = ['startDate', 'range']


    @Test
    public void testShowAReport() {

        final showVal = 'This_is_a_test'
        def result = reportGen.show(showVal)
        assertEquals 'ta-da!! Here is the report named ' + showVal, result
    }


    @Test
    public void test_request_report_creation_success() {

        def result = reportGen.generateReport(userId, reportName, rptParams.toString())
        assertFalse ReportGeneratorService.INVALID_USER_ERROR == result
        assertFalse ReportGeneratorService.INVALID_REPORT_REQUEST == result
        assertNotNull result
        assertFalse result.isEmpty()
        assertEquals 11, result.length()

    }

    @Test
    public void invalid_user_requests_report() {
        def invalidUserId = 'sdfsdf'
        def result = reportGen.generateReport(invalidUserId, reportName, rptParams.toString())
        assertEquals( ReportGeneratorService.INVALID_USER_ERROR, result)

    }


    @Before
    public void setup() {

        def goodList = [userId]
        def userService = new TestUserService(goodList)
        def rptMap = [:]
        rptMap.put(this.reportName, rptParams)
        def reportService = new TestReportService(rptMap)
        //notice we bypass the inject process for a unit test (or can, anyway)
        reportGen = new ReportGeneratorService(userService, reportService)
    }
}
