package Driver;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import utilitiy.ReadProperties;

public class Driver {
public	static WebDriver  driver;
	public static WebDriver getDriver() throws IOException {
		if(ReadProperties.Read("Browser").equalsIgnoreCase("Chrome"))
		driver= new ChromeDriver();
		else if(ReadProperties.Read("Browser").equalsIgnoreCase("Edge"))
			driver= new EdgeDriver();
		else {
			System.out.println("Invalid Browser");
			
		}
		return driver;
	}

public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;   // ✅ THIS FIXES YOUR ERROR
        }
    }

}
