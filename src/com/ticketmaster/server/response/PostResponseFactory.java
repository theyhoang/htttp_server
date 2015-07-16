package com.ticketmaster.server.response;

import com.ticketmaster.server.FileUtils;
import com.ticketmaster.server.Resources;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

/**
 * Created by yen.hoang on 7/7/15.
 */
public class PostResponseFactory extends ResponseFactory {

    @Override
    public Response generateResponse(Request request) {
        Response response = new Response();
        // TODO: create endpoint enum match for post requests?

        if (request.getUrl().equals("/form")) {
            // TODO: create form handler and set data
            Resources.addData(request.getMessage());
            response.setStatusCode(Response.STATUS_CODE_OK);
            response.setMessage(request.getMessage().getBytes());
        } else {
            response.setStatusCode(Response.STATUS_CODE_OK);
        }

        response.setHttpVersion(HTTP_VERSION);
        // TODO: HEADERS?
        response.setServer("Bot");


        return response;
    }
}
