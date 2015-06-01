package com.ticketmaster.server;

import com.ticketmaster.server.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by yen.hoang on 3/25/15.
 */
public class RequestHandler {

    private InputReader inputReader;

    public RequestHandler(BufferedReader input) {
        this.inputReader = new InputReader(input);
    }


    public List<String> readInput() {
        return inputReader.readInput();
    }

    public Request readRequest() throws IOException {
        List<String> inputList = readInput();

        // TODO: process request
        // TODO: read headers, depending on method act accordingly, return

        // parse string
        // create new request object
        // return request as string or as class

        Request request = parseForRequestDetails(inputList);


        return request;
    }


    private Request parseForRequestDetails(List<String> inputList) throws IOException{
        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parse(inputList);

        return request;
    }
}
