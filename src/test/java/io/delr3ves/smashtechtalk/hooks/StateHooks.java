package io.delr3ves.smashtechtalk.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.delr3ves.smashtechtalk.state.SuiteState;

import javax.inject.Inject;
import java.io.IOException;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class StateHooks {

    @Inject
    private SuiteState suiteState;

    @Before
    public void startRecorder(Scenario scenario) {
        suiteState.initializeScenario(scenario.getId());
    }

    @After
    public void stopRecorder(Scenario scenario) throws IOException {
        suiteState.removeScenarioState(scenario.getId());
    }
}
