package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
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

        String initialRequestLine = inputList.get(0);
        List<String> initialRequest = Arrays.asList(initialRequestLine.split("\\s+"));
        if (initialRequest.size() != 3)
            return null;

        // first get method
        request.setHttpMethod(initialRequest.get(0));
        if (request.getHttpMethod() == Method.NOT_SUPPORTED) { // not supported
            return null;
        }

        // then get path
        String path = initialRequest.get(1);
        // validate?
        request.setUrl(path);

        // finally http version
        String httpVersion = initialRequest.get(2);
        request.setHttpVersion(httpVersion);



        return request;
    }


    @Override public Object parse(BufferedReader input) throws IOException {
        return null;
    }
}
