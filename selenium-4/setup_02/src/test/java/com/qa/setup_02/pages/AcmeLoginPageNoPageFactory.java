package com.qa.setup_02.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AcmeLoginPageNoPageFactory {

	private WebDriver driver;
	
	private By usernameBy = By.id("username");
	private By passwordBy = By.id("password");
	private By signinBy = By.id("log-in");
	
	public AcmeLoginPageNoPageFactory(WebDriver driver) {
		this.driver = driver;
		
		driver.get("https://demo.applitools.com/");
		
		if (!driver.getTitle().equals("ACME demo app")) {
			throw new IllegalStateException("Page did not load");
		}
	}
	
	public AcmeHomePageNoPageFactory login(String username, String password) {
		driver.findElement(usernameBy).sendKeys(username);
		driver.findElement(passwordBy).sendKeys(password);
		driver.findElement(signinBy).click();
		return new AcmeHomePageNoPageFactory(driver);
	}
}
