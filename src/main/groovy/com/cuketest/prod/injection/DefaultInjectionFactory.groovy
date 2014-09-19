package com.cuketest.prod.injection

import com.google.inject.Guice
import com.google.inject.Injector


class DefaultInjectionFactory extends InjectionFactory {

    DefaultInjectionFactory() {
        injector = Guice.createInjector(new ProdModule());
    }

}
