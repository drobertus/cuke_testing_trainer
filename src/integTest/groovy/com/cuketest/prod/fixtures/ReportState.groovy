package com.cuketest.prod.fixtures

@Singleton
class ReportState {

    private def responseReportMap = [:]
//
//    private ReportState() {
//      println('crating new report state')
//    }

    void setTestReportNameToId(String testId, String generatedId) {
        responseReportMap.put(testId, generatedId)
    }

    String getReportIdFromTestId(String testName) {
        return responseReportMap.get(testName)
    }
}
