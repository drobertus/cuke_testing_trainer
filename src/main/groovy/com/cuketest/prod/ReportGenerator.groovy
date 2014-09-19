package com.cuketest.prod



import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("/reportGenerator")
public class ReportGenerator {

    @Produces(["text/plain"])
    @GET
    @Path("/show")
    String show() {
        "ta-da service!!"
    }
}
