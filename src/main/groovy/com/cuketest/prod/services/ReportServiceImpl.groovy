package com.cuketest.prod.services

import com.google.inject.Inject


class ReportServiceImpl implements ReportService{

//    @Inject
//    DataProvider dataSource
//
//    @Inject
//    public ReportServiceImpl(DataProvider ds){
//        dataSource = ds;
//    }

    @Override
    boolean isValidReport(String reportType, List<String> params) {
        return false
    }
}
