package com.cuketest.prod.services


class TestReportService implements ReportService {
    @Override
    boolean isValidReport(String reportType, String params) {
        return true
    }
}
