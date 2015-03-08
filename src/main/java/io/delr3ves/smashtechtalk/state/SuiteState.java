package io.delr3ves.smashtechtalk.state;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class SuiteState {

    private Map<String, ScenarioState> stateByScenario = new HashMap<>();

    public void initializeScenario(String scenario) {
        stateByScenario.put(scenario, new ScenarioState());
    }

    public void removeScenarioState(String scenario) {
        ScenarioState state = stateByScenario.get(scenario);
        if (state != null) {
            state.tearDown();
        }
        stateByScenario.remove(scenario);
    }

    public ScenarioState stateFor(String scenario) {
        ScenarioState state = stateByScenario.get(scenario);
        if (state == null) {
            state = new ScenarioState();
            stateByScenario.put(scenario, state);
        }
        return state;
    }
}
