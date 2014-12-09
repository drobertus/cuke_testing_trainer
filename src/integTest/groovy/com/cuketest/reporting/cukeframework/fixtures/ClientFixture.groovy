package com.cuketest.reporting.cukeframework.fixtures

import com.cuketest.reporting.cukeframework.steps.dto.ReportParameter
import com.google.inject.Singleton
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient;

@Singleton
public class ClientFixture {

    def url = "http://localhost:${ReportGeneratorFixture.serverPort}/reports"
    public static String SAMPLE_REPORT_CONTENT = "This is a sample report"

    def lastResponse

    TestReportMapping reportState = TestReportMapping.instance


    def generateReport(def userId, def reportId, List<ReportParameter> params) {

        def formattedParams = ""
        def isFirst = true
        params.each {  it ->
            if (!isFirst) {
                formattedParams += ','
            }
            formattedParams = formattedParams + "${it.parameterName}=${it.value}"
            isFirst = false
        }
        def path = "/generateRpt/${userId}/${URLEncoder.encode(reportId, 'UTF-8')}/${formattedParams}"
        println "thePath= ${path}"

        HttpClient client = new DefaultHttpClient();

        def thePost = new HttpPost(url + path)

        def theResponse = client.execute(thePost)
        lastResponse = theResponse.getEntity().getContent().getText()
        println "response to genReport= ${lastResponse}"
        return lastResponse

    }

    void assignLastResponseToReportId(String internalReportRef) {
        if (this.lastResponse && lastResponse.length() == 11) {
            reportState.setTestReportNameToId(internalReportRef, lastResponse)
            lastResponse = null
        }
        else {
            throw new Exception("The last response [${lastResponse}] does not appear to be a valid generated reportId")
        }

    }

    void requestReport(String userName, String reportName) {

        def theId = reportState.getReportIdFromTestId(reportName)
        def url = "${url}/getReport/${userName}/${theId}".toURL().getText()
        println 'getReport response = ' + url
        lastResponse = url
    }
}