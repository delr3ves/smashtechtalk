package io.delr3ves.smashtechtalk.steps;

import cucumber.api.java.en.Given;
import io.delr3ves.smashtechtalk.SmashtechConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class NavigationSteps {

    @Inject
    private WebDriver driver;

    @Inject
    private SmashtechConfig config;


    @Given("^user is in registration page$")
    public void userIsInRegistrationPage() throws Throwable {
        driver.get(config.getBaseUrl());
        List<WebElement> closeButons = driver.findElements(By.cssSelector("a.leadCloser.js-cerrar"));
        if (!closeButons.isEmpty()) {
            closeButons.get(0).click();
        }
        driver.findElement(By.id("userZoneTitle")).click();
        driver.findElement(By.linkText("¡Regístrate ahora!")).click();
    }
}
