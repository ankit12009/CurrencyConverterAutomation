import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class CurrencyConverterTest1 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Set wait for splash screen and transitions
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://currency-converter-six-tan.vercel.app/"); // Update with actual path
        
        // Wait for Splash screen to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("splash-screen")));
    }

    @Test(priority = 1, description = "TC_001: Verify About Page Flip Animation")
    public void testAboutPageFlip() {
        // 1. Initialization
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions actions = new Actions(driver);

        try {
            
            // 2. Handle Splash Screen
            // Wait until the splash screen is hidden before interacting with the menu
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("splash-screen")));

            // 3. Navigation
            // Locate the "About Us" list item and click it
            WebElement aboutMenuLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(text(), 'About Us')]")));
            aboutMenuLink.click();

            // 4. Grid Visibility Validation
            // Ensure the about-page section is displayed
            WebElement aboutSection = driver.findElement(By.id("about-page"));
            Assert.assertTrue(aboutSection.isDisplayed(), "About page section failed to display.");

            // 5. Card Interaction (The Flip)
            // Locating the first generated card within the team-grid
            WebElement firstCard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"team-grid\"]/div[1]/div/div[2]")));

            // Perform hover to trigger CSS flip animation
            actions.moveToElement(firstCard).perform();

            // 6. Verify "Performational" UI Change
            // Industry check: Verify the 'transform' style exists for the flip effect
            String transform = firstCard.getCssValue("transform");
            Assert.assertTrue(transform.contains("matrix"), "Flip animation was not triggered.");

            // 7. Verify Content Reveal
            // Check if the Member Quote (reverse side) becomes visible
            WebElement quote = firstCard.findElement(By.xpath("//*[@id=\"team-grid\"]/div[1]/div/div[2]/p")); 
            Assert.assertTrue(quote.isDisplayed(), "Member quote not visible after flip.");
            System.out.println("Test Passed: About Page Grid is responsive and interactive.");

        } finally {
            // 8. Cleanup
            driver.quit();
        }
    }

    

    @Test(priority = 2, description = "TC_003: Verify Dark/Light Mode Theme Adaptation")
    public void testThemeToggle() {
        WebElement toggleContainer = driver.findElement(By.id("themeToggleContainer"));
        //WebElement body = driver.findElement(By.tagName("body"));

        // Toggle to Light Mode (Initial is dark-mode based on your HTML class)
        toggleContainer.click();
        
        // Verification of class change or CSS property
//        boolean isDarkModeRemoved = wait.until(ExpectedConditions.not(
//            ExpectedConditions.attributeContains(body, "class", "dark-mode")));
//        System.out.println(isDarkModeRemoved);
//        Assert.assertTrue(isDarkModeRemoved, "Body should not have dark-mode class after toggle");
        
        String themeLabel = driver.findElement(By.id("themeLabel")).getText();
        Assert.assertEquals(themeLabel, "Light Mode", "Label did not update correctly");
    }

    @Test(priority = 3, description = "TC_004: Verify Data Integrity (Conversion Logic)")
    public void testConversionFunctionality() {
        // 1. Enter Amount
        WebElement amountInput = driver.findElement(By.id("amount"));
        amountInput.sendKeys("100");

        // 2. Select Currencies
        // From: INR (Index 0), To: USD (Index 0 in its list)
        
        // 3. Click Convert
        driver.findElement(By.id("convertBtn")).click();

        // 4. Verify Result Display
        WebElement bigResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bigResult")));
        String resultText = bigResult.getText();
        
        Assert.assertFalse(resultText.isEmpty(), "Conversion result is empty");
        Assert.assertTrue(resultText.contains("USD") || resultText.matches(".*\\d.*"), 
            "Result does not contain numeric conversion value");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}