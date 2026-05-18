
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AboutPage {
	@Test
    public void verifyAboutPageResponsiveness() {
        // 1. Initialization
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://currency-converter-six-tan.vercel.app/"); // Replace with your local path
            
            // Bypass Splash Screen
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("splash-screen")));

            // Navigate to About Us Page
            WebElement aboutMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(text(), 'About Us')]")));
            aboutMenu.click();

            // --- STEP 1: Desktop Resolution (1920px) ---
            driver.manage().window().setSize(new Dimension(1920, 1080));
            WebElement grid = driver.findElement(By.id("about-page"));
            System.out.println(grid.getSize().getWidth());
//            +driver.findElement(By.className("sidebar")).getSize().getWidth()
            // Check if the container is wide enough for multiple columns
            Assert.assertTrue(grid.getSize().getWidth() > 1200, "Desktop layout failed: Grid width too small.");
            System.out.println("Desktop (1920px) layout verified.");

            // --- STEP 2: Tablet Width (768px) ---
            driver.manage().window().setSize(new Dimension(768, 1024));
            // Small pause for CSS transition if any
            Thread.sleep(500); 
            
            int tabletWidth = grid.getSize().getWidth();
            Assert.assertTrue(tabletWidth <= 768 && tabletWidth > 400, "Tablet layout failed: Grid width out of bounds.");
            System.out.println("Tablet (768px) layout verified.");

            // --- STEP 3: Mobile Width (375px) ---
            driver.manage().window().setSize(new Dimension(375, 667));
            Thread.sleep(500);
            
            int mobileWidth = grid.getSize().getWidth();
            // Verify grid takes nearly full width of the viewport
            Assert.assertTrue(mobileWidth <= 375 && mobileWidth > 300, "Mobile layout failed: Grid width incorrect.");
            
            // Verify no horizontal overflow
            long scrollWidth = (long) ((JavascriptExecutor) driver).executeScript("return document.documentElement.scrollWidth");
            Assert.assertEquals(scrollWidth, 375, "Mobile layout failed: Horizontal scroll detected.");
            System.out.println("Mobile (375px) layout verified.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Cleanup
            driver.quit();
        }
    }
}