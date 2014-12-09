package com.cuketest.reporting.service.impl

import com.cuketest.reporting.dto.Report
import com.cuketest.reporting.dto.ReportDefinition
import com.cuketest.reporting.service.DataService

class DefaultDataService implements DataService {

    @Override
    ReportDefinition getReportDefinition(String reportTypeName) {
        return null
    }

    @Override
    boolean userInSystem(String userId) {
        return false
    }

    @Override
    void saveReport(Report report) {

    }

    @Override
    Report getReportById(String reportId) {
        return null
    }
}
