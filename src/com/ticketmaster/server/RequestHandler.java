package com.ticketmaster.server;

import com.ticketmaster.server.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yen.hoang on 3/25/15.
 */
public class RequestHandler {

    // TODO: implement protocol?

    private BufferedReader input;

    public RequestHandler(BufferedReader input) {
        this.input = input;
    }

    public void start() throws IOException {
        String userInput = null;
        while ((userInput = input.readLine()) != null) {
            //                out.println(userInput);
            System.out.println("echo: " + input.readLine());
        }
    }

    public List<String> readInput() throws IOException {
        List<String> inputList = new ArrayList<>();
        String userInput;
        while ((userInput = input.readLine()) != null) {
            System.out.println(userInput);
            inputList.add(userInput);
            if (userInput.isEmpty()) {
                break;
            }
        }
        return inputList;
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
