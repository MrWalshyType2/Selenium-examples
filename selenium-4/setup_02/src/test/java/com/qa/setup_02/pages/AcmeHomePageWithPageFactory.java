package com.qa.setup_02.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AcmeHomePageWithPageFactory {

	private WebDriver driver;
	
	@FindBy(className = "logged-user-name")
	private WebElement username;

	public AcmeHomePageWithPageFactory(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getLoggedInUsername() {
		return username.getText();
	}
}
