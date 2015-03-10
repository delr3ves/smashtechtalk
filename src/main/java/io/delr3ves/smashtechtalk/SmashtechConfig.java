package io.delr3ves.smashtechtalk;

import lombok.Data;

/**
 * @author Sergio Arroyo - @delr3ves
 */
@Data
public class SmashtechConfig {

    public static final String FIREFOX = "firefox";
    public static final String CHROME = "chrome";

    private String baseUrl = "https://www.atrapalo.com/";
    private String recorderUrl = "http://localhost:9998";
    private String webdriverUrl = "http://localhost";
    private Integer defaultTimeToWaitInSeconds = 2;

    private Boolean recordOnlyOnFailure = false;
    private String baseVideoPath = "/Users/serch/Desktop/tmp/";

    private String browser = CHROME;
    private String pathToChromeDriver = "/Users/serch/apps/chromeDriver";
}
