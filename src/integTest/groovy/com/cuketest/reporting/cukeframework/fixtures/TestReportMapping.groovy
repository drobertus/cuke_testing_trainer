package com.cuketest.reporting.cukeframework.fixtures

@Singleton
class TestReportMapping {

    private def responseReportMap = [:]


    void setTestReportNameToId(String testId, String generatedId) {
        responseReportMap.put(testId, generatedId)
    }

    String getReportIdFromTestId(String testName) {
        return responseReportMap.get(testName)
    }
}
