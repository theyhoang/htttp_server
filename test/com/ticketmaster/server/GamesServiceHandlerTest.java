package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.response.ServiceRegistry;
import com.ticketmaster.server.tictactoe.TicTacToeApp;
import cucumber.api.java.Before;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yen.hoang on 9/11/15.
 */
public class GamesServiceHandlerTest {

    @Test
    public void testPOSTGameEndpoint() {
        TicTacToeApp.reset();
        ServiceRegistry serviceRegistry = ServiceRegistry.initialize();

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
        request2.setUrl("/games");

        Assert.assertNotNull(request2);

        Response response2 = serviceRegistry.generateResponse(request2);
        Assert.assertNotNull(response2);
        Assert.assertEquals(response2.getStatusCode(), Response.STATUS_CODE_OK);

        String message2 = new String(response2.getMessage());
        Assert.assertEquals(message2, "{\"game_id\":2,\"occupiedMap\":[[false,false,false],[false,false,false],[false,false,false]],"
            + "\"markerMap\":[\"   \",\"   \",\"   \"]}");


    }

    @Test
    public void testGETGameEndpointAllGames() {
        TicTacToeApp.reset();
        ServiceRegistry serviceRegistry = ServiceRegistry.initialize();

        Request request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.POST);
        request.setUrl("/games");

        Assert.assertNotNull(request);

        Response response = serviceRegistry.generateResponse(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatusCode(), Response.STATUS_CODE_OK);

        String message = new String(response.getMessage());
        Assert.assertEquals(message,
            "{\"game_id\":1,\"occupiedMap\":[[false,false,false],[false,false,false],[false,false,false]],"
                + "\"markerMap\":[\"   \",\"   \",\"   \"]}");
        serviceRegistry.generateResponse(request);

        Request request2 = new Request();
        request2.setHttpVersion("HTTP/1.1");
        request2.setHttpMethod(Method.GET);
        request2.setUrl("/games");

        Assert.assertNotNull(request2);

        Response response2 = serviceRegistry.generateResponse(request2);
        Assert.assertNotNull(response2);
        Assert.assertEquals(response2.getStatusCode(), Response.STATUS_CODE_OK);

        Assert.assertEquals(new String(response2.getMessage()), "[{\"game_id\":1,\"occupiedMap\":[[false,false,false],[false,false,false],[false,false,false]],"
            + "\"markerMap\":[\"   \",\"   \",\"   \"]},"
            + "{\"game_id\":2,\"occupiedMap\":[[false,false,false],[false,false,false],[false,false,false]],"
            + "\"markerMap\":[\"   \",\"   \",\"   \"]}]");
    }

    @Test
    public void testGETGameEndpointSingleGame() {
        TicTacToeApp.reset();
        ServiceRegistry serviceRegistry = ServiceRegistry.initialize();

        Request request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.POST);
        request.setUrl("/games");

        Assert.assertNotNull(request);

        Response response = serviceRegistry.generateResponse(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatusCode(), Response.STATUS_CODE_OK);

        String message = new String(response.getMessage());
        Assert.assertEquals(message,
            "{\"game_id\":1,\"occupiedMap\":[[false,false,false],[false,false,false],[false,false,false]],"
                + "\"markerMap\":[\"   \",\"   \",\"   \"]}");
        serviceRegistry.generateResponse(request);

        Request request2 = new Request();
        request2.setHttpVersion("HTTP/1.1");
        request2.setHttpMethod(Method.GET);
        request2.setUrl("/games/2");

        Assert.assertNotNull(request2);

        Response response2 = serviceRegistry.generateResponse(request2);
        Assert.assertNotNull(response2);
        Assert.assertEquals(response2.getStatusCode(), Response.STATUS_CODE_OK);

        Request request3 = new Request();
        request3.setHttpVersion("HTTP/1.1");
        request3.setHttpMethod(Method.GET);
        request3.setUrl("/games/3");

        Assert.assertNotNull(request3);

        Response response3 = serviceRegistry.generateResponse(request3);
        Assert.assertNotNull(response3);
        Assert.assertEquals(response3.getStatusCode(), Response.STATUS_CODE_NOT_FOUND);

        request3.setUrl("/games/test");

        Assert.assertNotNull(request3);

        response3 = serviceRegistry.generateResponse(request3);
        Assert.assertNotNull(response3);
        Assert.assertEquals(response3.getStatusCode(), Response.STATUS_CODE_NOT_FOUND);
    }

    @Test
    public void testPOSTSpotEndpoint() {
        TicTacToeApp.reset();
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
        request2.setUrl("/games/1/spots");
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
