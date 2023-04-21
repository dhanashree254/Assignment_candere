package Ass.Candere.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDescPageObjects {
	private static final Logger logger = LogManager.getLogger(ProductDescPageObjects.class);
	
	// Section1: Declare a driver object
		private WebDriver driver;
		private WebDriverWait webDriverWait;
		
		//Section 2: Paratmerize the constuctor
		public ProductDescPageObjects(WebDriver driver, WebDriverWait webDriverWait)
		{
		this.driver = driver;
		this.webDriverWait = webDriverWait;
		}

		// Section 3: Define the locators
		// private String menu_item_text_element = "//button[@role='menuitem' and text()='%s']";
		private By sizeDropDownElement = By.xpath("//select[@id='mt_size']");
		private By selectedSizeElement = By.xpath("//select[@id='mt_size']/option[@selected='selected']");
		
		//Section 4: Write Business Methods (Methods to be exposed) agent
		public void sizeOfProductAndValidation(String prodsize) throws InterruptedException
		{
			WebElement sizeDropDown = webDriverWait.until(ExpectedConditions.elementToBeClickable(sizeDropDownElement));
			Select dropDown = new Select(sizeDropDown);
			logger.info("Select Dropdown for product size");
			dropDown.selectByVisibleText(prodsize);
			logger.info("Size is selected as ->" + prodsize);
			Thread.sleep(1000);
			WebElement selectedSize = webDriverWait.until(ExpectedConditions.elementToBeClickable(selectedSizeElement));
			Assert.assertEquals(prodsize, selectedSize.getText());
			logger.info("Assertion for User select size of product and validation is passed with expected");
		}
		
		public void priceUpdateTextDisplayed(String priceUpdated) throws InterruptedException {
			WebElement displayedText = driver.findElement(By.xpath("//div[text()='Price updated']"));
//			WebElement displayedText = webDriverWait.until(ExpectedConditions.elementToBeClickable((By.xpath("//div[text()='Price updated']"))));
			Thread.sleep(1000);
			Assert.assertEquals(priceUpdated, displayedText.getText());
			logger.info("Assertion for Displayed with text as "+ priceUpdated +" is passed with expected as ->" + priceUpdated + "and actual as ->" + displayedText.getText());
		}
}
