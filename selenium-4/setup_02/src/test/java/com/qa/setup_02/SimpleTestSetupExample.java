package com.qa.setup_02;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.setup_02.test_utilities.ScreenshotManager;

public class SimpleTestSetupExample {
	
	private WebDriver driver;
	private ScreenshotManager screenshotManager;

	@Before
	public void setup() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

		driver = WebDriverFactory.getDriver();
		screenshotManager = new ScreenshotManager();

		driver.manage().window().setSize(new Dimension(1366, 768));

		Timeouts timeouts = driver.manage().timeouts();
		timeouts.implicitlyWait(Duration.ofSeconds(2));

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	}

//	@Test
	public void oldStyleNavigationTest() throws InterruptedException {
		String originalTab = driver.getWindowHandle();
		driver.get("https://www.bing.com");

		((JavascriptExecutor) driver).executeScript("window.open()");
		String newTab = driver.getWindowHandle();

		driver.switchTo().window(originalTab);

		driver.close();

		Thread.sleep(3000);
	}

//	@Test
	public void newWindowApiNavigationTest() throws InterruptedException {
		String originalTab = driver.getWindowHandle();
		driver.get("https://www.bing.com");

		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://www.google.com");
		driver.close();

		driver.switchTo().window(originalTab);
		driver.get("https://google.com");

		Thread.sleep(3000);
	}

//	@Test
	public void takesScreenshotTest() throws IOException {
		driver.navigate().to(new URL("https", "bing.com", 443, ""));
		screenshotManager.takeAndSaveScreenshot(driver, "./src/test/resources/screenshot.png");
		screenshotManager.takeAndSaveElementScreenshot(driver, By.name("q"),
				"./src/test/resources/element-screenshot.png");
	}

//	@Test
	public void interactingWithElements() throws InterruptedException {
		driver.get("https://www.bing.com");

		By searchBarSelector = By.name("q");
		WebElement searchBar = driver.findElement(searchBarSelector);

		searchBar.sendKeys(Keys.chord(Keys.SHIFT, "puppies"));
		searchBar.sendKeys(" and kittens");

		searchBar.submit();

		Thread.sleep(5000);
	}

	// disable implicit waits before running this example
//	@Test
	public void explicitWaitExampleTest() throws InterruptedException {
		driver.get("https://demo.applitools.com/");

		WebElement usernameField = new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username"))));

		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement signInBtn = driver.findElement(By.id("log-in"));

		usernameField.sendKeys("Jack");
		passwordField.sendKeys("password");
		signInBtn.click();

		WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(webDriver -> webDriver.findElement(By.className("logged-user-name")));
		assertEquals(element.getText(), "Jack Gomez");
	}

//	@Test
	public void fluentWaitExampleTest() {
		driver.get("https://www.bing.com");

		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

//		WebElement searchBar = fluentWait.until(new Function<WebDriver, WebElement>() {
//			
//			@Override
//			public WebElement apply(WebDriver driver) {
//				return driver.findElement(By.name("q"));
//			}
//			
//		});

//		WebElement searchBar = fluentWait.until((WebDriver) -> driver.findElement(By.name("q")));

		WebElement searchBar = fluentWait.until(ExpectedConditions.elementToBeClickable(By.name("q")));

		searchBar.sendKeys("puppies and kittens");

		searchBar.submit();
	}

//	@Test
	public void actionsExampleTest() {
		driver.get("https://www.bing.com");
		WebElement searchBar = driver.findElement(By.name("q"));
		WebElement searchIcon = driver.findElement(By.id("search_icon"));

		Actions searchAction = new Actions(driver);
				
		searchAction.click(searchBar)
				    .sendKeys("puppies and kittens")
				    .click(searchIcon);
		
		searchAction.perform();		
		
		assertEquals("puppies and kittens - Bing", driver.getTitle());
	}
	
//	@Test
	public void withoutPomExample() {
		driver.get("https://demo.applitools.com/");

		WebElement usernameField = driver.findElement(By.id("username"));

		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement signInBtn = driver.findElement(By.id("log-in"));

		usernameField.sendKeys("Jack");
		passwordField.sendKeys("password");
		signInBtn.click();

		WebElement element = driver.findElement(By.className("logged-user-name"));
		assertEquals(element.getText(), "Jack Gomez");
	}

	@After
	public void teardown() {
		driver.quit();
	}
}
