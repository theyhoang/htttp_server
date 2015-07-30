package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yen.hoang on 7/30/15.
 */
public class MethodOptionsServiceHandler implements ServiceHandler{
    @Override public Response GET(Request request) {
        return null;
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
        Response response = new Response();

        String methodsAllowed = "GET,HEAD,POST,OPTIONS,PUT";
        
        methodsAllowed = "Allow: " + methodsAllowed;
//        System.out.println(methodsAllowed);
        response.setStatusCode(Response.STATUS_CODE_OK);
        response.setHttpVersion(HTTP_VERSION);


        List<String> headers = new ArrayList<String>();
        headers.add(methodsAllowed);
        response.setHeaders(headers);

        return response;
    }
}
