package com.ticketmaster.cobspec;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/com/ticketmaster/cobspec/"},
    format = {"html:target/cucumber-html", "json:target/results/result.json"})
public class RunnerIT {

}
