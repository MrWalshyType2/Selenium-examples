package com.qa.setup_02;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
	
	public static WebDriver getDriver() throws Exception {
		String webDriver = System.getProperty("browser", "chrome");
		
		switch (webDriver.toUpperCase()) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			return new ChromeDriver();
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
			return new FirefoxDriver();
		case "EDGE":
			System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/edgedriver.exe");
			return new EdgeDriver();
		default:
			throw new Exception("Fatal: No browser property supplied - Could not default to chromedriver, no driver available");
		}
	}
}
