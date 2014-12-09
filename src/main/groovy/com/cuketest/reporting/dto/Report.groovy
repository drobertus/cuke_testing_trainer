package com.cuketest.reporting.dto

/**
 * Created by David on 12/5/2014.
 */
class Report {
    def id
    ReportDefinition reportDefinition
    String userEmail
    ReportStatus status
    Map<String, String> reportParameters
    String theReport
}
