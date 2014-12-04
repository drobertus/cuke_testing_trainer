package com.cuketest.prod

import com.cuketest.prod.services.ReportService
import com.cuketest.prod.services.UserService
import com.google.inject.Inject
import groovy.util.logging.Log

@Log
public class ReportGeneratorService {

    private UserService userService
    private ReportService reportService

    public static final String INVALID_USER_ERROR = 'Invalid user'
    public static final String INVALID_REPORT_REQUEST = 'Invalid report request'

    @Inject
    public ReportGeneratorService(UserService us, ReportService rs) {
        //we need to use an empty constructor since we don't
        //control the instantiation
        log.info('injecting user and report services')
        this.userService = us //getInjectorFromFactory().getInstance(UserService.class)
        this.reportService = rs //getInjectorFromFactory().getInstance(ReportService.class)
    }

    /**
     * Show a report that was generated and given a unique id
     * @param reportId
     * @return
     */
    String show(String reportId) {

        "ta-da!! Here is the report named ${reportId}"
    }


    String generateReport(String userId,
                          String reportType,
                          String params) {


        if(!userService.isValidUser(userId)) {
            return INVALID_USER_ERROR
        }


        if (!reportService.isValidReport(reportType, params.split(',').flatten() )) {
            return INVALID_REPORT_REQUEST
        }

        def pool = ['a'..'z','A'..'Z',0..9,'_'].flatten()
        Random rand = new Random(System.currentTimeMillis())

        def passChars = (0..10).collect { pool[rand.nextInt(pool.size())] }
        passChars.join()
    }


}
