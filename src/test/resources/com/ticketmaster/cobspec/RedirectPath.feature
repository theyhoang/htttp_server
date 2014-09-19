Feature: Test Redirect Path

Scenario: Provide a redirect endpoint
	When I make a "GET" request to "/redirect" endpoint
	Then I should get a "200" response code
	And The last request path should be "http://localhost:5000/"