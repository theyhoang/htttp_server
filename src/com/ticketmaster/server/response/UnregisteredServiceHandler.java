package com.ticketmaster.server.response;

import com.ticketmaster.server.FileUtils;
import com.ticketmaster.server.Resources;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

/**
 * Created by yen.hoang on 7/16/15.
 */
public class UnregisteredServiceHandler implements ServiceHandler{
    @Override public Response GET(Request request) {
        Response response = new Response();

        // TODO: check if resource
        if (FileUtils.resourceExistsInPath(request.getUrl())) {
            response.setStatusCode(Response.STATUS_CODE_OK);
        } else {
            response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);
        }

        response.setHttpVersion(HTTP_VERSION);
        // TODO: HEADERS?

        if (response.getStatusCode() == (Response.STATUS_CODE_OK)) {
            if (FileUtils.isFile(request.getUrl())) {
                response.setContentType(FileUtils.getFileContentType(request.getUrl()));
                response.setMessage(FileUtils.getFileContent(request.getUrl()));
            } else if (FileUtils.isDirectory(request.getUrl())) {
                // TODO: return list of directories
//                response.setMessage(getDirectoryPage(request.getUrl()).getBytes());
            } else {
                // TODO: ERROR HANDLING
            }
        }
        return response;
    }

    @Override public Response PUT(Request request) {
        Response response = new Response();
        response.setHttpVersion(HTTP_VERSION);
        response.setStatusCode(Response.STATUS_CODE_METHOD_NOT_ALLOWED);
        return response;
    }

    @Override public Response POST(Request request) {
        Response response = new Response();
        response.setHttpVersion(HTTP_VERSION);
        response.setStatusCode(Response.STATUS_CODE_METHOD_NOT_ALLOWED);
        return response;
    }

    @Override public Response DELETE(Request request) {
        return null;
    }
}
