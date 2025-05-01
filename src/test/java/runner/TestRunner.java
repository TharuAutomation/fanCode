package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefs",
        
        		plugin = {
        	            "pretty",
        	            "html:src/test/resources/output/html-report.html",
        	            "html:target/cucumber-report.html",
        	            "json:src/test/resources/output/report.json"
        	        },
        monochrome = true
)
public class TestRunner {
}
