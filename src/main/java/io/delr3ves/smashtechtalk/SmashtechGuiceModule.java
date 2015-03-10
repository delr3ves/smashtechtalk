package io.delr3ves.smashtechtalk;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.delr3ves.smashtechtalk.services.RecorderService;
import io.delr3ves.smashtechtalk.services.RecorderServiceImpl;
import io.delr3ves.smashtechtalk.services.UserService;
import io.delr3ves.smashtechtalk.services.UserServiceWebdriverImpl;
import io.delr3ves.smashtechtalk.state.SuiteState;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.internal.ApacheHttpClient;

import javax.inject.Singleton;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class SmashtechGuiceModule extends AbstractModule {

    private SmashtechConfig config;
    private SuiteState suiteState = new SuiteState();

    public SmashtechGuiceModule(SmashtechConfig config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        bind(SmashtechConfig.class).toInstance(config);
        bind(UserService.class).to(UserServiceWebdriverImpl.class);
        bind(RecorderService.class).to(RecorderServiceImpl.class);
        bind(SuiteState.class).toInstance(suiteState);
    }

    @Provides
    @Singleton
    WebDriver getDefaultWebDriver() {
        WebDriver driver;
        if (config.getBrowser().equals(SmashtechConfig.CHROME)) {
            System.setProperty("webdriver.chrome.driver", config.getPathToChromeDriver());
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(
                config.getDefaultTimeToWaitInSeconds(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return new Augmenter().augment(driver);
    }

    @Provides
    @Singleton
    HttpClient getHttpClient() {
        try {
            return new ApacheHttpClient.Factory().createClient(new URL(config.getRecorderUrl()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
