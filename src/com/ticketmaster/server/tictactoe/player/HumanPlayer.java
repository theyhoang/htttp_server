package com.ticketmaster.server.tictactoe.player;

import com.ticketmaster.server.tictactoe.input.Input;

/**
 * Created by yen.hoang on 10/27/14.
 */
public class HumanPlayer implements Player {

    // TODO: open up input options/mechanisms
    // Wrap this into interface that accepts input, interaction strategy
    private Input input;

    public HumanPlayer(Input input) {
        this.input = input;
    }

    @Override
    public int pickSpot() {
        System.out.println("Pick a spot: ");
        int choice = input.getInput();
        System.out.println("Your Choice: " + choice);
        return choice;
    }
}
