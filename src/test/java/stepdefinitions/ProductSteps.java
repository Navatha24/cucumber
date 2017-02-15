package stepdefinitions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.config.CitrusSpringConfig;
import com.consol.citrus.dsl.design.TestDesigner;
import com.consol.citrus.http.client.HttpClient;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@ContextConfiguration(classes = CitrusSpringConfig.class)
public class ProductSteps {
	
	@CitrusResource
	private TestDesigner designer;

	@Autowired
	private HttpClient thomasBayerCustomers;
	
	@Given("^a Thomas-Bayer product with ID \"([^\"]*)\"$")
	public void aThomasBayerProductWithID(String arg1) throws Throwable {
	    
	}

	@When("^I request details about the product with above id$")
	public void iRequestDetailsAboutTheProductWithAboveId() throws Throwable {
	   
	}

	@Then("^I can get product \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
	public void iCanGetProduct(String arg1, String arg2, String arg3) throws Throwable {
	  
	}

}
