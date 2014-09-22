package com.cuketest.prod

import com.cuketest.prod.injection.DefaultInjectionFactory
import com.cuketest.prod.injection.InjectionFactory
import com.cuketest.prod.services.ReportService
import com.cuketest.prod.services.UserService
import com.google.inject.Injector

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces

@Path("/reports")
public class ReportGenerator {


    private UserService userService

    private ReportService reportService

    public static final String INVALID_USER_ERROR = 'Invalid user'
    public static final String INVALID_REPORT_REQUEST = 'Invalid report request'
    static InjectionFactory injectorFactory

    public ReportGenerator() {

        this.userService = getInjectorFromFactory().getInstance(UserService.class)
        this.reportService = getInjectorFromFactory().getInstance(ReportService.class)
    }

    /**
     * Show a report that was generated and given a unique id
     * @param reportId
     * @return
     */
    @GET
    @Produces(['application/json'])
    @Path("/show/{reportId}")
    String show(@PathParam("reportId") String reportId) {
        "ta-da!! Here is the report named ${reportId}"
    }

    /**
     * request for a report to be built
     * @param userId
     * @param reportType
     * @param params
     * @return
     */
    @POST
    @Produces(['application/json'])
    @Path('/generateRpt/{userId}/{reportType}/{params}')
    String generateReport(@PathParam("userId") String userId,
                          @PathParam("reportType") String reportType,
                          @PathParam("params") String params) {


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

    public Injector getInjectorFromFactory() {
        if (!injectorFactory) {
            injectorFactory = new DefaultInjectionFactory()
        }

        return injectorFactory.getInjector()
    }


}
