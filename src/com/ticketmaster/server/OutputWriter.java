package com.ticketmaster.server;

import com.ticketmaster.server.model.Response;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by yen.hoang on 6/1/15.
 */
public class OutputWriter {

    private final String CRLF = "\r\n";

    private DataOutputStream outputStream;

    public OutputWriter(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    // TODO: output as stream versus keeping file in memory
    public void outputResponse(Response response) {
        // write out response
        try {
            outputStream.write(response.getInitialResponseLine().getBytes());
            // TODO : out.write(rest of headers)
            outputStream.write((new String("Content-Type: " + response.getContentType() + "\r\n")).getBytes());
            outputStream.write(CRLF.getBytes());
            outputStream.write(response.getMessage());
            outputStream.write(CRLF.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
