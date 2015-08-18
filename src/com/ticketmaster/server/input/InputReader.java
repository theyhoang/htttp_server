package com.ticketmaster.server.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yen.hoang on 6/1/15.
 */
public class InputReader {

    private BufferedReader input;

    public InputReader(BufferedReader input) {
        this.input = input;
    }

    public List<String> readInput() {
        List<String> inputList = new ArrayList<>();
        String userInput;
        boolean hasMessage = false;
        int length = -1;

        try {
            while ((userInput = input.readLine()) != null) {
                if (userInput.startsWith("Content-Length: ")) { // get the message
                    hasMessage = true;
                    // content-length
                    int index = userInput.indexOf(':') + 1;
                    String len = userInput.substring(index).trim();
                    length = Integer.parseInt(len);
                }
                //TODO: this was blocking
//                System.out.println(userInput);
                inputList.add(userInput);
                if (userInput.isEmpty()) {
                    break;
                }
            }
            if (hasMessage) {
//                System.out.println("READING MESSAGE");
                // if there is Message body, go in to this loop
                StringBuilder body = new StringBuilder();
                if (length > 0) {
                    int read;
                    while ((read = input.read()) != -1) {
                        body.append((char) read);
                        if (body.length() == length)
                            break;
                    }
                }

                String message = body.toString();
                System.out.println(message);
                inputList.add(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }
}
