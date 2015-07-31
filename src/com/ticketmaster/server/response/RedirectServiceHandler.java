package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yen.hoang on 7/16/15.
 */
public class RedirectServiceHandler implements ServiceHandler{
    @Override public Response GET(Request request) {
        Response response = new Response();
        response.setHttpVersion(HTTP_VERSION);
        response.setStatusCode(Response.STATUS_CODE_FOUND);
        String location = "Location: http://localhost:5000/";
        List<String> headers = new ArrayList<String>();
        headers.add(location);
        response.setHeaders(headers);
        return response;
    }

    @Override public Response PUT(Request request) {
        return null;
    }

    @Override public Response POST(Request request) {
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
