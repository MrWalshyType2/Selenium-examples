package com.qa.setup_02.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AcmeLoginPageWithPageFactory {
	
	private WebDriver driver;

	@FindBy(id = "username")
	private WebElement usernameInput;
	
	@FindBy(how = How.ID, using = "password")
	private WebElement passwordInput;
	
	@FindBy(id = "log-in")
	private WebElement signinBtn;
	
	public AcmeLoginPageWithPageFactory(WebDriver driver) {
		this.driver = driver;
		
		driver.get("https://demo.applitools.com/");
		
		// page load verification
		if (!driver.getTitle().equals("ACME demo app")) {
			throw new IllegalStateException("Page did not load");
		}
	}
	
	public AcmeHomePageWithPageFactory login(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		signinBtn.click();
		return PageFactory.initElements(driver, AcmeHomePageWithPageFactory.class);
	}
}
