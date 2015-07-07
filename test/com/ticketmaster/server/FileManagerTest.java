package com.ticketmaster.server;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

/**
 * Created by yen.hoang on 5/8/15.
 */
public class FileManagerTest {

    private FileUtils fileUtils;

    @Before
    public void setUp() {
        URL url = getClass().getClassLoader().getResource("./");
        fileUtils = new FileUtils(url.getPath());
    }

    @Test
    public void testResourceExistsInPath() {
        Assert.assertTrue(fileUtils.resourceExistsInPath("/file1"));
        Assert.assertFalse(fileUtils.resourceExistsInPath("/dontexist"));
    }

    @Test
    public void testIsFile() {
        Assert.assertTrue(fileUtils.isFile("/file1"));
    }


    // TODO: How to test?
//    @Test
    public void testIsDirectory() {
        Assert.assertTrue(fileUtils.isDirectory("/folderTest"));
    }

    @Test
    public void testGetFileContentType() {
        Assert.assertEquals("image/jpeg", fileUtils.getFileContentType("/image.jpeg"));
    }

    @Test
    public void testFileContent() {
        Assert.assertNotNull(fileUtils.getFileContent("/file1"));
    }
}
