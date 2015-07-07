package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.response.ResponseHandler;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.URL;

/**
 * Created by yen.hoang on 3/25/15.
 */

public class ResponseHandlerTest {
    @Test
    public void testConstructResponse() {

        DataOutputStream out = new DataOutputStream(System.out);
        ResponseHandler responseHandler = new ResponseHandler(out);
        responseHandler.setFileUtils(new FileUtils(""));

        Request request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.GET);
        request.setUrl("/");

        Assert.assertNotNull(request);
        Response response = responseHandler.getResponse(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getInitialResponseLine(), "HTTP/1.1 200 OK\r\n");
    }

    @Test
    public void testGetDirectoryPage() {
        DataOutputStream out = new DataOutputStream(System.out);
        ResponseHandler responseHandler = new ResponseHandler(out);

        URL url = getClass().getClassLoader().getResource("./");
        responseHandler.setFileUtils(new FileUtils(url.getPath()));


        // TODO
    }

    @Test
    public void testGetFileContent() {
        DataOutputStream out = new DataOutputStream(System.out);
        ResponseHandler responseHandler = new ResponseHandler(out);
        responseHandler.setFileUtils(new FileUtils(""));

        URL url = getClass().getClassLoader().getResource("./");
        responseHandler.setFileUtils(new FileUtils(url.getPath()));

        Request request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.GET);
        request.setUrl("/file1");

        Assert.assertNotNull(request);
        Response response = responseHandler.getResponse(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getInitialResponseLine(), "HTTP/1.1 200 OK\r\n");
        Assert.assertNotNull(response.getMessage());
    }

    @Test
    public void testGetImageFiles() {
        DataOutputStream out = new DataOutputStream(System.out);
        ResponseHandler responseHandler = new ResponseHandler(out);
        responseHandler.setFileUtils(new FileUtils(""));

        URL url = getClass().getClassLoader().getResource("./");
        responseHandler.setFileUtils(new FileUtils(url.getPath()));

        Request request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.GET);
        request.setUrl("/image.jpeg");

        Assert.assertNotNull(request);
        Response response = responseHandler.getResponse(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getInitialResponseLine(), "HTTP/1.1 200 OK\r\n");
        Assert.assertNotNull(response.getMessage());
        Assert.assertEquals("image/jpeg", response.getContentType());

        request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.GET);
        request.setUrl("/image.gif");

        Assert.assertNotNull(request);
        response = responseHandler.getResponse(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getInitialResponseLine(), "HTTP/1.1 200 OK\r\n");
        Assert.assertNotNull(response.getMessage());
        Assert.assertEquals("image/gif", response.getContentType());

        request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.GET);
        request.setUrl("/image.png");

        Assert.assertNotNull(request);
        response = responseHandler.getResponse(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getInitialResponseLine(), "HTTP/1.1 200 OK\r\n");
        Assert.assertNotNull(response.getMessage());
        Assert.assertEquals("image/png", response.getContentType());
    }
}
