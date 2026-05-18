package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features={"C:\\TestLearn\\CurrencyConverter\\Feature\\Story1.feature","C:\\TestLearn\\CurrencyConverter\\Feature\\Story2.feature"},
		glue="stepDefinition",
		dryRun=false,
		monochrome=true,
		plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

public class Runner extends AbstractTestNGCucumberTests {

}
