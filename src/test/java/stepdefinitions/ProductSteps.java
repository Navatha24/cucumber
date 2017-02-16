package stepdefinitions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
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
	private HttpClient thomasBayerProducts;
	
	private String productID;
	
	@Given("^a Thomas-Bayer product with ID \"([^\"]*)\"$")
	public void aThomasBayerProductWithID(String ID) throws Throwable {
	    productID=ID;
	}

	@When("^I request details about the product with above id$")
	public void iRequestDetailsAboutTheProductWithAboveId() throws Throwable {
		designer
		.http()
		.client(thomasBayerProducts)
		.send()
		.get(productID);
	}

	@Then("^I can get product \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
	public void iCanGetProduct(String ID, String NAME, String PRICE) throws Throwable {
		
		productVariables(ID, NAME, PRICE);
		
		designer
		.http()
		.client(thomasBayerProducts)
		.receive()
		.response(HttpStatus.OK)
		.contentType("application/xml")
		.payload(new ClassPathResource("responses/response3.xml"));
	  
	}

	private void productVariables(String ID, String NAME, String PRICE) {
		designer.variable("id", ID);
		designer.createVariable("name", NAME);
		designer.createVariable("price", PRICE);
	}

}
