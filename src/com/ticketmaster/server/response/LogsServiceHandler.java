package com.ticketmaster.server.response;

import com.ticketmaster.server.Resources;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by yen.hoang on 7/23/15.
 */
public class LogsServiceHandler implements ServiceHandler {


    private final String USERNAME = "admin";
    private final String PASSWORD = "hunter2";

    @Override public Response GET(Request request) {
        Response response = new Response();
        response.setHttpVersion(HTTP_VERSION);

        // check basic auth
        // Authorization: Basic YWRtaW46aHVudGVyMg==
        List<String> headers = request.getHeaders();
        String basicAuthHeader = null;
        for (String header : headers) {
            if (header.startsWith("Authorization: Basic")) {
                basicAuthHeader = header;
            }
        }

        if (basicAuthHeader != null) {
            String[] basicAuthStrings = basicAuthHeader.split(" ");
            String usernamePassword = basicAuthStrings[2];
            byte[] decoded = Base64.decodeBase64(usernamePassword);
            String decodedString = null;
            try {
                decodedString = new String(decoded, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO: error handling
                e.printStackTrace();
            }

            String[] decodedStrings = decodedString.split(":");
            String username = decodedStrings[0];
            String password = decodedStrings[1];

            // check if username and password is valid
            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                response.setStatusCode(Response.STATUS_CODE_OK);
                // return logs
                StringBuilder messageSb = new StringBuilder();
                for (String log : Resources.getLogs()) {
                    messageSb.append(log + "\r\n");
                }
                response.setMessage((messageSb.toString()).getBytes());

            } else {
                response.setStatusCode(Response.STATUS_CODE_UNAUTHORIZED);
                response.setMessage((new String("Authentication required")).getBytes());
            }

        } else {
            response.setStatusCode(Response.STATUS_CODE_UNAUTHORIZED);
            response.setMessage((new String("Authentication required")).getBytes());
        }

        return response;
    }

    @Override public Response PUT(Request request) {
        return null;
    }

    @Override public Response POST(Request request) {
        return null;
    }

    @Override public Response DELETE(Request request) {
        return null;
    }

    @Override public Response HEAD(Request request) {
        return null;
    }
}
