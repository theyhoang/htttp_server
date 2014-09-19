Feature: Support for forms: Post, Get, Put

Scenario: Get on the form
	When I make a "GET" request to "/form" endpoint
	Then I should get a "200" response code
	And The response body contains "data = cosby"
	
Scenario: Post on the form
	Given I have enter the form data "data = cosby"
	When I make a "POST" request to "/form" endpoint
	Then I should get a "200" response code

	When I make a "GET" request to "/form" endpoint
	Then I should get a "200" response code
	And The response body contains "data = cosby"
	
Scenario: Post on the form
	Given I have enter the form data "data = heathcliff"
	When I make a "POST" request to "/form" endpoint
	Then I should get a "200" response code

	When I make a "GET" request to "/form" endpoint
	Then I should get a "200" response code
	And The response body contains "data = heathcliff"
	
	When I make a "GET" request to "/form" endpoint
	Then I should get a "200" response code
	And The response body contains "data = heathcliff"
	
Scenario: Put data to the form
	Given I have enter the form data ""My"="Data""
	When I make a "PUT" request to "/form" endpoint
	Then I should get a "200" response code