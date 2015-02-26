package io.delr3ves.smashtechtalk;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Sergio Arroyo - @delr3ves
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
            "pretty", "html:target/cucumber-html-report",
            "json:target/cucumber-report.json",
            "junit:target/cucumber-report.xml",
            "rerun:target/cucumber-failed.txt"
        },
        snippets = SnippetType.CAMELCASE
)
public class TestRunner {
}