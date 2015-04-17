package com.ticketmaster.server;

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
        String testRequestString = "GET / HTTP/1.1\r\n";
        testRequestString += "Host: localhost:9090\r\n";
        testRequestString += "Connection: keep-alive\r\n";
        testRequestString += "Accept: */*\r\n";
        testRequestString += "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36\r\n";
        testRequestString += "Accept-Encoding: gzip, deflate, sdch\r\n";
        testRequestString += "Accept-Language: en-US,en;q=0.8\r\n";
        testRequestString += "\r\n";


        DataOutputStream out = new DataOutputStream(System.out);
        InputStream stream = new ByteArrayInputStream(testRequestString.getBytes(StandardCharsets.UTF_8));
        PrintWriter printWriter = new PrintWriter(out, true);
        ResponseHandler responseHandler = new ResponseHandler(printWriter);
        responseHandler.setPublicDirPath("");
        RequestHandler requestHandler = new RequestHandler(new BufferedReader( new InputStreamReader(stream)));
        Request request = null;
        try {
            request = requestHandler.readRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(request);
        Response response = responseHandler.getResponse(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getInitialResponseLine(), "HTTP/1.1 200 OK\r\n");
    }
}
