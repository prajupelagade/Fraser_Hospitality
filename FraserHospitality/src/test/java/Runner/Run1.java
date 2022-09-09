package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(features="fraserfeatures",glue="Steps",plugin = { "pretty", 
        "json:target/cucumber-report.json",
"html:target/cucumber-reports.html"})



public class Run1 extends AbstractTestNGCucumberTests {

}
