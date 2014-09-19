package com.ticketmaster.cobspec;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestSteps {
  
  @Before
  public void startTheJavaServer() {
    // This is where you would call the start to your service
  }

  @After
  public void stopTheJavaServer() {
    // This is where you would shut down your service
  }

  @Given("^that I have made requests of method types:$")
  public void that_I_have_made_requests_of_types(List<String> requestType) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      // For automatic transformation, change DataTable to one of
      // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
      // E,K,V must be a scalar (String, Integer, Date, enum etc)
      for (String request : requestType){
          // Use the request type in here
      }
  }

}
