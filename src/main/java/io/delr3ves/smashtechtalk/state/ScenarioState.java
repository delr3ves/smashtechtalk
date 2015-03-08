package io.delr3ves.smashtechtalk.state;

import java.util.HashMap;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class ScenarioState extends HashMap<String, Object> {

    public void tearDown() {

    }

    public <T> T getState(String key) {
        return (T) this.get(key);
    }
}
