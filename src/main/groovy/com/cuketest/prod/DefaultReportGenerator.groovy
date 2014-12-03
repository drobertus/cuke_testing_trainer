package com.cuketest.prod

import com.google.inject.Inject;
import com.google.inject.Singleton;


@Singleton
public class DefaultReportGenerator implements ReportGenerator {

    private ReportGeneratorService rptGenService

    @Inject
    DefaultReportGenerator(final ReportGeneratorService reportGenService) {
        //System.out.println("Default report generator")
        rptGenService = reportGenService
    }

    @Override
    public String show(final String reportId) {
        return rptGenService.show(reportId)
    }

    @Override
    public String generateReport(final String userId, final String reportType, final String params) {

        return rptGenService.generateReport(userId, reportType,params)
    }
}
