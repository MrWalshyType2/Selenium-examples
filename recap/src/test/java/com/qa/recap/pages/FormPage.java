package com.qa.recap.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormPage {

	final static String URL = "https://demoqa.com/automation-practice-form";
	
	private WebDriver driver;
	
	@FindBy(id = "firstName")
	private WebElement firstName;
	
	@FindBy(id = "lastName")
	private WebElement lastName;

	@FindBy(id = "userEmail")
	private WebElement userEmail;
	
	// selecting labels as inputs are borked
	// id = "gender-radio-1"
	@FindBy(css = "[for=gender-radio-1]")
	private WebElement male;
	
	@FindBy(css = "[for=gender-radio-2]")
	private WebElement female;
	
	@FindBy(css = "[for=gender-radio-3]")
	private WebElement other;
	
	@FindBy(id = "userNumber")
	private WebElement userNumber;
	
	@FindBy(id="dateOfBirthInput")
	private WebElement dateOfBirthInput;
	
	@FindBy(id = "submit")
	private WebElement submit;
	
	@FindBy(css = "body > div.fade.modal.show > div > div > div.modal-body > div > table")
	private WebElement resultTable;
	
	public FormPage(WebDriver driver) throws Exception {
		driver.get(URL);
		
		if (!driver.getTitle().equals("ToolsQA")) throw new IllegalStateException("Site not found");
	}
	
	public FormPage name(String forename, String surname) {
		firstName.sendKeys(forename);
		lastName.sendKeys(surname);
		
		return this;
	}
	
	public FormPage email(String email) {
		userEmail.sendKeys(email);
		
		return this;
	}
	
	public FormPage gender(String gender) {
		switch (gender.toUpperCase()) {
		case "MALE": 
			male.click();
			break;
		case "FEMALE": 
			female.click();
			break;
		case "OTHER": 
			other.click();
			break;
		}
		
		return this;
	}
	
	public FormPage phoneNumber(String phoneNumber) {
		userNumber.sendKeys(phoneNumber);
		
		return this;
	}
	
	public FormPage dateOfBirthInput(String dateOfBirth) {
		for (int i = 0; i < 11; i++) {
			// website breaks when input field is empty
			dateOfBirthInput.sendKeys(Keys.BACK_SPACE);
		}
		
		dateOfBirthInput.sendKeys(dateOfBirth);
		
		return this;
	}
	
	public FormPage submit() {
		submit.click();
		
		return this;
	}
	
	public List<String> resultsFormValues() {
		List<String> formValues = new ArrayList<String>();
		List<WebElement> tableRows = resultTable.findElement(By.tagName("tbody"))
												.findElements(By.tagName("tr"));
		
		for (WebElement row : tableRows) {
			List<WebElement> rowData = row.findElements(By.tagName("td"));
			
			if (!rowData.get(0).getText().equals("Date of Birth")) {
				String data = rowData.get(1).getText();
				
				if (!data.isEmpty()) formValues.add(data);
			}
		}
		
		return formValues;
	}
}
