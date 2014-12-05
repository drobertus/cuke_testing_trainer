package com.cuketest.prod.service

import com.cuketest.prod.dto.Report
import com.cuketest.prod.dto.ReportDefinition

interface DataService {


    ReportDefinition getReportDefinition(String reportTypeName)

    boolean userInSystem(String userId);

    void saveReport(Report report)

    Report getReportById(String reportId)


}