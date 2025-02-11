package TestRunner;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/Features/UserAPI.feature"},

    glue = {"StepDefinitions"},

    plugin = {"pretty","html:target/CucumberReports/CucumberReport.html",
    		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)

	public class Runner {
		
}
