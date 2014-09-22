package com.cuketest.prod.services


class TestReportService implements ReportService {

    def reportMap = [:]

    TestReportService(reports){
        reportMap = reports
    }

    @Override
    boolean isValidReport(String reportType, List<String> params) {
        return true
    }
}
