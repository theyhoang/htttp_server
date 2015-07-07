package com.ticketmaster.server;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

/**
 * Created by yen.hoang on 5/8/15.
 */
public class FileManagerTest {

    @Before
    public void setUp() {
        URL url = getClass().getClassLoader().getResource("./");
        FileUtils.publicDirPath = url.getPath();
    }

    @Test
    public void testResourceExistsInPath() {
        Assert.assertTrue(FileUtils.resourceExistsInPath("/file1"));
        Assert.assertFalse(FileUtils.resourceExistsInPath("/dontexist"));
    }

    @Test
    public void testIsFile() {
        Assert.assertTrue(FileUtils.isFile("/file1"));
    }


    // TODO: How to test?
//    @Test
    public void testIsDirectory() {
        Assert.assertTrue(FileUtils.isDirectory("/folderTest"));
    }

    @Test
    public void testGetFileContentType() {
        Assert.assertEquals("image/jpeg", FileUtils.getFileContentType("/image.jpeg"));
    }

    @Test
    public void testFileContent() {
        Assert.assertNotNull(FileUtils.getFileContent("/file1"));
    }
}
