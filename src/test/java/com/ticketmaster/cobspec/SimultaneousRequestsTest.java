package com.ticketmaster.cobspec;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

public class SimultaneousRequestsTest {
  @Rule
  public ContiPerfRule i = new ContiPerfRule();

  @PerfTest(invocations = 4096, threads = 20)
  @Test
  public void hitGetEndpointOfService() {
    // Make a get call to the service method
  }
}
