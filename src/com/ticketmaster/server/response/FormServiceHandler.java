package com.ticketmaster.server.response;

import com.ticketmaster.server.Resources;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

/**
 * Created by yen.hoang on 7/16/15.
 */
public class FormServiceHandler implements ServiceHandler{

    @Override public Response GET(Request request) {
        Response response = new Response();
        response.setStatusCode(Response.STATUS_CODE_OK);
        response.setMessage(Resources.getData().getBytes());
        response.setHttpVersion(HTTP_VERSION);
        response.setContentType("text/html");
        return response;
    }

    @Override public Response PUT(Request request) {
        Response response = new Response();
        Resources.addData(request.getMessage());
        response.setStatusCode(Response.STATUS_CODE_OK);
        response.setMessage(request.getMessage().getBytes());
        response.setHttpVersion(HTTP_VERSION);
        return response;
    }

    @Override public Response POST(Request request) {
        Response response = new Response();
        Resources.addData(request.getMessage());
        response.setStatusCode(Response.STATUS_CODE_OK);
        response.setMessage(request.getMessage().getBytes());
        response.setHttpVersion(HTTP_VERSION);
        return response;
    }

    @Override public Response DELETE(Request request) {
        Response response = new Response();
        Resources.clearData();
        response.setStatusCode(Response.STATUS_CODE_OK);
        response.setHttpVersion(HTTP_VERSION);
        return response;
    }

    @Override public Response HEAD(Request request) {
        return null;
    }
}
