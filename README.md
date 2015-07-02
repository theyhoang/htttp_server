How to build and run java server:
mvn clean package
cd into target directory where packaged jar is contained
java -jar com.ticketmaster-0.0.1-SNAPSHOT.jar -p 9000 -d /Users/yen.hoang/Projects/Apprenticeship/cob_spec/public/
-p indicates port
-d indicated directory of public folder


How to use this project:

1. Generate the Step Definition stubs in Java. You can either use your favorite plugin IDE or just use the Cucumber JVM facilities:

mvn clean verify

You will get output to the console that looks like:

You can implement missing steps with the snippets below:

@When("^I make a \"(.*?)\" request to \"(.*?)\" endpoint$")
public void i_make_a_request_to_endpoint(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

@Then("^I should get a \"(.*?)\" response code$")
public void i_should_get_a_response_code(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}

@Then("^The response body contains \"(.*?)\"$")
...

Append everything inside the TestStep.java file

Look at your Test Results:

target/cucumber-html/index.html

Everything should be pending implementation

2. Start implementing the Step Definitions

Since you are primarily writing a Web Server to deal with GET, POST, PUT, and some other methods, I've included a testing library that may be useful.

    <dependency>
		<groupId>com.jayway.restassured</groupId>
		<artifactId>rest-assured</artifactId>
		<version>2.3.3</version>
	</dependency>

Feel free to experiment with the Step Defs to see what they actually do.

3. Run the tests again: mvn clean verify
   Read the report: target/cucumber-html/index.html
   You should now have some failing tests
   
4. Start implementing your server and run the tests to see if you've met the spec.
