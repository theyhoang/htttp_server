package com.ticketmaster.server;

import com.ticketmaster.server.model.Request;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yen.hoang on 3/25/15.
 */
public class ResponseHandlerTest {
    @Test
    public void testConstructResponse() {
        //        String testResponseString = "HTTP/1.0 200 OK\n";
        //        testResponseString += "Content-Type: text/html\n";
        //        testResponseString += "Server: Bot\n";
        //        testResponseString += "\n";
        //        testResponseString += "<H1>Welcome to Test Request</H1>";

        String testRequestString = "GET /path/file.html HTTP/1.0\n";
        testRequestString += "From: someuser@jmarshall.com\n";
        testRequestString += "User-Agent: HTTPTool/1.0\n";


        DataOutputStream out = new DataOutputStream(System.out);

        ResponseHandler responseHandler = new ResponseHandler(out);
//        List<String> paths = new ArrayList<String>();
//        paths.add("/path/file.html");
//        httpHandler.setPaths(paths);
//        Request request = httpHandler.readRequest();
//
//        Assert.assertNotNull(request);
//        String response = httpHandler.getResponse(request);
//
//        Assert.assertEquals("HTTP/1.0 200 OK", response);
        Assert.assertNotNull(null);
    }
}
