package com.ticketmaster.server.request;

import com.ticketmaster.server.Resources;
import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yen.hoang on 3/25/15.
 */
public class RequestHandler {

    public Request readRequest(List<String> inputList) throws IOException {
        Method methodType = parseForMethodType(inputList);

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
                requestFactory = new PatchRequestFactory();
                break;
            case DELETE:
                requestFactory = new DeleteRequestFactory();
                break;
            case OPTIONS:
                requestFactory = new OptionsRequestFactory();
                break;
            case HEAD:
                requestFactory = new HeadRequestFactory();
                break;
            default:
                // TODO: should throw exception
                break;
        }

        // add log and generate request
        Resources.addLog(inputList.get(0));
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
