package com.qa.setup_02.test_utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScreenshotManager {
	
	private File currentScreenshot;

	public void takeScreenshot(WebDriver driver) {
		currentScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}
	
	public void takeElementScreenshot(WebDriver driver, By selector) {
		WebElement element = driver.findElement(selector);
		currentScreenshot = element.getScreenshotAs(OutputType.FILE);
	}
	
	/**
	 * Saves the current screenshot in the File object to the specified path, last section of the path is the filename.
	 * 
	 * "./images/test-image.png" would be a valid path as a String
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void saveScreenshot(String path) throws IOException {
		currentScreenshot.renameTo(new File(path));
		currentScreenshot.createNewFile();
	}
	
	public void takeAndSaveScreenshot(WebDriver driver, String path) throws IOException {
		takeScreenshot(driver);
		saveScreenshot(path);
	}
	
	public void takeAndSaveElementScreenshot(WebDriver driver, By selector, String path) throws IOException {
		takeElementScreenshot(driver, selector);
		saveScreenshot(path);
	}
}
