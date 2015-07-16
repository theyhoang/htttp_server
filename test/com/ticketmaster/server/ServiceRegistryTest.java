package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.response.ResponseHandler;
import com.ticketmaster.server.response.ServiceRegistry;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

/**
 * Created by yen.hoang on 7/16/15.
 */
public class ServiceRegistryTest {
    // TODO
    @Test
    public void testConstructResponse() {

        ServiceRegistry serviceRegistry = new ServiceRegistry();
        FileUtils.publicDirPath = "";

        Request request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.GET);
        request.setUrl("/");

        Assert.assertNotNull(request);

        Response response = serviceRegistry.generateResponse(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getInitialResponseLine(), "HTTP/1.1 200 OK\r\n");
    }

    @Test
    public void testGetDirectoryPage() {
        ServiceRegistry serviceRegistry = new ServiceRegistry();

        URL url = getClass().getClassLoader().getResource("./");
        FileUtils.publicDirPath = url.getPath();


        // TODO
    }

    @Test
    public void testGetFileContent() {
        ServiceRegistry serviceRegistry = new ServiceRegistry();
        FileUtils.publicDirPath = "";

        URL url = getClass().getClassLoader().getResource("./");
        FileUtils.publicDirPath = url.getPath();

        Request request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.GET);
        request.setUrl("/file1");

        Assert.assertNotNull(request);
        Response response = serviceRegistry.generateResponse(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getInitialResponseLine(), "HTTP/1.1 200 OK\r\n");
        Assert.assertNotNull(response.getMessage());
    }

    @Test
    public void testGetImageFiles() {
        ServiceRegistry serviceRegistry = new ServiceRegistry();
        FileUtils.publicDirPath = "";

        URL url = getClass().getClassLoader().getResource("./");
        FileUtils.publicDirPath = url.getPath();

        Request request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.GET);
        request.setUrl("/image.jpeg");

        Assert.assertNotNull(request);
        Response response = serviceRegistry.generateResponse(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getInitialResponseLine(), "HTTP/1.1 200 OK\r\n");
        Assert.assertNotNull(response.getMessage());
        Assert.assertEquals("image/jpeg", response.getContentType());

        request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.GET);
        request.setUrl("/image.gif");

        Assert.assertNotNull(request);
        response = serviceRegistry.generateResponse(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getInitialResponseLine(), "HTTP/1.1 200 OK\r\n");
        Assert.assertNotNull(response.getMessage());
        Assert.assertEquals("image/gif", response.getContentType());

        request = new Request();
        request.setHttpVersion("HTTP/1.1");
        request.setHttpMethod(Method.GET);
        request.setUrl("/image.png");

        Assert.assertNotNull(request);
        response = serviceRegistry.generateResponse(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getInitialResponseLine(), "HTTP/1.1 200 OK\r\n");
        Assert.assertNotNull(response.getMessage());
        Assert.assertEquals("image/png", response.getContentType());
    }
}
