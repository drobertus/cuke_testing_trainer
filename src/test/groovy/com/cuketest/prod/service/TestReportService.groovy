package com.cuketest.prod.service

import com.cuketest.prod.dto.Report
import com.cuketest.prod.dto.ReportDefinition
import com.cuketest.prod.dto.ReportStatus
import com.cuketest.prod.util.ReportUtils
import groovy.util.logging.Log

@Log
class TestReportService implements ReportService {

    Map<String, ReportDefinition> reportTypeMap = [:]

    Map<String, Report> existingReports = [:]

    TestReportService(reports){
        log.info 'test report service created'
        reportTypeMap = reports
    }

    @Override
    boolean isValidReport(String reportType, Collection<String> params) {
        boolean found = false
        def rptType = reportTypeMap.get(reportType)

        println "the Params= ${params} and size = ${params.size()}"
        if(rptType && rptType.parameters.size() == params.size()) {
            def matched = 0
            for (String aParam : params) {
                println aParam
                if (rptType.parameters.contains(aParam)){
                    matched ++
                }else {
                    println 'not found'
                }
            }
            if (matched == rptType.parameters.size()) {
                found = true
            }
        }

        return found
    }

    @Override
    String createReport(String userId, String reportType, Map<String, String> params) {

        def newId = ReportUtils.generateUniqueReportId()
        def newReport = new Report(userEmail: userId, reportDefinition: this.reportTypeMap[reportType],
            reportParameters: params, id: newId)
        this.existingReports.put(newId, newReport)
        return newId

    }

    @Override
    Report getReport(String reportId) {
        return existingReports.get(reportId)
    }
}
