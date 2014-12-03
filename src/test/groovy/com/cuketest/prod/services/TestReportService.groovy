package com.cuketest.prod.services

import groovy.util.logging.Log

@Log
class TestReportService implements ReportService {

    def reportMap = [:]

    TestReportService(reports){
        log.info 'test report service created'
        reportMap = reports
    }

    @Override
    boolean isValidReport(String reportType, List<String> params) {
        return true
    }
}
