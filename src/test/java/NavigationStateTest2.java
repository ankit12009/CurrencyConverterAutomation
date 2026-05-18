import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class NavigationStateTest2 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://currency-converter-six-tan.vercel.app/"); // Update with your actual file path
        
        // Wait for Splash screen to bypass
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("splash-screen")));
    }

    @Test(priority = 1, description = "TC_INT_001: Seamless Page Switching")
    public void testPageSwitching() {
        // Switch to Trends
        driver.findElement(By.xpath("//li[contains(text(), 'Trends')]")).click();
        Assert.assertTrue(driver.findElement(By.id("trends-page")).isDisplayed());

        // Switch to About
        driver.findElement(By.xpath("//li[contains(text(), 'About Us')]")).click();
        Assert.assertTrue(driver.findElement(By.id("about-page")).isDisplayed());

        // Switch back to Converter
        driver.findElement(By.xpath("//li[contains(text(), 'Converter')]")).click();
        Assert.assertTrue(driver.findElement(By.id("converter-page")).isDisplayed());
    }

    @Test(priority = 2, description = "TC_INT_002: Theme State Persistence")
    public void testThemePersistence() {
        WebElement toggle = driver.findElement(By.id("themeToggleContainer"));
        WebElement body = driver.findElement(By.tagName("body"));

        // Step 1: Set to Light Mode on Converter Page
      
        toggle.click();

        // Step 2: Navigate to About Us
        driver.findElement(By.xpath("//li[contains(text(), 'About Us')]")).click();

        // Step 3: Verify Light Mode is still active
        String bodyClass = body.getAttribute("class");
        Assert.assertFalse(bodyClass.contains("dark-mode"), "Theme did not persist after navigation!");
    }

    @Test(priority = 3, description = "TC_INT_003: Recent Conversions Persistence")
    public void testHistoryPersistence() {
        // Step 1: Perform a dummy conversion to generate history
        driver.findElement(By.id("amount")).sendKeys("500");
        driver.findElement(By.id("convertBtn")).click();
        
        // Ensure history item is present
        WebElement historyList = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("historyList")));
        int initialHistoryCount = historyList.findElements(By.tagName("li")).size();
        Assert.assertTrue(initialHistoryCount > 0, "No history generated.");

        // Step 2: Navigate away and return
        driver.findElement(By.xpath("//li[contains(text(), 'Trends')]")).click();
        driver.findElement(By.xpath("//li[contains(text(), 'Converter')]")).click();

        // Step 3: Verify history still exists
        int finalHistoryCount = driver.findElement(By.id("historyList")).findElements(By.tagName("li")).size();
        Assert.assertEquals(finalHistoryCount, initialHistoryCount, "History was cleared during navigation.");
    }

    @Test(priority = 4, description = "TC_INT_004: Input Field Continuity")
    public void testInputFieldPersistence() {
        String testAmount = "750.50";
        WebElement amountField = driver.findElement(By.id("amount"));
        
        // Step 1: Enter amount
        amountField.sendKeys(testAmount);

        // Step 2: Navigate away and return
        driver.findElement(By.xpath("//li[contains(text(), 'About Us')]")).click();
        driver.findElement(By.xpath("//li[contains(text(), 'Converter')]")).click();

        // Step 3: Verify amount is still there
        String currentAmount = driver.findElement(By.id("amount")).getAttribute("value");
        Assert.assertEquals(currentAmount, testAmount, "Input field data was lost during navigation.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}