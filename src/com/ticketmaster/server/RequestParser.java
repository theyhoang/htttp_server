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

    @Override
    public Request parse(BufferedReader input) throws IOException {
        Request request = new Request();

        String initialRequestLine = input.readLine();
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


}
