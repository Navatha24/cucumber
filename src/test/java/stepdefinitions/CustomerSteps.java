
package stepdefinitions;

import org.apache.log4j.Logger;
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
public class CustomerSteps {

	@CitrusResource
	private TestDesigner designer;

	@Autowired
	private HttpClient thomasBayerCustomers;

	private String URIPath;
	private String customerId;
	private int Id;
	private static Logger log = Logger.getLogger(CustomerSteps.class);

	@Given("^I have Thomas-Bayer customers url \"([^\"]*)\"$")
	public void iHaveThomasBayerCustomersUrl(String url) throws Throwable {
	   log.info("url:" +url);
	}

	@When("^I send a GET request to the service with above url$")
	public void iSendAGETRequestToTheServiceWithAboveUrl() throws Throwable {
		designer
		.http()
		.client(thomasBayerCustomers)
		.send()
		.get(URIPath);
	}

	@Then("^I can retrieve list of all Thomas-Bayer customers$")
	public void iCanRetrieveListOfAllThomasBayerCustomers() throws Throwable {
		designer
		.http()
		.client(thomasBayerCustomers)
		.receive()
		.response(HttpStatus.OK)
		.contentType("application/xml")
		.validateScript("assert root.children().size() > 45");
	}
	
	@Given("^a Thomas-Bayer customer with ID \"([^\"]*)\"$")
	public void aThomasBayerCustomerWithID(String id) throws Throwable {
		customerId = id;
	}

	@When("^I request details about the customer with above id$")
	public void iRequestDetailsAboutTheCustomerWithAboveId() throws Throwable {
		designer
		.http()
		.client(thomasBayerCustomers)
		.send()
		.get(customerId +"/");
	}

	@Then("^I can get customers \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
	public void iCanGetCustomers(String id, String firstname, String lastname, String street, String city)
			throws Throwable {
		
		customerVariables(id, firstname, lastname, street, city);
		
		designer
		.http()
		.client(thomasBayerCustomers)
		.receive()
		.response(HttpStatus.OK)
		.contentType("application/xml")
		.payload(new ClassPathResource("responses/response2.xml"));
		
	}
	

	@Given("^Thomas-Bayer customer with ID '(\\d+)'$")
	public void thomasBayerCustomerWithID(int id) throws Throwable {
		Id = id;
	}

	@When("^I request details about the customer with above id in XML format$")
	public void iRequestDetailsAboutTheCustomerWithAboveIdInXMLFormat() throws Throwable {
		designer.
		http().
		client(thomasBayerCustomers).
		send().get(""+Id);
	}
	
	@Then("^I should receive:$")
	public void iShouldReceive(String xml) throws Throwable {
		designer.http().client(thomasBayerCustomers).receive().response(HttpStatus.OK).contentType("application/xml")
				.payload(xml);
	}
	

	private void customerVariables(String id, String firstname, String lastname, String street, String city) {
		designer.variable("id", id);
		designer.createVariable("firstname", firstname);
		designer.createVariable("lastname", lastname);
		designer.createVariable("street", street);
		designer.createVariable("city", city);
	}

}
