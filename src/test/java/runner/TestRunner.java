package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = "stepdefinition",           // Path to your step definitions
        plugin = { "pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json" }, // Reporting plugins
        monochrome = true                        // Makes console output more readable
)

public class TestRunner {

}