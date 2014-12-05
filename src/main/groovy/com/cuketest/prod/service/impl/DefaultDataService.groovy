package com.cuketest.prod.service.impl

import com.cuketest.prod.dto.Report
import com.cuketest.prod.dto.ReportDefinition
import com.cuketest.prod.service.DataService

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
