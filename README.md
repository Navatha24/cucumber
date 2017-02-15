# Cucumber - Citrus Test Framework ![Logo][2] ![Logo][1] #



## System Under Test (SUT) - Thomas Bayer Sample application ##

The Thomas Bayer is a very simple sample application that represents the SUT for this Cucumber-Citrus test framework. This application provides REST API for accessing below resources using HTTP GET verb.

* CUSTOMERList
* INVOICEList
* ITEMList
* PRODUCTList

|base URL| HTTP verb|URI path| Description|
|:-------|:---------|:-------|:-----------|
|http://www.thomas-bayer.com/sqlrest/|GET|CUSTOMER/|Retreives list of all customers|
|http://www.thomas-bayer.com/sqlrest/|GET|PRODUCT/|Retreives list of all products|

## Getting started ##


### Cucumber JUnit Test Runner ###

Run tests using Cucumber Junit Test Runner class in the same way you run junit tests.


    @RunWith(Cucumber.class)
    @CucumberOptions(features = "src/test/resources/stories", glue = "stepdefinitions", snippets = SnippetType.CAMELCASE,
        plugin = { "com.consol.citrus.cucumber.CitrusReporter","pretty", "html:target/Cucumber" , "json:target/cucumber.json"} )
    public class RunCukeTest {
	
		
    }

### Behaviour Driven Development (BDD) Specifications ###

BDD Specifications are described using Gherkin syntax.

    @customers
    Feature: Retrieve information about Thomas-Bayer Customers 
    As manager of Thomas-Bayer
    I want to get all details of my customers
    so that I can provide updated information about my company services to all my customers

    Scenario Outline: Find customer details by customer id 
    Given a Thomas-Bayer customer with ID "<ID>"
    When I request details about the customer with above id 
    Then I can get customers "<ID>""<FIRSTNAME>""<LASTNAME>""<STREET>""<CITY>" 
    Examples: 
    |ID|FIRSTNAME|LASTNAME|STREET|CITY|
    |0|Laura|Steel|429 Seventh Av.|Dallas|
    |1|Susanne|King|366 - 20th Ave.|Olten|
		

        
The steps executed are defined in a separate class where a Citrus test designer is used to build integration test logic.

    @ContextConfiguration(classes = CitrusSpringConfig.class)
    public class CustomerSteps {
    
    @CitrusResource
	private TestDesigner designer;

	@Autowired
	private HttpClient thomasBayerCustomers;
	
	private String customerId;
    
    @Given("^Thomas-Bayer customer with ID '(\\d+)'$")
	public void thomasBayerCustomerWithID(int id) throws Throwable {
		customersId = id;
	}

	@When("^I request details about the customer with above id in XML format$")
	public void iRequestDetailsAboutTheCustomerWithAboveIdInXMLFormat() throws Throwable {
		designer.
		http().
		client(thomasBayerCustomers).
		send().
		get("/" + customersId);
	}

	@Then("^I should receive:$")
	public void iShouldReceive(String xml) throws Throwable {
		designer.
		http().
		client(thomasBayerCustomers).
		receive().
		response(HttpStatus.OK).
		contentType("application/xml").
		payload(xml);
	}
       
        [...]
    }    
    
Spring **@Autowired** annotations are used in order to enable dependency injection. The **CitrusSpringConfig**
class is loaded as Spring context configuration in order to load the Citrus default Spring application context.   

## Cucumber - Citrus Configuration ##

This test framework uses cucumber spring support for all tests. Three steps are required to make citrus work with cucumber spring features.

***Cucumber-Spring maven dependency is required for the project***
 
    <dependency>
      <groupId>info.cukes</groupId>
      <artifactId>cucumber-spring</artifactId>
      <version>${cucumber.version}</version>
      <scope>test</scope>
    </dependency>
     
***Citrus Spring object factory is enabled in 'cucumber.properties' to make Citrus work with Cucumber Spring support in all tests.***

    
    cucumber.api.java.ObjectFactory=cucumber.runtime.java.spring.CitrusSpringObjectFactory
    
The object factory injects *@CitrusResource* annotated fields in step classes and for creating all step definition instances.

***Citrus component (i.e HTTP component to call the application REST API) are defined in Citrus Spring configuration file citrus-context.xml that is automatically loaded within the object factory***

    <citrus-http:client id="thomasBayerCustomers"
                          request-url="http://www.thomas-bayer.com/sqlrest/CUSTOMER"/>


## Run Cucumber-Citrus tests using maven build tool ##
    

Execute all Cucumber-Citrus tests using maven by calling

     mvn install or mvn test

Execute subset of scenarios tagged with @customers by calling

     mvn test -Dcucumber.options="--tags @customers"

For invoking cukedoctor to publish reports

     mvn cukedoctor:execute

## BDD Living Documentation ##
This framework generates HTML and JSON reports in "**${project.basedir}/target**" folder that can be published and made available in Jenkins

### Jenkin Plugins for publishing reports###
* Cucumber Reports:https://wiki.jenkins-ci.org/display/JENKINS/Cucumber+Reports+Plugin
* Cukedoctor Reports:https://wiki.jenkins-ci.org/display/JENKINS/Cucumber+Living+Documentation+Plugin


Further information
-------------------
For more information on Cucumber-Citrus Test Framework see:
[1]:http://www.citrusframework.org/img/brand-logo.png "Citrus"
[2]:http://keekdageek.com/assets/cucumber_logo.png "Cucumber"

http://www.citrusframework.org

https://cucumber.io/

http://www.citrusframework.org/reference/html/cucumber.html

https://github.com/rmpestano/cukedoctor

https://wiki.jenkins-ci.org/display/JENKINS/Cucumber+Living+Documentation+Plugin