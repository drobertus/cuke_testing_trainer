package com.cuketest.reporting.steps.dto

import com.cuketest.reporting.dto.ReportDefinition

/**
 * Created by drobertu on 12/8/14.
 */
class ReportDef {

    String name
    String paramNames

    ReportDefinition toReportDefinition() {
        def params = []
        paramNames.split(',').each {
            params << it.trim()
        }

        return new ReportDefinition(name: name, parameters: params)
    }
}
