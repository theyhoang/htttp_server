Feature: Test Basic Authentication

Scenario: The /logs url should be protected with basic access authentication. A request passing username "admin" and password "hunter2" should return log information. All others should return a 401 Unauthorized response.
	When I make a "GET" request to "/logs" endpoint
	Then I should get a "401" response code
	And The response body contains "Authentication required"
	And The response body does not contain "GET /log HTTP/1.1"
	
Scenario: Access log request methods
	Given that I have made requests of method types
	| get  |
	| put  |
	| head |
	When I get "/logs" with credentials username "admin" password "hunter2"
	Then I should get a "200" response code
	And The response body contains "GET /log HTTP/1.1"
	And The response body contains "PUT /these HTTP/1.1"
	And The response body contains "HEAD /requests HTTP/1.1"