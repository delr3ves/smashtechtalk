package io.delr3ves.smashtechtalk.steps;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.delr3ves.smashtechtalk.model.User;
import io.delr3ves.smashtechtalk.model.builder.UserBuilder;
import io.delr3ves.smashtechtalk.services.UserService;
import io.delr3ves.smashtechtalk.state.ScenarioState;
import io.delr3ves.smashtechtalk.state.SuiteState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.inject.Inject;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class UserCreationSteps {

    public static final String CREATED_USER_KEY = "createdUser";

    @Inject
    private UserService userService;
    @Inject
    private WebDriver driver;
    @Inject
    private SuiteState suiteState;

    @Inject
    private UserBuilder userBuilder;

    private static Scenario scenario;

    @Before
    public static void injectScenario(Scenario currentScenario) {
        scenario = currentScenario;
    }

    @When("^user cretates an user with \"(.*?)\"$")
    public void userCretatesAnUserWith(String userDescription) throws Throwable {
        ScenarioState state = suiteState.stateFor(scenario.getId());
        User user = userBuilder.createUserByDescription(userDescription);
        User createdUser = userService.register(user);

        state.put(CREATED_USER_KEY, createdUser);
    }

    @Then("^the user should be created$")
    public void theUserShouldBeCreated() throws Throwable {
        WebElement successMessage = driver.findElement(By.id("js-confirmacion"));
        assertTrue(successMessage.getText().contains("Muchas gracias, tu cuenta se ha creado correctamente"));
    }

    @Then("^the user will not be created due to (\\d+) errors are found$")
    public void theUserWillNotBeCreatedDueToErrorAreFound(Integer numberOfReasons) {
        WebElement errorContainer = driver.findElement(By.id("error"));
        List<WebElement> errors = errorContainer.findElements(By.tagName("li"));
        assertThat(errors.size(), equalTo(numberOfReasons));
    }

}
