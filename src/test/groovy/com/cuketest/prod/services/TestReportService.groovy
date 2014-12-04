package com.cuketest.prod.services

import groovy.util.logging.Log

@Log
class TestReportService implements ReportService {

    Map<String, List<String>> reportMap = [:]

    TestReportService(reports){
        log.info 'test report service created'
        reportMap = reports
    }

    @Override
    boolean isValidReport(String reportType, Collection<String> params) {
        boolean found = false
        def allowedParams = reportMap.get(reportType)
        println "the Params= ${params} and size = ${params.size()}"
        if(allowedParams && allowedParams.size() == params.size()) {
            def matched = 0
            for (String aParam : params) {
                println aParam
                if (allowedParams.contains(aParam)){
                    matched ++
                }else {
                    println 'not found'
                }
            }
            if (matched == allowedParams.size()) {
                found = true
            }
        }

        return found
    }
}
