package com.cuketest.reporting.service.testImpl

import com.cuketest.reporting.dto.Report
import com.cuketest.reporting.dto.ReportDefinition
import com.cuketest.reporting.service.DataService


class TestDataService implements DataService {

    List<String> validUsers = []
    Map<String, ReportDefinition> recognizedReports = [:]
    Map<String, Report> completedReports = [:]

    @Override
    ReportDefinition getReportDefinition(String reportTypeName) {
        return recognizedReports.get(reportTypeName)
    }

    @Override
    boolean userInSystem(String userId){
        return validUsers.contains(userId)
    }

    @Override
    void saveReport(Report report) {
        completedReports.put(report.id, report)
    }

    @Override
    Report getReportById(String reportId) {
        return completedReports.get(reportId)
    }
}
