package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

/**
 * Created by yen.hoang on 7/16/15.
 */
public class ServiceRegistry {

    public ServiceHandler getServiceHandler(String path) {
        ServiceHandler serviceHandler = null;
        switch (path) {
            case "/form":
                serviceHandler = new FormServiceHandler();
                break;
            case "/redirect":
                serviceHandler = new RedirectServiceHandler();
                break;
            default:
                serviceHandler = new UnregisteredServiceHandler();
                break;
        }

        return serviceHandler;
    }

    public Response generateResponse(Request request) {

        ServiceHandler serviceHandler = getServiceHandler(request.getUrl());
        Response response = null;
            switch (request.getHttpMethod()) {
                case GET:
                    response = serviceHandler.GET(request);
                    break;
                case POST:
                    response = serviceHandler.POST(request);
                    break;
                case PUT:
                    response = serviceHandler.PUT(request);
                    break;
                case DELETE:
                    response = serviceHandler.DELETE(request);
                    break;
                default:
                    break;
            }
        // TODO: check if response is null, throw an exception or return method not supported
        if (response == null) {
            response = new Response();
            response.setHttpVersion(ServiceHandler.HTTP_VERSION);
            response.setStatusCode(Response.STATUS_CODE_METHOD_NOT_ALLOWED);
        }
        return response;
    }
}
