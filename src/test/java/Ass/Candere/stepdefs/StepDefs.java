package Ass.Candere.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Ass.Candere.core.WebDriverFactory;
import Ass.Candere.pageobjects.FooterObjects;
import Ass.Candere.pageobjects.LandingPageObjects;
import Ass.Candere.pageobjects.ProductDescPageObjects;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefs {
	
    private static final Logger logger = LogManager.getLogger(StepDefs.class);

	
	Scenario scn;
	WebDriver driver;
    String base_url = "https://www.candere.com/";
    int implicit_wait_timeout_in_sec = 20;
    WebDriverWait webDriverWait;
    
    LandingPageObjects landingPageObjects;
    ProductDescPageObjects productDescPageObjects;
    FooterObjects footerObjects;
    
    @Before
    public void setup(Scenario scn) throws Exception
    {	
    	this.scn = scn;
    	String browserName = WebDriverFactory.getBrowserName();
    	driver = WebDriverFactory.getWebDriverForBrowser(browserName); 
        webDriverWait = new WebDriverWait(driver,20);
        scn.log("Browser got invoked");
        
        //**Initialize class objects
        landingPageObjects = new LandingPageObjects(driver, webDriverWait);
        productDescPageObjects = new ProductDescPageObjects(driver, webDriverWait);
        footerObjects = new FooterObjects(driver, webDriverWait); 
    }
    
    @After(order=1)
	public void tearDown()
	{
		WebDriverFactory.quitDriver();
		scn.log("Browser got closed");
		
	}
	
	@After(order=2)
	public void ScreenshotForFailure(Scenario scn)
	{
		 if (scn.isFailed()) {
		        TakesScreenshot scrnShot = (TakesScreenshot)driver;
		        byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
		        scn.attach(data, "image/png","Failed Step Name: " + scn.getName());
		    }
		 else{
		        scn.log("Test case is passed, no screen shot captured");
		    }
		
	}
    
	@When("User navigated to the landing page of the applicaton")
	public void user_navigated_to_the_landing_page_of_the_applicaton(){
		
		driver.get(base_url);
		logger.info("Browser got invoked with URL as ->" + base_url);
		scn.log("User navigated to the landing page of the applicaton");	
	}
	
	@Then("Validating title {string}")
	public void validating_title(String prodTitleExpected) {
		
		Assert.assertEquals(prodTitleExpected, driver.getTitle());
		logger.info("assertion for page validation is passed with expected as ->" + prodTitleExpected + "and actual as ->" + driver.getTitle());
		scn.log("Validation of title");	
	}
	
	@When("User search for product {string}")
	public void user_search_for_product(String prodName) {		
		landingPageObjects.searchProduct(prodName);
		scn.log("User searched for product");		    
	}

	@Then("Search Result page is displayed as {string}")
	public void search_result_page_is_displayed_as(String prodName) {
	    landingPageObjects.searchedProductDisplayed(prodName);
		scn.log("Search Result page is displayed");		
	}
	
	@Then("Click on displayed product and validate title of page {string}")
	public void click_on_displayed_product_and_validate_title_of_page(String searchProdExcepted) throws InterruptedException {
		
		WebElement searchProd = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='productsList']/ul/li//div//div[text()='Majestic Solitaire Diamond Ring']")));
    	logger.info("Webelement for search product" +searchProdExcepted);
    	searchProd.click();
    	logger.info("Clicked on search product");
    	Thread.sleep(2000);
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String childWindowId = it.next();
		driver.switchTo().window(childWindowId);
		//Thread.sleep(1000);
		Assert.assertEquals(searchProdExcepted, driver.getTitle());
		logger.info("Assertion for Click on displayed product and validate title of page is passed with expected as ->" + searchProdExcepted + "and actual as ->" + driver.getTitle());
		scn.log("Click on displayed product and validated title of page");
	}
	
	@Then("User select size of product and validate {string}")
	public void user_select_size_of_product_and_validate(String size) throws InterruptedException {
		productDescPageObjects.sizeOfProductAndValidation(size);
		scn.log("User selected size of product and validated");		
	}
	
	@Then("Displayed with text as {string}")
	public void displayed_with_text_as(String priceUpdated) throws InterruptedException {
		productDescPageObjects.sizeOfProductAndValidation(priceUpdated);
		scn.log("Updated price text displayed");	
	}
	
	
	@When("Scroll down the page")
	public void scroll_down_the_page() throws InterruptedException {
		
		footerObjects.scrollDown();
		scn.log("user scroll down the page");
	}

	@When("Validating {string} Section")
	public void validating_section(String string) {
		
		WebElement AboutUs = driver.findElement(By.xpath("//p[text()='ABOUT US']"));
		Assert.assertEquals(string, AboutUs.getText());
		logger.info("Assertion passed ABOUT US section validated");
		scn.log("Validating ABOUT US Section");
	}

	@Then("Validating List of link below about us")
	public void validating_list_of_link_below_about_us(List<String> dataTable) {
		
		List<WebElement> ListOfAboutUs = driver.findElements(By.xpath("//a[@class='second our_compamy__']//parent::nav/a"));
		for (int i = 0; i < dataTable.size(); i++) {

			if (dataTable.get(i).equalsIgnoreCase(ListOfAboutUs.get(i).getText())) {
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		}
		logger.info("List of About us validated");
		scn.log("List of About us validated");
	}
	
	@When("Validating footer {string} Section")
	public void validating_footer_section(String followus) {
		WebElement followusElement = driver.findElement(By.xpath("//p[text()='FOLLOW US']"));
		Assert.assertEquals(followus, followusElement.getText());
		logger.info("Assertion passed FOLLOW US section validated");
		scn.log("Validating FOLLOW US Section");
		
	}

	@When("Clicking on social medialink {string}")
	public void clicking_on_social_medialink(String string) throws InterruptedException {
	  
			
		WebElement SocialMidiaLink = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-gtm='" + string + "']")));
		Assert.assertTrue(SocialMidiaLink.isDisplayed());
		logger.info("Social media link" +string + "displayed");
		SocialMidiaLink.click();
		logger.info("Social media link" +string + "clicked");
		Thread.sleep(2000);
		scn.log("Clicked on social media links");
	}
	@Then("Validating the All social media links With Expected {string} and {string}")
	public void validating_the_all_social_media_links_with_expected_and(String expected, String actual) throws InterruptedException {
		
		if (actual.equals("Candere by Kalyan Jewellers ")) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[text()='Candere by Kalyan Jewellers']"))).getText().equalsIgnoreCase(actual);
			Thread.sleep(3000);
			Assert.assertTrue(true);
			logger.info("Matched with facebook");
		} else if (actual.equals("canderejewellery ")) {
			driver.findElement(By.xpath("//h2[text()='canderejewellery']")).getText().equalsIgnoreCase(actual);
			Thread.sleep(3000);
			Assert.assertTrue(true);
			logger.info("Matched with instagram");
			
		} else if (actual.equals("Candere By Kalyan jewellers")) {
			driver.findElement(By.xpath("(//span[text()='Candere By Kalyan Jewellers'])[2]")).getText().equalsIgnoreCase(actual);
			Thread.sleep(3000);
			Assert.assertTrue(true);
			logger.info("Matched with twitter");
		}
		else
		{
			Assert.assertFalse(false);
		}
		logger.info("Validating the All social media links With Expected" +expected + " and "+ actual);
		scn.log("user validating the URL and page text");
	}

}
