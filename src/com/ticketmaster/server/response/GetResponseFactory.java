package com.ticketmaster.server.response;

import com.ticketmaster.server.FileUtils;
import com.ticketmaster.server.Resources;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

/**
 * Created by yen.hoang on 7/7/15.
 */
public class GetResponseFactory extends ResponseFactory {


    @Override
    public Response generateResponse(Request request) {
        Response response = new Response();

        if (FileUtils.resourceExistsInPath(request.getUrl())) {
            response.setStatusCode(Response.STATUS_CODE_OK);
        }
        else {
            if(request.getUrl().equals("/form")) {
                // TODO: create form handler and retrieve data
                response.setStatusCode(Response.STATUS_CODE_OK);
            } else {
                response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);
            }
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
                response.setMessage(Resources.getData().getBytes());
                response.setContentType("text/html");

                // TODO: ERROR HANDLING
            }
        } else {
            response.setMessage(
                (new String("<H1>" + response.getInitialResponseLine() + "</H1>")).getBytes());
        }

        return response;
    }
}
