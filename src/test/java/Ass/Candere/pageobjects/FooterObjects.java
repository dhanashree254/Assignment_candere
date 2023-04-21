package Ass.Candere.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FooterObjects {
	private static final Logger logger = LogManager.getLogger(LandingPageObjects.class);

	// Section1: Declare a driver object
		private WebDriver driver;
		private WebDriverWait webDriverWait;
		
		//Section 2: Paratmerize the constuctor
		public FooterObjects(WebDriver driver, WebDriverWait webDriverWait)
		{
		this.driver = driver;
		this.webDriverWait = webDriverWait;
		}

		// Section 3: Define the locators
		// private String menu_item_text_element = "//button[@role='menuitem' and text()='%s']";
		private By elementAboutUs = By.xpath("//p[text()='ABOUT US']");
		
		
		//Section 4: Write Business Methods (Methods to be exposed) agent
		public void scrollDown() throws InterruptedException {
			
			WebElement AboutUs = driver.findElement(elementAboutUs);
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			Thread.sleep(2000);
			js.executeScript("arguments[0].scrollIntoView();", AboutUs);
			Thread.sleep(2000);
			logger.info("page scroll down till about us");
		}
}
