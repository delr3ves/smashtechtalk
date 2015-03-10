package io.delr3ves.smashtechtalk.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.delr3ves.smashtechtalk.SmashtechConfig;
import io.delr3ves.smashtechtalk.services.RecorderService;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author Sergio Arroyo - @delr3ves
 */
public class RecorderHooks {

    @Inject
    private RecorderService recorderService;
    @Inject
    private SmashtechConfig config;
    @Inject
    private WebDriver driver;

    @Before("@recorded")
    public void startRecorder() {
        recorderService.startRecording();
    }

    @After("@recorded")
    public void stopRecorder(Scenario scenario) throws IOException, InterruptedException {
        if (!config.getRecordOnlyOnFailure() || scenario.isFailed()) {
            Thread.sleep(1000);
            String file = recorderService.stopRecording(UUID.randomUUID().toString());

            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                byte[] image = screenshot.getScreenshotAs(OutputType.BYTES);
                scenario.embed(image, "image/png");
            } catch (Throwable e) {
                //the browser has no ability to take an screenshot
            }

            Path path = Paths.get(config.getBaseVideoPath() + file);
            scenario.write("</pre><p><a href=\"" + path + "\">See the execution video here</a></p><pre>");
        } else {
            recorderService.rejectRecording();
        }
    }

}
