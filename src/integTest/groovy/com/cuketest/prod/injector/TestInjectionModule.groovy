package com.cuketest.prod.injector

import com.cuketest.prod.injection.InjectionFactory
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Created by drobertu on 9/19/14.
 */
class TestInjectionModule extends InjectionFactory {

    TestInjectionModule() {
        injector = Guice.createInjector(new TestModule())
    }

}
