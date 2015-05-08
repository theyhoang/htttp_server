package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yen.hoang on 3/25/15.
 */

// TODO: check tests
public class ResponseHandlerTest {
    @Test
    public void testConstructResponse() {

        DataOutputStream out = new DataOutputStream(System.out);
        ResponseHandler responseHandler = new ResponseHandler(out);
        responseHandler.setFileManager(new FileManager(""));

        Request request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.GET.name());
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
        responseHandler.setFileManager(new FileManager(""));

        // TODO: get message and check for directory page
    }

    @Test
    public void testGetFileContent() {
        DataOutputStream out = new DataOutputStream(System.out);
        ResponseHandler responseHandler = new ResponseHandler(out);
        responseHandler.setFileManager(new FileManager(""));

        // TODO: get message and check for file content
    }
}
