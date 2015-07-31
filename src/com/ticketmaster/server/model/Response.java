package com.ticketmaster.server.model;

import java.util.List;

/**
 * Created by yen.hoang on 3/5/15.
 */
public class Response {

//    Status Code – an integer to indicate whether the request was success or not. Some of the well known status codes are 200 for success, 404 for Not Found and 403 for Access Forbidden.
//    Content Type – text, html, image, pdf etc. Also known as MIME type
//    Content – actual data that is rendered by client and shown to user.

    public static final int STATUS_CODE_OK = 200;
    public static final int STATUS_CODE_NO_CONTENT = 204;
    public static final int STATUS_CODE_PARTIAL_CONTENT = 206;
    public static final int STATUS_CODE_FOUND = 302;
    public static final int STATUS_CODE_BAD_REQUEST = 400;
    public static final int STATUS_CODE_UNAUTHORIZED = 401;
    public static final int STATUS_CODE_FORBIDDEN = 403;
    public static final int STATUS_CODE_NOT_FOUND = 404;
    public static final int STATUS_CODE_METHOD_NOT_ALLOWED = 405;
    public static final int STATUS_CODE_INTERNAL_SERVER_ERROR = 500;
    public static final int STATUS_CODE_NOT_IMPLEMENTED = 501;

    private final String CRLF = "\r\n";

    // TODO: use map for headers, message to body
    private int statusCode;
    private List<String> headers;
    private String contentType;
    private String server;
    private byte[] message;
    private String httpVersion;
    private String reasonPhrase;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        setReasonPhrase();
    }

    public int getStatusCode() {
        return statusCode;
    }
    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
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

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void setReasonPhrase() {
        String phrase = null;
        switch(statusCode) {
            case STATUS_CODE_OK:
                phrase = "OK";
                break;
            case STATUS_CODE_BAD_REQUEST:
                phrase = "BAD REQUEST";
                break;
            case STATUS_CODE_FORBIDDEN:
                phrase = "FORBIDDEN";
                break;
            case STATUS_CODE_NOT_FOUND:
                phrase = "NOT FOUND";
                break;
            case STATUS_CODE_NOT_IMPLEMENTED:
                phrase = "NOT IMPLEMENTED";
                break;
            case STATUS_CODE_INTERNAL_SERVER_ERROR:
                phrase = "INTERNAL SERVER ERROR";
                break;

        }
        this.reasonPhrase = phrase;

    }

    public String getInitialResponseLine() {
        String response = "";
        response = response.concat(httpVersion);
        response = response.concat(" " + Integer.toString(statusCode));
        response = response.concat(" " + reasonPhrase);
        response = response.concat(CRLF);

        return response;
    }
}
