package com.ticketmaster.server.model;

/**
 * Created by yen.hoang on 3/5/15.
 */
public class Request {

//    HTTP Method – action to be performed, usually GET, POST, PUT etc.
//    URL – Page to access
//    Form Parameters – similar to arguments in a java method, for example user,password details from login page.

    // components of a request
    private Method httpMethod;
    private String url;
    private String message;
    private String userAgent;
    private String httpVersion;


    public Method getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        if (httpMethod.startsWith(Method.GET.name())) {
            this.httpMethod = Method.GET;
        } else if (httpMethod.startsWith(Method.POST.name())) {
            this.httpMethod = Method.POST;
        } else {
            this.httpMethod = Method.NOT_SUPPORTED;
        }
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
}
