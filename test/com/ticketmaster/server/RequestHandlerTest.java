package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
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

// TODO: redo tests
public class RequestHandlerTest {

//    GET / HTTP/1.1
//    User-Agent: curl/7.41.0
//    Host: localhost:9090
//    Accept: */*
//

//
//    GET /favicon.ico HTTP/1.1
//    Host: localhost:9090
//    Connection: keep-alive
//    Accept: */*
//User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36
//Accept-Encoding: gzip, deflate, sdch
//Accept-Language: en-US,en;q=0.8


    @Test
    public void testReadInput() {

        String testRequestString = "GET / HTTP/1.1\r\n";
        testRequestString += "User-Agent: curl/7.41.0\r\n";
        testRequestString += "Host: localhost:9090\r\n";
        testRequestString += "Accept: */*\r\n";


        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        RequestHandler requestHandler = new RequestHandler(new BufferedReader( new InputStreamReader(stream)));

        List<String> inputList = new ArrayList<>();
        try {
            inputList = requestHandler.readInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotEquals(inputList.size(), 0);
    }

    @Test
    public void testReadRequest() {
        String testRequestString = "GET / HTTP/1.1\r\n";
        testRequestString += "User-Agent: curl/7.41.0\r\n";
        testRequestString += "Host: localhost:9090\r\n";
        testRequestString += "Accept: */*\r\n";
        testRequestString += "\r\n";


        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        RequestHandler requestHandler = new RequestHandler(new BufferedReader( new InputStreamReader(stream)));

        Request request = null;
        try {
            request = requestHandler.readRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(request);
        Assert.assertEquals(request.getHttpMethod(), Method.GET);
        Assert.assertEquals(request.getHttpVersion(), "HTTP/1.1");
        Assert.assertEquals(request.getUrl(), "/");

        String testRequestString2 = "GET /favicon.ico HTTP/1.1\r\n";
        testRequestString2 += "Host: localhost:9090\r\n";
        testRequestString2 += "Connection: keep-alive\r\n";
        testRequestString2 += "Accept: */*\r\n";
        testRequestString2 += "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36\r\n";
        testRequestString2 += "Accept-Encoding: gzip, deflate, sdch\r\n";
        testRequestString2 += "Accept-Language: en-US,en;q=0.8\r\n";
        testRequestString2 += "\r\n";

        stream = new ByteArrayInputStream(testRequestString2.getBytes(StandardCharsets.UTF_8));
        requestHandler = new RequestHandler(new BufferedReader( new InputStreamReader(stream)));

        Request request2 = null;
        try {
            request2 = requestHandler.readRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(request2);
        Assert.assertEquals(request2.getHttpMethod(), Method.GET);
        Assert.assertEquals(request2.getHttpVersion(), "HTTP/1.1");
        Assert.assertEquals(request2.getUrl(), "/favicon.ico");
        Assert.assertEquals(request2.getHeaders().size(), 7);

        // TODO: finish test


    }
}
