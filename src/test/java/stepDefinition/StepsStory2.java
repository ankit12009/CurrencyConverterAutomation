package stepDefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import pageObject.HomePageCurrencyConverter;

public class StepsStory2 {

	private WebDriver getDriver() {
		return Story1.driver;
	}
	
	
	private HomePageCurrencyConverter getHomepage() {
		return Story1.HomePage;
	}
	private WebDriverWait getWait() {
		return new WebDriverWait(getDriver(), Duration.ofSeconds(10));
	}
	
	@Then("Verify Light Mode is still active")
	public void verify_light_mode_is_still_active() {
		WebElement body = getDriver().findElement(By.tagName("body"));
		String bodyClass = body.getAttribute("class");
        Assert.assertFalse(bodyClass.contains("Light-mode"), "Theme did not persist after navigation!");
	}

	@Then("I Switch to Trends")
	public void i_switch_to_trends() throws InterruptedException {
		getHomepage().clickOnTrendsPage();
		Assert.assertTrue(getHomepage().getTrendsPage().isDisplayed());
	}

	@Then("I Switch to About")
	public void i_switch_to_about() throws InterruptedException {
		Thread.sleep(2000);
		getHomepage().aboutUsClick();
		Assert.assertTrue(getHomepage().getAboutPage().isDisplayed());
	}
  public void handleAlert() {
	  getDriver().switchTo().alert().accept();
}
	@Then("I Switch back to Converter")
	public void i_switch_back_to_converter() {
		getHomepage().clickConverter();

	}

	@Then("I checked Converter page should display")
	public void i_checked_converter_page_should_display() {
		Assert.assertTrue(getHomepage().getConverterPage().isDisplayed());
	}
	
	@Then("Perform a dummy conversion to generate history")
	public void perform_a_dummy_conversion_to_generate_history() throws InterruptedException {
		Thread.sleep(2000);
		getHomepage().setAmount("500");
	 Thread.sleep(2000);
	 getHomepage().clickConvetBtn();
	}

	@Then("I Ensure history item is present")
	public void i_ensure_history_item_is_present() {
		
		  WebElement historyList = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("historyList")));
	        int initialHistoryCount = historyList.findElements(By.tagName("li")).size();
	        Assert.assertTrue(initialHistoryCount > 0, "No history generated.");
	}

	@Then("I Verify history still exists")
	public void i_verify_history_still_exists() {
		 WebElement historyList = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("historyList")));
		 int initialHistoryCount = historyList.findElements(By.tagName("li")).size();
		  int finalHistoryCount = getDriver().findElement(By.id("historyList")).findElements(By.tagName("li")).size();
	        Assert.assertEquals(finalHistoryCount, initialHistoryCount, "History was cleared during navigation.");
	}
	
	@Then("I Entered the amount {string}")
	public void i_entered_the_amount(String amount) throws InterruptedException {
		System.out.println(amount);
		getHomepage().setAmount(amount);
		getHomepage().clickConvetBtn();   
	}

	@Then("I Verify amount is still there {string}")
	public void i_verify_amount_is_still_there(String amount) {
		 String currentAmount = getDriver().findElement(By.id("amount")).getAttribute("value");
	        Assert.assertEquals(currentAmount, amount, "Input field data was lost during navigation.");
	}
}
