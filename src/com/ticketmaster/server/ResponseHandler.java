package com.ticketmaster.server;

import com.google.common.io.Files;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by yen.hoang on 3/25/15.
 */
public class ResponseHandler {


    private DataOutputStream out;
    private final String HTTP_VERSION = "HTTP/1.1";
    private final String CRLF = "\r\n";

    private String publicDirPath;
//    PrintWriter out =
//        new PrintWriter(clientSocket.getOutputStream(), true);

    public ResponseHandler(DataOutputStream out) {
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
        response.setServer("Bot");

        // TODO: if file exists
        // TODO: retrieve directory page with links if directory
        // TODO: retrieve file contents if file
        if (response.getStatusCode() == (Response.STATUS_CODE_OK)) {
            if (isFile(request.getUrl())) {
                response.setContentType(getFileContentType(request.getUrl()));
                response.setMessage(getFileContent(request.getUrl()));
            } else if (isDirectory(request.getUrl())) {
                response.setMessage(getDirectoryPage(request.getUrl()).getBytes());
            } else {
                // TODO: ERROR
            }
        } else {
            response.setMessage((new String("<H1>" + response.getInitialResponseLine() + "</H1>")).getBytes());
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

    private String getFileContentType(String path) {

        String contentType = "text/html";
        File file = new File(publicDirPath + path);
        // TODO: abstract to method for MIME Types
        if (file.getName().endsWith(".jpeg")) {
            contentType = "image/jpeg";
        }

        return contentType;
    }

    private byte[] getFileContent(String path) {
        byte[] content = null;
        try {
            File file = new File(publicDirPath + path);
            content = Files.toByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
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
        try {
            out.write(response.getInitialResponseLine().getBytes());
            // TODO : out.write(rest of headers)
            out.write((new String("Content-Type: " + response.getContentType() + "\r\n")).getBytes());
            out.write(CRLF.getBytes());
            out.write(response.getMessage());
            out.write(CRLF.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPublicDirPath(String publicDirPath) {
        this.publicDirPath = publicDirPath;
    }
}
