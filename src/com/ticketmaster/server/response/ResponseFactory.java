package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

/**
 * Created by yen.hoang on 7/7/15.
 */
public abstract class ResponseFactory {

    protected final String HTTP_VERSION = "HTTP/1.1";

    public abstract Response generateResponse(Request request);

    public String getDirectoryPage(String path) {

        return "<H1>" + path + " IS A DIRECTORY!</H1>";
    }

}
