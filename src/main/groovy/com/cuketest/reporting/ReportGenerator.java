package com.cuketest.reporting;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/reports")
public interface ReportGenerator {

    @GET
    @Produces("application/json")
    @Path("/getReport/{userId}/{reportId}")
    Response show(@PathParam("userId") String userId,
                  @PathParam("reportId") String reportId);


    @POST
    @Produces("application/json")
    @Path("/generateRpt/{userId}/{reportType}/{params}")
    Response generateReport(@PathParam("userId") String userId,
                          @PathParam("reportType") String reportType,
                          @PathParam("params") String params);

}
