package com.cuketest.reporting.service

import com.cuketest.reporting.dto.Report
import com.cuketest.reporting.dto.ReportDefinition

interface DataService {


    ReportDefinition getReportDefinition(String reportTypeName)

    boolean userInSystem(String userId);

    void saveReport(Report report)

    Report getReportById(String reportId)


}