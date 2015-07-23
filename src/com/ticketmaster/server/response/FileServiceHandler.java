package com.ticketmaster.server.response;

import com.ticketmaster.server.FileUtils;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

/**
 * Created by yen.hoang on 7/16/15.
 */
public class FileServiceHandler implements ServiceHandler{
    @Override public Response GET(Request request) {
        Response response = new Response();

        if (FileUtils.resourceExistsInPath(request.getUrl())) {
            response.setStatusCode(Response.STATUS_CODE_OK);
        } else {
            response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);
        }

        response.setHttpVersion(HTTP_VERSION);

        if (response.getStatusCode() == (Response.STATUS_CODE_OK)) {
            // if a file return file contents
            if (FileUtils.isFile(request.getUrl())) {
                response.setContentType(FileUtils.getFileContentType(request.getUrl()));
                response.setMessage(FileUtils.getFileContent(request.getUrl()));
                // if directory return directory listing/contents
            } else if (FileUtils.isDirectory(request.getUrl())) {
                response.setContentType("text/html");
                StringBuilder sb = new StringBuilder();
                String[] contents = FileUtils.getDirectoryContents(request.getUrl());
                for (String content : contents) {
                    String urlContent = request.getUrl() + content;
                    sb.append("<a href=" + "\"" + urlContent + "\"" + ">" + content + "</a><br />");
                }
                String message = sb.toString();
                response.setMessage(message.getBytes());
            } else {
                // TODO: ERROR HANDLING
            }
        }
        return response;
    }

    @Override public Response PUT(Request request) {
        return null;
    }

    @Override public Response POST(Request request) {
        return null;
    }

    @Override public Response DELETE(Request request) {
        return null;
    }

    @Override public Response HEAD(Request request) {
        return null;
    }
}
