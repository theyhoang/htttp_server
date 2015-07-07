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
        try {
            while ((userInput = input.readLine()) != null) {
                System.out.println(userInput);
                inputList.add(userInput);
                if (userInput.isEmpty()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }
}
