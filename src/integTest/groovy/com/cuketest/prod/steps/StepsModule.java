package com.cuketest.prod.steps;

import com.cuketest.prod.fixtures.ClientFixture;
import com.cuketest.prod.fixtures.ReportGeneratorFixture;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

/**
 * Created by David on 12/5/2014.
 */
public class StepsModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(ReportGeneratorFixture.class).in(Singleton.class);
        binder.bind(ClientFixture.class).in(Singleton.class);
    }
}
