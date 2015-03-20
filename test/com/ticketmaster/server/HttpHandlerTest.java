package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HttpHandlerTest {

    @Test
    public void testReadRequest() {

        String testRequestString = "GET /path/file.html HTTP/1.0\n";
        testRequestString += "From: someuser@jmarshall.com\n";
        testRequestString += "User-Agent: HTTPTool/1.0\n";


        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        DataOutputStream out = new DataOutputStream(System.out);

        HttpHandler httpHandler = new HttpHandler(new BufferedReader( new InputStreamReader(stream)),out);
        Request request = httpHandler.readRequest();
        Assert.assertEquals(request.getHttpMethod(), Method.GET);
        Assert.assertEquals(request.getUrl(), "/path/file.html");
        Assert.assertEquals(request.getHttpVersion(), "HTTP/1.0");
    }

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


        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        DataOutputStream out = new DataOutputStream(System.out);

        HttpHandler httpHandler = new HttpHandler(new BufferedReader( new InputStreamReader(stream)),out);
        List<String> paths = new ArrayList<String>();
        paths.add("/path/file.html");
        httpHandler.setPaths(paths);
        Request request = httpHandler.readRequest();

        Assert.assertNotNull(request);
        String response = httpHandler.getResponse(request);

        Assert.assertEquals("HTTP/1.0 200 OK", response);
    }

}
