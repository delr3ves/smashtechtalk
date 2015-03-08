package io.delr3ves.smashtechtalk.hooks;

import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;

import javax.inject.Inject;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class WebsiteHooks {

    @Inject
    private WebDriver driver;

    @After()
    public void stopDriver() {
        //global hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    driver.quit();
                } catch (Throwable e) {
                   //Its ok, the browser is already closed
                }
            }
        });
    }
}
