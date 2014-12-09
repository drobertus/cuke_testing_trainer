package com.cuketest.reporting.util

/**
 * Created by David on 12/4/2014.
 */
class TestParameterGenerator {

    static def convertToNameValPair(List<String> keys, List<String>vals = [keys.size()]) {

        def formattedParams = ""
        for(int i=0; i < keys.size(); i ++) {
            def aKey = keys[i]
            def aVal = vals[i]
            if (i > 0)
                formattedParams += ','
            formattedParams = formattedParams + "${aKey}=${aVal}"

        }
        return formattedParams
    }
}
