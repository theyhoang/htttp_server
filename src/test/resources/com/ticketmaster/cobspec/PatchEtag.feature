Feature: Patch Etag
Patch is a relatively new HTTP method allowing partial update of resources.
HTTP 1.1 specifications require that the client send a SHA1 encoded version of the resource you're attempting to update in an If-Match header.
This ensures that the record on the server hasn't changed since you initially requested it.
Without this feature two clients could conflict with each other when making an update simultaneously.
Click here for more information about patch.

Scenario: Excercise the Patch Content functionality
	When I make a "GET" request to "/patch-content.txt" endpoint
	Then I should get a "200" response code
	And The response body contains "default content"
	
	When I set patch data to "patched content"
	And I set the eTag "60bb224c68b1ed765a0f84d910de58d0beea91c4"
	And I make a "PATCH" request to "/patch-content.txt" endpoint
	Then I should get a "204" response code
	
	When I make a "GET" request to "/patch-content.txt" endpoint
	Then I should get a "200" response code
	And The response body contains "patched content"
	
	## Revert patched content back to default content for cleanup
	When I set patch data to "default content"
	And I set the eTag "69bc18dc1edc9e1316348b2eaaca9df83898249f"
	And I make a "PATCH" request to "/patch-content.txt" endpoint
	Then I should get a "204" response code
	
	When I make a "GET" request to "/patch-content.txt" endpoint
	Then I should get a "200" response code
	And The response body contains "patched content"
	