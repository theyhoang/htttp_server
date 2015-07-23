package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

/**
 * Created by yen.hoang on 7/16/15.
 */
public interface ServiceHandler {

    String HTTP_VERSION = "HTTP/1.1";

    Response GET(Request request);

    Response PUT(Request request);

    Response POST(Request request);

    Response DELETE(Request request);

    Response HEAD(Request request);
}
