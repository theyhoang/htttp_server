package com.ticketmaster.server.output;

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
            if (response.getHeaders() != null) {
                for (String header : response.getHeaders()) {
                    outputStream.write((new String(header + "\r\n")).getBytes());
                }
            }

            if (response.getContentType() != null) {
                outputStream.write(
                    (new String("Content-Type: " + response.getContentType() + "\r\n")).getBytes());
            }
            if (response.getMessage() != null) {
                outputStream.write(
                    (new String("Content-Length: " + response.getMessage().length + "\r\n"))
                        .getBytes());
            }
            outputStream.write(CRLF.getBytes());
            if (response.getMessage() != null) {
                outputStream.write(response.getMessage());
                outputStream.write(CRLF.getBytes());
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
