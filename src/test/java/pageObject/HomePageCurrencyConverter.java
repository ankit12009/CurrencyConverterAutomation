package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageCurrencyConverter {

	WebDriver driver;

	public HomePageCurrencyConverter(WebDriver driver) {
		this.driver = driver;
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	private By aboutMenuLink = By.xpath("//li[contains(text(), 'About Us')]");
	private By firstCard = By.xpath("//*[@id=\'team-grid\']/div[1]/div/div[2]");
	private By quote = By.xpath("//*[@id=\'team-grid\']/div[1]/div/div[2]/p");
	private By toggleContainer=By.xpath("//*[@id=\'themeToggleContainer\']/div");
	private By themeLabel=By.id("themeLabel");
	private By amountInput=By.id("amount");
	private By convertBtn= By.id("convertBtn");
	private By convertResult=By.id("bigResult");
	private By trendspage= By.xpath("//li[contains(text(), 'Trends')]");
	private By aboutPage=By.id("about-page");
	private By converterList=By.xpath("//li[contains(text(), 'Converter')]");
	private By converterPage=By.id("converter-page");
	
	public WebElement getConverterPage() {
		return driver.findElement(converterPage);
	}
	
	public void clickConverter() {
		driver.findElement(converterList).click();
	}
	
	public WebElement getAboutPage() {
		return driver.findElement(aboutPage);
	}
	
	public WebElement getTrendsPage() {
		return driver.findElement(trendspage);
	}
			public void clickOnTrendsPage() throws InterruptedException {
//				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				 js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.elementToBeClickable(trendspage)));
				Thread.sleep(2000);
				 driver.findElement(trendspage).click();
				 Thread.sleep(2000);
				 driver.switchTo().alert().accept();
			}
			
	public String getConvertResult() {
	return driver.findElement(convertResult).getText();	
	}
	
	public String getConvertedResult() {
		return driver.findElement(convertResult).getText();
				
	}
	
	public void clickConvetBtn() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		 JavascriptExecutor js = (JavascriptExecutor) driver;
//		 js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.elementToBeClickable(convertBtn)) );
		Thread.sleep(1000);
		driver.findElement(convertBtn).click();
	}
	
	public void setAmount(String Amount) {
		driver.findElement(amountInput).sendKeys(Amount);
	}

	public void aboutUsClick() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("splash-screen")));
		wait.until(ExpectedConditions.elementToBeClickable(aboutMenuLink)).click();
	}

	public WebElement getFirstCard() {
		return driver.findElement(firstCard);
	}

	public boolean getQuoteDisplayed() {
		return getFirstCard().findElement(quote).isDisplayed();
	}
	
	public void themeToggleClick() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		 JavascriptExecutor js = (JavascriptExecutor) driver;
//		 js.executeScript("arguments[0].click();",wait.until(ExpectedConditions.elementToBeClickable(toggleContainer)) );
		 Thread.sleep(3000);
		 driver.findElement(toggleContainer).click();
	}
	public String getThemeLabel() {
		return driver.findElement(themeLabel).getText();
	}
	
	
}
