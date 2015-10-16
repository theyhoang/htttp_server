package com.ticketmaster.server.response;

import com.ticketmaster.server.FileUtils;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.ArrayList;
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
        if (headers != null) {
            for (String header : headers) {
                if (header.startsWith("Range:")) {
                    return true;
                }
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

        // use bounds to retrieve partial bytes
        byte[] fileContent = FileUtils.getFileContent(request.getUrl());

        // Range: bytes=0-999
        String[] rangeStringList = range.split("=");

        //0-999, 0-, -6 (last six bytes)
        range = rangeStringList[1];

        rangeStringList = range.split("-");
        Integer lowerbound = null;
        Integer upperbound = null;
        if (rangeStringList.length == 2) {
            if (!rangeStringList[0].isEmpty()) {
                // if we have upper/lower bound
                lowerbound = new Integer(rangeStringList[0]);
                upperbound = new Integer(rangeStringList[1]);
            } else {
                // if we get last # of bytes
                lowerbound = fileContent.length - new Integer(rangeStringList[1]);
            }
        } else if (rangeStringList.length == 1) {
            // we only have lower bound
            lowerbound = new Integer(rangeStringList[0]);
        }


        // System.arraycopy(sourceArray,
//        sourceStartIndex,
//            targetArray,
//            targetStartIndex,
//            length);
        if (upperbound == null) {
            upperbound = fileContent.length;
        }

        Integer rangeLength = upperbound - lowerbound;
        if (!upperbound.equals(fileContent.length)) {
            rangeLength++;
        }

        byte[] partialContent = new byte[rangeLength];

        System.arraycopy(fileContent, lowerbound, partialContent, 0, rangeLength);

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

    @Override public Response OPTIONS(Request request) {
        return null;
    }

    @Override public Response PATCH(Request request) {
        Response response = new Response();

        if (!FileUtils.resourceExistsInPath(request.getUrl())) {
            //check if file exists, if not 404
            response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);
        }

        response.setHttpVersion(HTTP_VERSION);

        boolean doesContainEtagHeader = containsEtagHeader(request.getHeaders());
        if (!doesContainEtagHeader) {
            // TODO: malformed request
            response.setStatusCode(Response.STATUS_CODE_BAD_REQUEST);
        }


        //if exists update file with message, return 204, and etag
        if (response.getStatusCode() != Response.STATUS_CODE_NOT_FOUND && doesContainEtagHeader) {
            // get original etag
            String originalFileContent = new String(FileUtils.getFileContent(request.getUrl()));
            String originalEtag = DigestUtils.sha1Hex(originalFileContent);

            // check against etag from request header
            // If-Match: "e0023aa4e"
            String etagFromRequest = retrieveEtagFromRequest(request.getHeaders());

            if (originalEtag.equals(etagFromRequest)) {

                try {
                    FileUtils.updateFile(request.getUrl(), request.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // then retrieve etag, return etag in header
                String etag = DigestUtils.sha1Hex(request.getMessage());

                List<String> headers = new ArrayList<String>();
                headers.add("Etag: \"" + etag + "\"");
                response.setHeaders(headers);
                response.setStatusCode(Response.STATUS_CODE_NO_CONTENT);
            } else {
                response.setStatusCode(Response.STATUS_CODE_BAD_REQUEST);
            }
        }


        return response;
    }

    private boolean containsEtagHeader(List<String> headers) {
        for (String header : headers) {
            if (header.startsWith("If-Match: ")) {
                return true;
            }
        }
        return false;
    }

    private String retrieveEtagFromRequest(List<String> headers) {
        String etagHeader = null;
        for (String header : headers) {
            if (header.startsWith("If-Match: ")) {
                etagHeader = header;
                break;
            }
        }

        String etag = etagHeader.split(" ")[1];
        etag = etag.replace("\"", "");
        return etag;
    }
}
