package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

/**
 * Created by yen.hoang on 7/31/15.
 */
public class ParametersServiceHandler implements ServiceHandler{
    @Override public Response GET(Request request) {
        Response response = new Response();
        response.setStatusCode(Response.STATUS_CODE_OK);
        response.setHttpVersion(HTTP_VERSION);
        response.setContentType("text/html");

        if (request.getParameters() != null) {
            // add parameters to body
            StringBuilder messageSb = new StringBuilder();
            for (String parameter : request.getParameters()) {
                messageSb.append(parameter);
            }
            response.setMessage((messageSb.toString()).getBytes());
        }

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
