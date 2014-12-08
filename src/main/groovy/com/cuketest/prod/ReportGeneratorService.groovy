package com.cuketest.prod

import com.cuketest.prod.dto.Report
import com.cuketest.prod.dto.ReportStatus
import com.cuketest.prod.service.ReportService
import com.cuketest.prod.service.UserService
import com.google.inject.Inject
import groovy.util.logging.Log

@Log
public class ReportGeneratorService {

    private UserService userService
    private ReportService reportService

    public static final String INVALID_USER_ERROR = 'Invalid user'
    public static final String INVALID_REPORT_REQUEST = 'Invalid report request'
    public static final String INVALID_REPORT_ID_ERROR = 'Report Id was not found'
    public static final String WRONG_USER_REQUESTING_REPORT = 'You do not have access to this report'

    @Inject
    public ReportGeneratorService(UserService us, ReportService rs) {
        //we need to use an empty constructor since we don't
        //control the instantiation
        log.info('injecting user and report services')
        this.userService = us
        this.reportService = rs
    }

    /**
     * Show a report that was generated and given a unique id
     * @param reportId
     * @return
     */
    String getReport(final String userId, final String reportId) {

        if (!userService.isValidUser(userId)) {
            return INVALID_USER_ERROR
        }

        Report report = reportService.getReport(reportId)
        if(!report) {
            return INVALID_REPORT_ID_ERROR
        }

        if(!userId.equals(report.userEmail)){
            return WRONG_USER_REQUESTING_REPORT
        }
        if (ReportStatus.Complete.equals(report.status)) {
            return report.theReport
        }
        return report.status
    }


    String generateReport(String userId,
                          String reportType,
                          Map<String,String> params) {
        println("reportType=${reportType}, params found in service=" + params)
        if(!userService.isValidUser(userId)) {
            return INVALID_USER_ERROR
        }

        if (!reportService.isValidReport(reportType, params.keySet())) {
            return INVALID_REPORT_REQUEST
        }

        return reportService.createReport(userId, reportType, params)

    }


}
