package com.qa.setup_02;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.setup_02.pages.AcmeHomePageNoPageFactory;
import com.qa.setup_02.pages.AcmeLoginPageNoPageFactory;
import com.qa.setup_02.pages.AcmeLoginPageWithPageFactory;
import com.qa.setup_02.test_utilities.ScreenshotManager;

public class PomTestExamples {

	private WebDriver driver;
	private ScreenshotManager screenshotManager;
	
	private AcmeLoginPageNoPageFactory acmeLoginPageNoPageFactory;
	private AcmeLoginPageWithPageFactory acmeLoginPageWithPageFactory;

	@Before
	public void setup() throws Exception {
		driver = WebDriverFactory.getDriver();
		screenshotManager = new ScreenshotManager();
		
		acmeLoginPageNoPageFactory = new AcmeLoginPageNoPageFactory(driver);
		
		acmeLoginPageWithPageFactory = PageFactory.initElements(driver, AcmeLoginPageWithPageFactory.class);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	}
	
//	@Test
	public void acmeLoginTest() throws IOException {
		String expectedUsername = "Jack Gomez";
		String actualUsername = acmeLoginPageNoPageFactory.login("Jack", "password")
													      .getLoggedInUsername();
		screenshotManager.takeAndSaveScreenshot(driver, "./login-result.png");
		assertEquals(expectedUsername, actualUsername);
	}
	
	@Test
	public void acmeLoginTestWithPageFactory() throws IOException {
		String expectedUsername = "Jack Gomez";
		String actualUsername = acmeLoginPageWithPageFactory.login("Jack", "password")
													        .getLoggedInUsername();
		screenshotManager.takeAndSaveScreenshot(driver, "./login-result.png");
		assertEquals(expectedUsername, actualUsername);
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
}
