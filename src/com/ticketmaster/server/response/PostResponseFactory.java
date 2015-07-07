package com.ticketmaster.server.response;

import com.ticketmaster.server.FileUtils;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

/**
 * Created by yen.hoang on 7/7/15.
 */
public class PostResponseFactory extends ResponseFactory {

    @Override
    public Response generateResponse(Request request) {
        Response response = new Response();
        response.setStatusCode(Response.STATUS_CODE_OK);

        response.setHttpVersion(HTTP_VERSION);
        // TODO: HEADERS?
        response.setServer("Bot");

        // TODO: read set data

        return response;
    }
}
