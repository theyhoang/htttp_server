package com.ticketmaster.server;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by yen.hoang on 3/25/15.
 */
public class ResponseHandler {


    private PrintWriter out;
    private final String HTTP_VERSION = "HTTP/1.0";
    private List<String> paths;

//    PrintWriter out =
//        new PrintWriter(clientSocket.getOutputStream(), true);

    public ResponseHandler(PrintWriter out) {
        this.out = out;
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
        if (out == null)
            return null;
        Response response = new Response();
        if (paths.contains(request.getUrl()))
            response.setStatusCode(Response.STATUS_CODE_OK);
        else
            response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);

        response.setHttpVersion(HTTP_VERSION);

        return response;
    }

    // TODO : use actual paths to check for resources
    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public List<String> getPaths() {
        return paths;
    }

    // TODO: print out response
    public void print(Response response) {
        out.print(response);
    }
}
