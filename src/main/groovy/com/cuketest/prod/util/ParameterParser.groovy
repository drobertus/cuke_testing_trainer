package com.cuketest.prod.util

/**
 * Created by David on 12/4/2014.
 */
class ParameterParser {

    static Map<String,String> parseParams(String paramsString) {

        def valMap =[:]
        paramsString.split(',').each { aKeyVal ->
           def aPair = aKeyVal.split('=')
            valMap.put(aPair[0], aPair[1])
        }
        println "ValMap = ${valMap}"
        return valMap
    }
}
