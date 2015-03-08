package io.delr3ves.smashtechtalk;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class SmashtechInjectorSource implements InjectorSource {

    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.PRODUCTION, CucumberModules.SCENARIO, new SmashtechGuiceModule(initializeConfiguration()));
    }

    private SmashtechConfig initializeConfiguration() {
        return new SmashtechConfig();
    }
}
