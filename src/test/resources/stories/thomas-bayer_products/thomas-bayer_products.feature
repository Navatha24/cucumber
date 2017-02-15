@products
Feature: Retrieve information about Thomas-Bayer products 
As manager of Thomas-Bayer
I want to get all details of my products
so that I can provide updated information about my company products to all my customers

 You can use *asciidoc markup* in _feature_ #description#.

   NOTE: This is a very important feature!

    #{IMPORTANT: Asciidoc markup inside *steps* must be surrounded by *curly brackets*.}
    # {NOTE: Steps comments are placed *before* each steps so this comment is for the *WHEN* step.}


Scenario Outline: Find customer details by customer id 
	Given a Thomas-Bayer product with ID "<ID>"
	When I request details about the product with above id 
	Then I can get product "<ID>""<NAME>""<PRICE>"
	Examples: 
		|ID|NAME|PRICE|
		|0|Iron Iron|5.4|
		|1|Chair Shoe|24.8|

		
