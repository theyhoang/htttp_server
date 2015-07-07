package com.ticketmaster.server.response;

import com.ticketmaster.server.FileUtils;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.output.OutputWriter;

import java.io.DataOutputStream;

/**
 * Created by yen.hoang on 3/25/15.
 */

// TODO: to only handle response, separate other responsibilities to downstream classes
public class ResponseHandler {

    private final String HTTP_VERSION = "HTTP/1.1";

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

    // TODO: refactor out responsibilities
    private Response constructResponseDetails(Request request) {
        Response response = new Response();
        if (FileUtils.resourceExistsInPath(request.getUrl()))
            response.setStatusCode(Response.STATUS_CODE_OK);
        else {
            response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);
        }

        response.setHttpVersion(HTTP_VERSION);
        // TODO: HEADERS?
        response.setServer("Bot");

        // TODO: retrieve directory page with links if directory
        // TODO: error handling

        if (response.getStatusCode() == (Response.STATUS_CODE_OK)) {
            if (FileUtils.isFile(request.getUrl())) {
                response.setContentType(FileUtils.getFileContentType(request.getUrl()));
                response.setMessage(FileUtils.getFileContent(request.getUrl()));
            } else if (FileUtils.isDirectory(request.getUrl())) {
                response.setMessage(getDirectoryPage(request.getUrl()).getBytes());
            } else {
                // TODO: ERROR HANDLING
            }
        } else {
            response.setMessage((new String("<H1>" + response.getInitialResponseLine() + "</H1>")).getBytes());
        }

        return response;
    }

    private String getDirectoryPage(String path) {

        return "<H1>" + path + " IS A DIRECTORY!</H1>";
    }

}