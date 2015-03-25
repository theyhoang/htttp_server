package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by yen.hoang on 3/25/15.
 */
public class RequestHandlerTest {


    @Test
    public void testReadRequest() {

        String testRequestString = "GET /path/file.html HTTP/1.0\n";
        testRequestString += "From: someuser@jmarshall.com\n";
        testRequestString += "User-Agent: HTTPTool/1.0\n";


        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));

        RequestHandler requestHandler = new RequestHandler(new BufferedReader( new InputStreamReader(stream)));
        Request request = requestHandler.readRequest();
        Assert.assertEquals(request.getHttpMethod(), Method.GET);
        Assert.assertEquals(request.getUrl(), "/path/file.html");
        Assert.assertEquals(request.getHttpVersion(), "HTTP/1.0");
    }
}
