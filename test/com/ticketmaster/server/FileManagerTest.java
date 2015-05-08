package com.ticketmaster.server;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

/**
 * Created by yen.hoang on 5/8/15.
 */
public class FileManagerTest {

    private FileManager fileManager;

    @Before
    public void setUp() {
        URL url = getClass().getClassLoader().getResource("./");
        fileManager = new FileManager(url.getPath());
    }

    @Test
    public void testResourceExistsInPath() {
        Assert.assertTrue(fileManager.resourceExistsInPath("/file1"));
        Assert.assertFalse(fileManager.resourceExistsInPath("/dontexist"));
    }

    @Test
    public void testIsFile() {
        Assert.assertTrue(fileManager.isFile("/file1"));
    }


    // TODO: How to test?
//    @Test
    public void testIsDirectory() {
        Assert.assertTrue(fileManager.isDirectory("/folderTest"));
    }

    @Test
    public void testGetFileContentType() {
        Assert.assertEquals("image/jpeg", fileManager.getFileContentType("/image.jpeg"));
    }

    @Test
    public void testFileContent() {
        Assert.assertNotNull(fileManager.getFileContent("/file1"));
    }
}
