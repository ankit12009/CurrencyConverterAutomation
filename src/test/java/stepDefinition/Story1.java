package stepDefinition;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Driver.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObject.HomePageCurrencyConverter;

public class Story1 {

	static WebDriver driver;
	static HomePageCurrencyConverter HomePage;
	static Actions action;
public WebDriver getDriver() {
	return driver;
}
	public HomePageCurrencyConverter getHomePageObj() {
		return HomePage;
	}
	@Given("I Launch the browser")
	public void openBrowser() throws IOException {
		driver = Driver.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		action = new Actions(driver);
		HomePage = new HomePageCurrencyConverter(driver);

	}

	@Then("I open the URL {string}")
	public void NavigateURL(String URL) {
		driver.get(URL);
	}

	@Then("I Navigate to the About us page")
	public void navigatAboutpage() {

		HomePage.aboutUsClick();
	}

	@Then("I identify the team card and performe a mouse hover")
	public void i_identify_the_team_card_and_performe_a_mouse_hover() {
		action.moveToElement(HomePage.getFirstCard()).perform();
	}

	@Then("I Checked if the Member Quote \\(reverse side) becomes visible")
	public void MemberQuoteDisplayed() {
		Assert.assertTrue(HomePage.getQuoteDisplayed(), "Member quote not visible after flip.");
		System.out.println("Test Passed: About Page Grid is responsive and interactive.");
	}

	@Then("Close Browser")
	public void closeBrowser() {

		driver.quit();
	}

	@Then("I Chanced the theam to dark mode")
	public void i_chanced_the_theam_to_dark_mode() throws InterruptedException {
//		System.out.println(driver);
		HomePage.themeToggleClick();
	}

	@Then("I checked Theam should be changed")
	public void i_checked_theam_should_be_changed() {
		String actual = HomePage.getThemeLabel();
		System.out.println(actual);
		Assert.assertEquals(actual, "Light Mode", "Label did not update correctly");
	}

	@Then("I entered the amount in the input filed")
	public void i_entered_the_amount_in_the_input_filed() throws InterruptedException {
		Thread.sleep(1000);
		HomePage.setAmount("100");
	}

	@Then("I click on convert button")
	public void i_click_on_convert_button() throws InterruptedException {
		HomePage.clickConvetBtn();
	}

	@Then("I checked for the Numeric amount as result")
	public void i_checked_for_the_numeric_amount_as_result() {
		Assert.assertFalse(HomePage.getConvertedResult().isEmpty(), "Conversion result is empty");
		Assert.assertTrue(HomePage.getConvertedResult().contains("USD") || HomePage.getConvertedResult().matches(".*\\d.*"),"Result does not contain numeric conversion value");
	}

}
