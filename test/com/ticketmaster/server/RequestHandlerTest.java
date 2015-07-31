package com.ticketmaster.server;

import com.ticketmaster.server.input.InputReader;
import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.request.RequestHandler;
import com.ticketmaster.server.response.ServiceRegistry;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

//    GET /favicon.ico HTTP/1.1
//    Host: localhost:9090
//    Connection: keep-alive
//    Accept: */*
//User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36
//Accept-Encoding: gzip, deflate, sdch
//Accept-Language: en-US,en;q=0.8


    @Test
    public void testParseForMethodTypeGet() throws IOException {

        String testRequestString = "GET / HTTP/1.1\r\n";
        testRequestString += "User-Agent: curl/7.41.0\r\n";
        testRequestString += "Host: localhost:9090\r\n";
        testRequestString += "Accept: */*\r\n";


        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        InputReader inputReader = new InputReader(new BufferedReader( new InputStreamReader(stream)));
        List<String> inputList = inputReader.readInput();
        RequestHandler requestHandler = new RequestHandler();
        Method methodType = requestHandler.parseForMethodType(inputList);


        Assert.assertEquals(Method.GET, methodType);
    }

    @Test
    public void testParseForMethodTypeInvalidType() throws IOException {
        String testRequestString = "BAD / HTTP/1.1\r\n";
        testRequestString += "User-Agent: curl/7.41.0\r\n";
        testRequestString += "Host: localhost:9090\r\n";
        testRequestString += "Accept: */*\r\n";


        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        InputReader inputReader = new InputReader(new BufferedReader( new InputStreamReader(stream)));
        List<String> inputList = inputReader.readInput();
        RequestHandler requestHandler = new RequestHandler();
        Method methodType = requestHandler.parseForMethodType(inputList);
    }

    // TODO: add more test scenarios


    @Test
    public void testReadRequest() throws IOException {
        String testRequestString = "GET / HTTP/1.1\r\n";
        testRequestString += "User-Agent: curl/7.41.0\r\n";
        testRequestString += "Host: localhost:9090\r\n";
        testRequestString += "Accept: */*\r\n";
        testRequestString += "\r\n";


        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        InputReader inputReader = new InputReader(new BufferedReader( new InputStreamReader(stream)));
        RequestHandler requestHandler = new RequestHandler();
        Request request = requestHandler.readRequest(inputReader.readInput());


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
        inputReader = new InputReader(new BufferedReader( new InputStreamReader(stream)));
        requestHandler = new RequestHandler();
        Request request2 = requestHandler.readRequest(inputReader.readInput());


        Assert.assertNotNull(request2);
        Assert.assertEquals(request2.getHttpMethod(), Method.GET);
        Assert.assertEquals(request2.getHttpVersion(), "HTTP/1.1");
        Assert.assertEquals(request2.getUrl(), "/favicon.ico");
        Assert.assertEquals(request2.getHeaders().size(), 7);

        testRequestString2 = "POST /favicon.ico HTTP/1.1\r\n";
        testRequestString2 += "Host: localhost:9090\r\n";
        testRequestString2 += "Connection: keep-alive\r\n";
        testRequestString2 += "Accept: */*\r\n";
        testRequestString2 += "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36\r\n";
        testRequestString2 += "Accept-Encoding: gzip, deflate, sdch\r\n";
        testRequestString2 += "Accept-Language: en-US,en;q=0.8\r\n";
        testRequestString2 += "Content-Length: 10\r\n";
        testRequestString2 += "\r\n";
        testRequestString2 += "home=Cosby";

        stream = new ByteArrayInputStream(testRequestString2.getBytes(StandardCharsets.UTF_8));
        inputReader = new InputReader(new BufferedReader( new InputStreamReader(stream)));
        requestHandler = new RequestHandler();
        request2 = requestHandler.readRequest(inputReader.readInput());


        Assert.assertNotNull(request2);
        Assert.assertEquals(request2.getHttpMethod(), Method.POST);
        Assert.assertEquals(request2.getHttpVersion(), "HTTP/1.1");
        Assert.assertEquals(request2.getUrl(), "/favicon.ico");
        Assert.assertEquals(request2.getHeaders().size(), 8);
        Assert.assertEquals(request2.getMessage(), "home=Cosby");

    }

    @Test
    // TODO: move this to tests for responses and add test cases
    public void testPartialContentRequest() throws IOException {
        URL url = getClass().getClassLoader().getResource("./");
        FileUtils.publicDirPath = url.getPath();
        String testRequestString = "GET /partial_content.txt HTTP/1.1\r\n";
        testRequestString += "User-Agent: curl/7.41.0\r\n";
        testRequestString += "Host: localhost:9090\r\n";
        testRequestString += "Range: bytes=0-4\r\n";
        testRequestString += "\r\n";


        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        InputReader inputReader = new InputReader(new BufferedReader( new InputStreamReader(stream)));
        RequestHandler requestHandler = new RequestHandler();
        Request request = requestHandler.readRequest(inputReader.readInput());

        ServiceRegistry serviceRegistry = ServiceRegistry.initialize();
        Response response = serviceRegistry.generateResponse(request);

        Assert.assertNotNull(response);

        testRequestString = "GET /partial_content.txt HTTP/1.1\r\n";
        testRequestString += "User-Agent: curl/7.41.0\r\n";
        testRequestString += "Host: localhost:9090\r\n";
        testRequestString += "Range: bytes=-6\r\n";
        testRequestString += "\r\n";

        stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        inputReader = new InputReader(new BufferedReader( new InputStreamReader(stream)));
        request = requestHandler.readRequest(inputReader.readInput());
        response = serviceRegistry.generateResponse(request);
        Assert.assertNotNull(response);
    }



    @Test
    public void testPatchRequest() throws IOException {
//        PATCH /file.txt HTTP/1.1
//        Host: www.example.com
//        Content-type: application/example
//        If-Match: "e0023aa4e"
//        Content-Length: 100

        URL url = getClass().getClassLoader().getResource("./");
        FileUtils.publicDirPath = url.getPath();
        String message = "patched content";
        String testRequestString = "PATCH /test.txt HTTP/1.1\r\n";
        testRequestString += "User-Agent: curl/7.41.0\r\n";
        testRequestString += "Host: localhost:9090\r\n";
        testRequestString += "Content-Length: " + message.getBytes().length + "\r\n";
        testRequestString += "If-Match: \"dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec\"\r\n";
        testRequestString += "\r\n";
        testRequestString += message;


        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        InputReader inputReader = new InputReader(new BufferedReader( new InputStreamReader(stream)));
        RequestHandler requestHandler = new RequestHandler();
        Request request = requestHandler.readRequest(inputReader.readInput());

        Assert.assertTrue(request.getHttpMethod() == Method.PATCH);

        ServiceRegistry serviceRegistry = ServiceRegistry.initialize();
        Response response = serviceRegistry.generateResponse(request);

        Assert.assertNotNull(response);
        // TODO: finish test for response
    }

    @Test
    public void testDecodeParameters() throws IOException {
        String path = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";

        String testRequestString = "GET " + path + " HTTP/1.1\r\n";
        testRequestString += "User-Agent: curl/7.41.0\r\n";
        testRequestString += "Host: localhost:9090\r\n";
        testRequestString += "Range: bytes=0-4\r\n";
        testRequestString += "\r\n";


        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        InputReader inputReader = new InputReader(new BufferedReader( new InputStreamReader(stream)));
        RequestHandler requestHandler = new RequestHandler();
        Request request = requestHandler.readRequest(inputReader.readInput());

        Assert.assertNotNull(request);

    }
}
