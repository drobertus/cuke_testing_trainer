package com.cuketest.prod

import org.junit.Test

import static org.junit.Assert.assertEquals

/**
 * Created by David on 9/19/2014.
 */
class ReportGenTest {

    @Test
    public void testReporGenService() {
        def reportGen = new ReportGenerator()
        def result = reportGen.show()
        assertEquals 'ta-da service!!', result

    }
}
