package com.cuketest.prod

import com.google.inject.Inject;
import com.google.inject.Singleton
import com.sun.jersey.core.spi.factory.ResponseImpl

import javax.ws.rs.core.Response;


@Singleton
public class DefaultReportGenerator implements ReportGenerator {

    private ReportGeneratorService rptGenService

    @Inject
    DefaultReportGenerator(final ReportGeneratorService reportGenService) {
        rptGenService = reportGenService
    }

    @Override
    public Response show(final String reportId) {
        def theResponse = rptGenService.show(reportId)
        return Response.ok(theResponse).build();
    }

    @Override
    public Response generateReport(final String userId, final String reportType, final String params) {

        def genRequest = rptGenService.generateReport(userId, reportType,params)
        return Response.ok(genRequest).build()
    }
}
