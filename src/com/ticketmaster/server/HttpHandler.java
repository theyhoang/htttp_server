package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by yen.hoang on 3/5/15.
 */
public class HttpHandler {

    // TODO : multiple version of HTTP?
    private final String HTTP_VERSION = "HTTP/1.0";
    private BufferedReader input;
    private DataOutputStream output;
    private List<String> paths;

    public HttpHandler(BufferedReader input, DataOutputStream output){
        this.input = input;
        this.output = output;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public List<String> getPaths() {
        return paths;
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

        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parse(input);
        if (request == null)
            output.close();

        return request;
    }

    // construct response
    // based on protocol
    // construct response
    // return as string or class
    public String getResponse(Request request) {
        String response = constructResponseDetails(request).toString();
        return response;
    }


    //    out.println("HTTP/1.0 200 OK");
    //    out.println("Content-Type: text/html");
    //    out.println("Server: Bot");
    //    // this blank line signals the end of the headers
    //    out.println("");
    //    // Send the HTML page
    //    out.println("<H1>Welcome to the Ultra Mini-WebServer</H2>");
    private Response constructResponseDetails(Request request) {
        if (output == null)
            return null;
        Response response = new Response();
        if (paths.contains(request.getUrl()))
            response.setStatusCode(Response.STATUS_CODE_OK);
        else
            response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);

        response.setHttpVersion(HTTP_VERSION);

        return response;
    }

}
