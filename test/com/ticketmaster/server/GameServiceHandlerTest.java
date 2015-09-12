package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.response.ServiceRegistry;
import com.ticketmaster.server.tictactoe.TicTacToeApp;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yen.hoang on 9/11/15.
 */
public class GameServiceHandlerTest {

    @Test
    public void testPOSTGameEndpoint() {
        ServiceRegistry serviceRegistry = ServiceRegistry.initialize(new TicTacToeApp());

        Request request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.POST);
        request.setUrl("/game");

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
        request2.setUrl("/game");

        Assert.assertNotNull(request);

        Response response2 = serviceRegistry.generateResponse(request);
        Assert.assertNotNull(response2);
        Assert.assertEquals(response2.getStatusCode(), Response.STATUS_CODE_OK);

        String message2 = new String(response2.getMessage());
        Assert.assertEquals(message2, "{\"game_id\":2,\"occupiedMap\":[[false,false,false],[false,false,false],[false,false,false]],"
            + "\"markerMap\":[\"   \",\"   \",\"   \"]}");


    }
}
