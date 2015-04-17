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
//GET / HTTP/1.1
//Host: localhost:9090
//Connection: keep-alive
//Cache-Control: max-age=0
//Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//    User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36
//    Accept-Encoding: gzip, deflate, sdch
//    Accept-Language: en-US,en;q=0.8
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

        String testRequestString = "GET / HTTP/1.1\n";
        testRequestString += "User-Agent: curl/7.41.0\n";
        testRequestString += "Host: localhost:9090\n";
        testRequestString += "Accept: */*\n";


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
        String testRequestString = "GET / HTTP/1.1\n";
        testRequestString += "User-Agent: curl/7.41.0\n";
        testRequestString += "Host: localhost:9090\n";
        testRequestString += "Accept: */*\n";


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
        // TODO: finish test


    }
}
