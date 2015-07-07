package com.ticketmaster.server;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * Created by yen.hoang on 5/8/15.
 */
public class FileUtils {

    public static String publicDirPath;

//    FileUtils(String publicDirPath) {
//        this.publicDirPath = publicDirPath;
//    }

    public static boolean resourceExistsInPath(String path) {
        File file = new File(publicDirPath + path);
        return file.exists();
    }

    public static boolean isFile(String path) {
        File file = new File(publicDirPath + path);
        return file.isFile();
    }

    public static boolean isDirectory(String path) {
        File file = new File(publicDirPath + path);
        return file.isDirectory();
    }

    public static String getFileContentType(String path) {

        String contentType = "text/html";
        File file = new File(publicDirPath + path);
        // TODO: abstract to method for MIME Types
        // TODO: look into library that can retrieve MIME type
        if (file.getName().endsWith(".jpeg")) {
            contentType = "image/jpeg";
        } else if (file.getName().endsWith(".gif")) {
            contentType = "image/gif";
        } else if (file.getName().endsWith(".png")) {
            contentType = "image/png";
        }

        return contentType;
    }

    public static byte[] getFileContent(String path) {
        byte[] content = null;
        try {
            File file = new File(publicDirPath + path);
            content = Files.toByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
