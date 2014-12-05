package com.cuketest.prod

import com.cuketest.prod.util.ReportUtils
import com.google.inject.Inject;
import com.google.inject.Singleton

import javax.ws.rs.core.Response;


@Singleton
public class DefaultReportGenerator implements ReportGenerator {

    private ReportGeneratorService rptGenService

    @Inject
    DefaultReportGenerator(final ReportGeneratorService reportGenService) {
        rptGenService = reportGenService
    }

    @Override
    public Response show(final String userId, final String reportId) {
        def theResponse = rptGenService.getReport(userId, reportId)
        return Response.ok(theResponse).build();
    }

    @Override
    public Response generateReport(final String userId, final String reportType, final String params) {
        println("params found in default=" + params)
        Map<String,String> keyVals = ReportUtils.parseParams(params)
        def genRequest = rptGenService.generateReport(userId, reportType, keyVals)
        return Response.ok(genRequest).build()
    }
}
