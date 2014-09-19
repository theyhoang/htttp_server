package com.ticketmaster.cobspec;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

public class SimultaneousRequestsTest {
  @Rule
  public ContiPerfRule i = new ContiPerfRule();

  @PerfTest(duration = 10000, threads = 20, rampUp = 1000, warmUp = 1000)
  @Test
  public void hitGetEndpointOfService() {
    // Make a get call to the service method
  }
}
