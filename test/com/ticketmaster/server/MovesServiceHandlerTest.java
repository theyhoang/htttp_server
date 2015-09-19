package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.response.ServiceRegistry;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yen.hoang on 9/18/15.
 */
public class MovesServiceHandlerTest {
    @Test
    public void testPOSTMovesEndpoint() {
        ServiceRegistry serviceRegistry = ServiceRegistry.initialize();

        // create initial game
        Request request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.POST);
        request.setUrl("/games");

        Assert.assertNotNull(request);

        Response response = serviceRegistry.generateResponse(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatusCode(), Response.STATUS_CODE_OK);

        String message = new String(response.getMessage());
        Assert.assertEquals(message, "{\"game_id\":1,\"occupiedMap\":[[false,false,false],[false,false,false],[false,false,false]],"
            + "\"markerMap\":[\"   \",\"   \",\"   \"]}");


        Request request2 = new Request();
        request2.setHttpVersion("HTTP/1.1");
        request2.setHttpMethod(Method.POST);
        request2.setUrl("/moves/1");
        request2.setMessage("{\"spot_id\":1}");

        Assert.assertNotNull(request2);

        Response response2 = serviceRegistry.generateResponse(request2);
        Assert.assertNotNull(response2);
        Assert.assertEquals(response2.getStatusCode(), Response.STATUS_CODE_OK);

        String message2 = new String(response2.getMessage());
        Assert.assertEquals(message2, "{\"game_id\":1,\"occupiedMap\":[[true,false,false],[false,true,false],[false,false,false]],"
            + "\"markerMap\":[\"X  \",\" O \",\"   \"]}");


    }
}
