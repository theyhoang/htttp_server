package com.ticketmaster.server;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

import java.io.DataOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by yen.hoang on 3/25/15.
 */
public class ResponseHandler {


    private PrintWriter out;
    private final String HTTP_VERSION = "HTTP/1.1";

    private String publicDirPath;
//    PrintWriter out =
//        new PrintWriter(clientSocket.getOutputStream(), true);

    public ResponseHandler(PrintWriter out) {
        this.out = out;
    }

    // construct response
    // based on protocol
    // construct response
    // return as string or class
    public Response getResponse(Request request) {
        Response response = constructResponseDetails(request);
        return response;
    }

//    printWriter.write("HTTP/1.0 200 OK \r\n");
//    printWriter.write("Content-Type: text/html \r\n");
//    printWriter.write("Server: Bot \r\n");
//    // this blank line signals the end of the headers
//    printWriter.write("\r\n");
//    // Send the HTML page
//    printWriter.write("<H1>Hello Sean</H1>");

    private Response constructResponseDetails(Request request) {
        if (out == null)
            return null;
        Response response = new Response();
        if (resourceExistsInPath(request.getUrl()))
            response.setStatusCode(Response.STATUS_CODE_OK);
        else
            response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);

        response.setHttpVersion(HTTP_VERSION);
        // TODO: HEADERS?
        response.setContentType("text/html");
        response.setServer("Bot");
        response.setMessage("<H1>Hello Yen!</H1>");

        return response;
    }

    // TODO : use actual paths to check for resources
    public boolean resourceExistsInPath(String path) {
        // TODO: search for existing resource/directory
        System.out.println("publicDirPath: " + publicDirPath);
        System.out.println("path: " + path);
        File file = new File(publicDirPath + path);
        return file.exists();
    }

    // TODO: writeResponseToClient out response
    public void writeResponseToClient(Response response) {
        //    printWriter.write("HTTP/1.0 200 OK \r\n");
        //    printWriter.write("Content-Type: text/html \r\n");
        //    printWriter.write("Server: Bot \r\n");
        //    // this blank line signals the end of the headers
        //    printWriter.write("\r\n");
        //    // Send the HTML page
        //    printWriter.write("<H1>Hello Sean</H1>");
        out.write(response.getInitialResponseLine());
        // out.write(rest of headers)
        //
        out.write("\r\n");
        out.write(response.getMessage());
        out.flush();
        out.close();
    }

    public void setPublicDirPath(String publicDirPath) {
        this.publicDirPath = publicDirPath;
    }
}
