package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

/**
 * Created by yen.hoang on 9/10/15.
 */
public class MovesServiceHandler implements  ServiceHandler {
    @Override public Response GET(Request request) {
        return null;
    }

    @Override public Response PUT(Request request) {
        return null;
    }

    @Override public Response POST(Request request) {
        // TODO: POST moves/{game_id}
        // TODO: POST retrieve data to post to spot "spot" : [0-9], "marker" : "[X/O]"
        return null;
    }

    @Override public Response DELETE(Request request) {
        return null;
    }

    @Override public Response HEAD(Request request) {
        return null;
    }

    @Override public Response OPTIONS(Request request) {
        return null;
    }

    @Override public Response PATCH(Request request) {
        return null;
    }
}
