package com.ticketmaster.server;

import com.ticketmaster.server.model.Request;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by yen.hoang on 3/25/15.
 */
public class RequestHandler {

    private BufferedReader input;

    public RequestHandler(BufferedReader input) {
        this.input = input;
    }

    public Request readRequest() {
        // parse string
        // create new request object
        // return request as string or as class

        Request request = null;
        try {
            request = parseForRequestDetails(input);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return request;
    }


    private Request parseForRequestDetails(BufferedReader input) throws IOException{
        if (input == null)
            return null;

        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parse(input);

        return request;
    }
}
