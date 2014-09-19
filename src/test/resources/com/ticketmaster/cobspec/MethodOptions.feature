Feature: Method Options

Scenario: Make an Options method call and get back method options for an endpoint
	When I make a "OPTIONS" request to "/method_options" endpoint
	Then I should get a "200" response code
	And The response header contains "GET,HEAD,POST,OPTIONS,PUT"

Scenario Outline: Excercise the various METHODS against the endpoint	
	When I make a <METHOD> request to "/method_options" endpoint
	Then I should get a <RESPONSE> response code
	Examples:
		| METHOD | RESPONSE |
		| get    | 200	    |
		| put    | 200      |
		| post   | 200      |
		| head   | 200      |
		| delete | 400      |
