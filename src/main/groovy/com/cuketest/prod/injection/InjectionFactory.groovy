package com.cuketest.prod.injection

import com.google.inject.Injector

/**
 * Created by drobertu on 9/19/14.
 */
abstract class InjectionFactory {

    Injector injector

    Injector getInjector() {
        return injector
    }
}
