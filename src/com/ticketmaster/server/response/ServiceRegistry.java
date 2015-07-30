package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

import java.util.HashMap;

/**
 * Created by yen.hoang on 7/16/15.
 */
public class ServiceRegistry {

    private HashMap<String, ServiceHandler> serviceHandlerMap;
    private ServiceHandler defaultServiceHandler;

    public ServiceHandler getServiceHandler(String path) {

        ServiceHandler serviceHandler = serviceHandlerMap.get(path);
        if (serviceHandler == null) {
            serviceHandler = defaultServiceHandler;
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
                case HEAD:
                    response = serviceHandler.HEAD(request);
                    break;
                case OPTIONS:
                    response = serviceHandler.OPTIONS(request);
                    break;
                default:
                    break;
            }

        // if response is null, method is not supported
        if (response == null) {
            response = new Response();
            response.setHttpVersion(ServiceHandler.HTTP_VERSION);
            response.setStatusCode(Response.STATUS_CODE_METHOD_NOT_ALLOWED);
        }
        return response;
    }

    public void setDefaultServiceHandler(ServiceHandler serviceHandler) {
        defaultServiceHandler = serviceHandler;
    }

    public void registerServiceHandler(String pattern, ServiceHandler serviceHandler) {
        // put(pattern, serviceHandler)
        if (serviceHandlerMap == null) {
            serviceHandlerMap = new HashMap<String, ServiceHandler>();
        }
        serviceHandlerMap.put(pattern, serviceHandler);
    }
}
