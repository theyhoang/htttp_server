package com.ticketmaster.server.request;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.request.GetRequestFactory;
import com.ticketmaster.server.request.RequestFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yen.hoang on 3/25/15.
 */
public class RequestHandler {

    public Request readRequest(List<String> inputList) throws IOException {
        // TODO: process request
        // TODO: read headers, depending on method act accordingly, return
        Method methodType = parseForMethodType(inputList);

        // TODO: check if path is valid
        // get requestfactory based on method type
        RequestFactory requestFactory = null;
        switch(methodType) {
            case GET:
                requestFactory = new GetRequestFactory();
                break;
            case PUT:
                requestFactory = new PutRequestFactory();
                break;
            case POST:
                requestFactory = new PostRequestFactory();
                break;
            case PATCH:
                break;
            case DELETE:
                requestFactory = new DeleteRequestFactory();
                break;
            case OPTIONS:
                break;
            case HEAD:
                break;
            default:
                // TODO: should throw exception
                break;
        }

        // TODO: generate request with requestfactory type and return
        Request request = requestFactory.generateRequest(inputList);

        return request;
    }

    public Method parseForMethodType(List<String> inputList) throws IOException, IllegalArgumentException {
        String initialRequestLine = inputList.get(0);
        List<String> initialRequest = Arrays.asList(initialRequestLine.split("\\s+"));
        if (initialRequest.size() != 3) {
            // TODO: error handling throw exception?
            return null;
        }

        Method methodType = null;
        try {
           methodType = Method.valueOf(initialRequest.get(0));
        } catch (IllegalArgumentException ex) {
            methodType = Method.NOT_SUPPORTED;
        }
        return methodType;
    }
}
