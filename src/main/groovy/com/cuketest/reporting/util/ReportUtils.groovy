package com.cuketest.reporting.util

/**
 * Created by David on 12/4/2014.
 */
class ReportUtils {

    static Map<String,String> parseParams(String paramsString) {

        def valMap =[:]
        paramsString.split(',').each { aKeyVal ->
           def aPair = aKeyVal.split('=')
            valMap.put(aPair[0], aPair[1])
        }
        println "ValMap = ${valMap}"
        return valMap
    }

    static generateUniqueReportId() {

        def pool = ['a'..'z','A'..'Z',0..9,'_'].flatten()
        Random rand = new Random(System.currentTimeMillis())

        def passChars = (0..10).collect { pool[rand.nextInt(pool.size())] }
        passChars.join()
    }
}
