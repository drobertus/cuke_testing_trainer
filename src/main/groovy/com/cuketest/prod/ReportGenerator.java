package com.cuketest.prod;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/reports")
public interface ReportGenerator {

    @GET
    @Produces("application/json")
    @Path("/show/{reportId}")
    Response show(@PathParam("reportId") String reportId);


    @POST
    @Produces("application/json")
    @Path("/generateRpt/{userId}/{reportType}/{params}")
    Response generateReport(@PathParam("userId") String userId,
                          @PathParam("reportType") String reportType,
                          @PathParam("params") String params);

}
