package com.cuketest.reporting

import com.cuketest.reporting.dto.Report
import com.cuketest.reporting.dto.ReportDefinition
import com.cuketest.reporting.dto.ReportStatus
import com.cuketest.reporting.service.TestReportService
import com.cuketest.reporting.service.TestUserService
import org.junit.Before
import org.junit.Test
import spock.lang.Specification

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNotNull


class ReportGeneratorServiceTest extends Specification {

    ReportGeneratorService reportGen

    final String userId = 'billTester@test.org'
    final String reportName = 'Usage Data'
    final List<String> rptParamsKey = ['startDate', 'range']
    final Map<String,String> rptParams = ['startDate':'20100101', 'range':'365']
    TestReportService reportService

    void "test Show Completed Report"() {
        setup:
        final showVal = 'This_is_a_test'
        final expectedMsg = 'ta-da!! Here is the report'
        reportService.existingReports.put(showVal,
            new Report(theReport: expectedMsg, userEmail: userId,
            status: ReportStatus.Complete))

        when:
        def result = reportGen.getReport(userId, showVal)

        then:
        assertEquals expectedMsg, result
    }


    @Test
    void "test request report creation success"() {

        when:
        def result = reportGen.generateReport(userId, reportName, rptParams)

        then:
        assertFalse ReportGeneratorService.INVALID_USER_ERROR == result
        assertFalse ReportGeneratorService.INVALID_REPORT_REQUEST == result
        assertNotNull result
        assertFalse result.isEmpty()
        assertEquals 11, result.length()

    }

    void "test invalid user requests report"() {
        expect:

        def invalidUserId = 'sdfsdf'
        def result = reportGen.generateReport(invalidUserId, reportName, rptParams)
        assertEquals( ReportGeneratorService.INVALID_USER_ERROR, result)

    }


    @Before
    public void setup() {

        def goodList = [userId]
        def userService = new TestUserService(goodList)
        def rptMap = [:]
        def rptDef = new ReportDefinition(name: reportName, parameters: rptParamsKey)
        rptMap.put(rptDef.name, rptDef)
        reportService = new TestReportService(rptMap)

        reportGen = new ReportGeneratorService(userService, reportService)
//        TODO: which would use less test code- mocks or working stubs?
//        def mockUserService = Mock(UserService)
//        def mockReportService = Mock(ReportService)
//
//        //notice we bypass the inject process for a unit test (or can, anyway)
//        reportGen = new ReportGeneratorService(mockUserService, mockReportService)
    }

}
