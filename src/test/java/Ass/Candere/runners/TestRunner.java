package Ass.Candere.runners;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features ="classpath:features",
		glue="Ass.Candere.stepdefs",
		tags="@Requirement3",
		plugin = {"pretty",
	            "html:target/html/htmlreport.html",
	            "json:target/json/file.json",
	            },
			monochrome = true,
	        publish=true,
	        dryRun=false
	        
	)        
public class TestRunner {

}
//mvn clean verify -Dcucumber.filter.tags=”@Requirement”
//mvn clean verify -Dcucumber.filter.tags=”@Requirement” -Dbrowser=”headless”
//mvn clean verify -Dcucumber.filter.tags=”@Requirement” -Dbrowser=”edge”
//mvn clean verify -Dcucumber.filter.tags=”@Requirement” -Dbrowser=”chrome”