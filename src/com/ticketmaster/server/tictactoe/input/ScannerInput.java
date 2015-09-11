package com.ticketmaster.server.tictactoe.input;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by yen.hoang on 11/3/14.
 */
public class ScannerInput implements Input{

    private Scanner in;

    public ScannerInput(InputStream source) {
        in = new Scanner(source);
    }

    @Override
    public int getInput() {
        while (!in.hasNextInt()) {
            System.out.println("Choice must be an int!");
            in.next();
        }
        int choice = in.nextInt();
        return choice;
    }
}
