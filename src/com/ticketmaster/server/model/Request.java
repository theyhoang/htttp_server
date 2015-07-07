package com.ticketmaster.server.model;

import java.util.List;

/**
 * Created by yen.hoang on 3/5/15.
 */
public class Request {

//    HTTP Method – action to be performed, usually GET, POST, PUT etc.
//    URL – Page to access
//    Form Parameters – similar to arguments in a java method, for example user,password details from login page.

    // TODO: use standard terminology: message = body, map for headers
    // components of a request
    private Method httpMethod;
    private String url;
    private String message;
    private String userAgent;
    private String httpVersion;
    private List<String> headers;

    public Method getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(Method methodType) {
        this.httpMethod = methodType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
