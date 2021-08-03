package com.qa.selenium_intro_01;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.Arrays;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BingPuppiesSearch {

	@Test
	public void searchForPuppiesTest() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		driver.get("https://www.bing.com");
		
		WebElement searchBar = driver.findElement(By.name("q"));
		searchBar.sendKeys("puppies");
		searchBar.submit();
		
		assertEquals("puppies - Bing", driver.getTitle());
		
		driver.quit();
	}
}

