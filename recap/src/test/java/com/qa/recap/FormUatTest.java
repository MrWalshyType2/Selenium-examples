package com.qa.recap;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.qa.recap.pages.FormPage;

// Given a signup form, 
// When the user enters their details
// And the user submits the form
// Then the user should see a table with their details in it

public class FormUatTest {

	private JavascriptExecutor executor;
	private WebDriver driver;
	private FormPage formPage;

	private String forename;
	private String surname;
	private String email;
	private String gender;
	private String phoneNumber;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		executor = (JavascriptExecutor) driver;

		Window window = driver.manage().window();
		window.maximize();

		Timeouts timeouts = driver.manage().timeouts();
		timeouts.implicitlyWait(Duration.ofSeconds(5)); // max time for DOM polling for an element that we have
														// requested
		timeouts.pageLoadTimeout(Duration.ofSeconds(15));

//		formPage = PageFactory.initElements(driver, FormPage.class);
	}

//	@Test
	public void formTest() throws InterruptedException {
		Thread.sleep(2000);

		forename = "Bob";
		surname = "Derby";
		email = "bob@email.com";
		gender = "Male";
		phoneNumber = "1234567890";

		formPage.name(forename, surname).email(email).gender(gender).phoneNumber(phoneNumber);

		// if we don't scroll, the ad covers the button
		executor.executeScript("window.scrollBy(0, window.innerHeight)");

		formPage.submit();
		List<String> expectedResult = List.of(forename + " " + surname, email, gender, phoneNumber);
		List<String> actualResult = formPage.resultsFormValues();
		// Compare the actual values with the expected values
		// - we need the actual values
		// - we need the expected values
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void dragDropTest() throws InterruptedException {
		Thread.sleep(2000);
		driver.get("http://way2automation.com/way2auto_jquery/droppable.php");
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src='droppable/default.html']")));
		
		WebElement draggable = driver.findElement(By.id("draggable"));
		WebElement droppable = driver.findElement(By.id("droppable"));
		
		Actions actions = new Actions(driver);
		actions.dragAndDrop(draggable, droppable);
		
		actions.build()
			   .perform();
		
		driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
	}

	@After
	public void teardown() {
		driver.quit();
	}
}
