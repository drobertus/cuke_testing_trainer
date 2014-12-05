package com.cuketest.prod.service.impl

import com.cuketest.prod.dto.Report
import com.cuketest.prod.dto.ReportDefinition
import com.cuketest.prod.service.DataService
import com.cuketest.prod.service.ReportService
import com.cuketest.prod.util.ReportUtils
import com.google.inject.Inject

class DefaultReportService implements ReportService {

    DataService dataService

    @Inject
    public DefaultReportService(DataService ds){
        dataService = ds;
    }

    @Override
    boolean isValidReport(String reportType, Collection<String> params) {
        boolean found = false

        def rptDef = dataService.getReportDefinition(reportType)

        if (rptDef) {
            def allowedParams = rptDef.getParameters()
            println "the Params= ${params} and size = ${params.size()}"
            if (allowedParams.size() == params.size()) {
                def matched = 0
                for (String aParam : params) {
                    println aParam
                    if (allowedParams.contains(aParam)) {
                        matched++
                    } else {
                        println 'not found'
                    }
                }
                if (matched == allowedParams.size()) {
                    found = true
                }
            }
        }
        return found
    }

    @Override
    String createReport(String userId, String reportType, Map<String, String> params) {

        def uniqueId = ReportUtils.generateUniqueReportId()
        return uniqueId
    }

    @Override
    Report getReport(String reportId) {
        return dataService.getReportById(reportId)
    }
}
