package com.cuketest.reporting.service

import com.cuketest.reporting.dto.Report


public interface ReportService {

    boolean isValidReport(String reportType, Collection<String> params)

    String createReport(String userId, String reportType, Map<String,String> params)

    Report getReport(String reportId);
}