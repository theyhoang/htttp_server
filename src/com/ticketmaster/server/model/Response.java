package com.ticketmaster.server.model;

/**
 * Created by yen.hoang on 3/5/15.
 */
public class Response {

//    Status Code – an integer to indicate whether the request was success or not. Some of the well known status codes are 200 for success, 404 for Not Found and 403 for Access Forbidden.
//    Content Type – text, html, image, pdf etc. Also known as MIME type
//    Content – actual data that is rendered by client and shown to user.

    private final int STATUS_CODE_OK = 200;
    private final int STATUS_CODE_BAD_REQUEST = 400;
    private final int STATUS_CODE_FORBIDDEN = 403;
    private final int STATUS_CODE_NOT_FOUND = 404;
    private final int STATUS_CODE_INTERNAL_SERVER_ERROR = 500;
    private final int STATUS_CODE_NOT_IMPLEMENTED = 501;

    private int statusCode;
    private String headers;
    private String contentType;
    private String server;
    private String message;

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
