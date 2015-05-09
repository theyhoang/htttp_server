package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yen.hoang on 3/20/15.
 */
public class RequestParser implements Parser {

    public RequestParser() {

    }

    public Request parse(List<String> inputList) throws IOException {
        Request request = new Request();

        // TODO: put inside a method for readability
        String initialRequestLine = inputList.get(0);
        List<String> initialRequest = Arrays.asList(initialRequestLine.split("\\s+"));
        if (initialRequest.size() != 3) {
            return null;
        }

        // first get method
        request.setHttpMethod(initialRequest.get(0));
        // TODO: add method to validate?
        if (request.getHttpMethod() == Method.NOT_SUPPORTED) { // not supported
            // TODO: throw exception
            return null;
        }

        // then get path
        String path = initialRequest.get(1);
        // validate?
        request.setUrl(path);

        // finally http version
        String httpVersion = initialRequest.get(2);
        request.setHttpVersion(httpVersion);

        // TODO: read headers
        List<String> headers = new ArrayList<String>();
        for (int i = 0; i < inputList.size(); i++) {
            // look for blank line to end headers
            if (!inputList.get(i).isEmpty()) {
                headers.add(inputList.get(i));
            }
        }

        request.setHeaders(headers);

        // TODO: get message/content
        // check headers for content type and post/put method
        // if (request.getHttpMethod() == Method.POST || request.getHttpMethod() == Method.PUT)
        //

        return request;
    }


    @Override public Object parse(BufferedReader input) throws IOException {
        return null;
    }
}
