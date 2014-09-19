Feature: Test the implementation of the Java Server Specs
	
Scenario: Request to Default Index returns a page with Document Root contents as links
	When I make a "GET" request to "/" endpoint
	Then the HTML response page contains links to:
	| file1 |
	| file2 |
	| image.gif |
	| image.jpeg |
	| image.png |
	| text-file.txt |
	
Scenario: Request to Default Index returns a page with the Directories in Document Root
	When I make a "GET" request to "/" endpoint
	Then the body contains directory content "public"
	
Scenario: Request to file link will return file contents
	When I make a "GET" request to "/file1" endpoint
	Then I should get a "200" response code
	And The body contains file content "public/file1"
	
Scenario: Request to file link will return image contents
	When I make a "GET" request to "/image.jpeg" endpoint
	Then I should get a "200" response code
	And The body contains file content "public/image.jpeg"
	When I make a GET request to "/image.png" endpoint
	Then I should get a "200" response code
	And The body contains file content "public/image.png"
	When I make a GET request to "/image.gif" endpoint
	Then I should get a "200" response code
	And The body contains file content "public/image.gif"
	
Scenario: Request to a missing link or page returns a 404
	When I make a "GET" request to "/missing_page" endpoint
	Then I should get a "404" response code
	
Scenario: Request method is not allowed
	When I make a "PUT" request to "/file1" endpoint
	Then I should get a "405" response code
	When I make a "POST" request to "/text-file.txt" endpoint
	Then I should get a "405" response code
	
Scenario: HTTP Decode Parameters on Query String
	When I make a "GET" request to "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff" endpoint
	Then I should get a "200" response code
	And The response body contains "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: "is that all"?"
	And The response body contains "variable_2 = stuff"
	
Scenario: Request partial content
	When I make a "GET" request for partial content to "/partial_content.txt" endpoint
	Then I should get a "206" response code
	And The body contains file content "public/partial_content.txt"

