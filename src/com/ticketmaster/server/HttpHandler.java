package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by yen.hoang on 3/5/15.
 */
public class HttpHandler {

    private BufferedReader input;
    private DataOutputStream output;

    public HttpHandler(BufferedReader input, DataOutputStream output){
        this.input = input;
        this.output = output;
    }


//    GET /path/file.html HTTP/1.0
//    From: someuser@jmarshall.com
//    User-Agent: HTTPTool/1.0

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

        Request request = new Request();
        String methodString = input.readLine();

        // first get method
        request.setHttpMethod(methodString);
        if (request.getHttpMethod() == Method.NOT_SUPPORTED) { // not supported
            output.close();
            return null;
        }


        return request;
    }

    // construct response
    // based on protocol
    // construct response
    // return as string or class
    public String getResponse() {
        Response response = new Response();

        return null;
    }


    //    out.println("HTTP/1.0 200 OK");
    //    out.println("Content-Type: text/html");
    //    out.println("Server: Bot");
    //    // this blank line signals the end of the headers
    //    out.println("");
    //    // Send the HTML page
    //    out.println("<H1>Welcome to the Ultra Mini-WebServer</H2>");
    private Response constructResponseDetails() {
        if (output == null)
            return null;
        return null;
    }

}
