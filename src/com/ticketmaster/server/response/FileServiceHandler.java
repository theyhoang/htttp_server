package com.ticketmaster.server.response;

import com.ticketmaster.server.FileUtils;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

import java.util.List;

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
                // TODO: check for partial content
                if (checkForPartialContent(request)) {
                    response.setMessage(retrievePartialContentBody(request));
                    response.setStatusCode(Response.STATUS_CODE_PARTIAL_CONTENT);
                } else {
                    response.setMessage(FileUtils.getFileContent(request.getUrl()));
                }
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

    private boolean checkForPartialContent(Request request) {
        List<String> headers = request.getHeaders();

        // Range: bytes=0-999
        for (String header : headers) {
            if (header.startsWith("Range:")) {
                return true;
            }
        }
        return false;
    }

    private byte[] retrievePartialContentBody(Request request) {
        // get range
        String range = null;
        for (String header : request.getHeaders()) {
            if (header.startsWith("Range:")) {
                range = header;
            }
        }

        // Range: bytes=0-999
        String[] rangeStringList = range.split("=");

        //0-999, 0-
        range = rangeStringList[1];

        rangeStringList = range.split("-");
        Integer lowerbound = null;
        Integer upperbound = null;
        if (rangeStringList.length == 2) {
            // then we have upper and lower bound
            lowerbound = new Integer(rangeStringList[0]);
            upperbound = new Integer(rangeStringList[1]);
        } else if (rangeStringList.length == 1) {
            // then we only have lower bound
            lowerbound = new Integer(rangeStringList[0]);
        }
        // use bounds to retrieve partial bytes
        byte[] fileContent = FileUtils.getFileContent(request.getUrl());

        // System.arraycopy(sourceArray,
//        sourceStartIndex,
//            targetArray,
//            targetStartIndex,
//            length);
        byte[] partialContent = new byte[fileContent.length];
        if (upperbound == null) {
            upperbound = fileContent.length;
        }

        Integer rangeLength = upperbound - lowerbound;

        System.arraycopy(fileContent, lowerbound, partialContent, 0, rangeLength + 1);

        return partialContent;
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
