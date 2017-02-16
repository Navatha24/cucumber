@customers
Feature: Retrieve information about Thomas-Bayer Customers 
As manager of Thomas-Bayer
I want to get all details of my customers
so that I can provide updated information about my company services to all my customers

Scenario: Retrieve list of all Thomas-Bayer Customers 
	Given I have Thomas-Bayer customers url "http://www.thomas-bayer.com/sqlrest/CUSTOMER"
	When I send a GET request to the service with above url 
	Then I can retrieve list of all Thomas-Bayer customers 
	
Scenario: Retrieve list of all Thomas-Bayer Customers 
	Given I need to know the list of all Thomas-Bayer customers 
	When I request details about all existing customers 
	Then I can retrieve list of all Thomas-Bayer customers 
	
Scenario Outline: Find customer details by customer id 
	Given a Thomas-Bayer customer with ID "<ID>"
	When I request details about the customer with above id 
	Then I can get customers "<ID>""<FIRSTNAME>""<LASTNAME>""<STREET>""<CITY>" 
	Examples: 
		|ID|FIRSTNAME|LASTNAME|STREET|CITY|
		|0|Laura|Steel|429 Seventh Av.|Dallas|
		|1|Susanne|King|366 - 20th Ave.|Olten|
				
Scenario: Should return customer details in XML format 
	Given Thomas-Bayer customer with ID '0' 
	When I request details about the customer with above id in XML format 
	Then I should receive:
		"""
<?xml version="1.0"?>
<CUSTOMER xmlns:xlink="http://www.w3.org/1999/xlink">
    <ID>0</ID>
    <FIRSTNAME>Laura</FIRSTNAME>
    <LASTNAME>Steel</LASTNAME>
    <STREET>429 Seventh Av.</STREET>
    <CITY>Dallas</CITY>
</CUSTOMER>
"""
