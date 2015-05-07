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
        else {
            response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);
        }

        response.setHttpVersion(HTTP_VERSION);
        // TODO: HEADERS?
        response.setContentType("text/html");
        response.setServer("Bot");

        // TODO: if file exists
        // TODO: retrieve directory page with links if directory
        // TODO: retrieve file contents if file
        if (response.getStatusCode() == (Response.STATUS_CODE_OK)) {
            if (isFile(request.getUrl())) {
                response.setMessage(getFileContent(request.getUrl()));
            } else if (isDirectory(request.getUrl())) {
                response.setMessage(getDirectoryPage(request.getUrl()));
            } else {
                // TODO: ERROR
            }
        } else {
            response.setMessage("<H1>" + response.getInitialResponseLine() + "</H1>");
        }

        return response;
    }

    private boolean resourceExistsInPath(String path) {
        File file = new File(publicDirPath + path);
        return file.exists();
    }

    private boolean isFile(String path) {
        File file = new File(publicDirPath + path);
        return file.isFile();
    }

    private boolean isDirectory(String path) {
        File file = new File(publicDirPath + path);
        return file.isDirectory();
    }

    private String getDirectoryPage(String path) {

        return "<H1>" + path + " IS A DIRECTORY!</H1>";
    }

    private String getFileContent(String path) {
        return "<H1>" + path + " IS A FILE!</H1>";
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
        // TODO : out.write(rest of headers)
        //
        out.write("\r\n");
        out.write(response.getMessage());
        out.write("\r\n");
        out.flush();
        out.close();
    }

    public void setPublicDirPath(String publicDirPath) {
        this.publicDirPath = publicDirPath;
    }
}
