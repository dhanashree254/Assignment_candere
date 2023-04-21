package Ass.Candere.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LandingPageObjects {

	private static final Logger logger = LogManager.getLogger(LandingPageObjects.class);

	// Section1: Declare a driver object
	private WebDriver driver;
	private WebDriverWait webDriverWait;
	
	//Section 2: Paratmerize the constuctor
	public LandingPageObjects(WebDriver driver, WebDriverWait webDriverWait)
	{
	this.driver = driver;
	this.webDriverWait = webDriverWait;
	}

	// Section 3: Define the locators
	// private String menu_item_text_element = "//button[@role='menuitem' and text()='%s']";
	private By searchBoxElement = By.id("search");
	private By searchedProduct = By.xpath("//div[text()='Majestic Solitaire Diamond Ring']");
	
	//Section 4: Write Business Methods (Methods to be exposed) agent
	
	 public void searchProduct(String prodName)
	 {	 
			//webDriverWait = new WebDriverWait(driver,20);
			//logger.info("webDriverWait time out set to ->" + 20);
			webDriverWait.until(ExpectedConditions.elementToBeClickable(searchBoxElement));
			logger.info("Waiting for webelement -> elementSearchBox to be clickable");	
			driver.findElement(searchBoxElement).sendKeys(prodName);
			logger.info("sending keys into webelement -> " + prodName);
	 }
	 public void searchedProductDisplayed(String prodName) {
			WebElement searchedProd = webDriverWait.until(ExpectedConditions.elementToBeClickable(searchedProduct));
			logger.info("Webelement for search result" + prodName);		
			Assert.assertEquals(prodName, searchedProd.getText());
			logger.info("Assertion for Search Result page is displayed is passed with expected as ->"+prodName+ "and actual as ->" + searchedProd.getText());
	 }
	 

}
