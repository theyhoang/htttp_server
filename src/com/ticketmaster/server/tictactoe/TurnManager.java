package com.ticketmaster.server.tictactoe;

/**
 * Created by yen.hoang on 10/27/14.
 */
public class TurnManager {

    private Turn turn;

    public TurnManager() {
        turn = Turn.HUMAN;
    }

    public Turn getTurn() {
        return turn;
    }

    public void switchTurn() {
        if (turn.equals(Turn.HUMAN)) {
            turn = Turn.COMPUTER;
        } else if (turn.equals(Turn.COMPUTER)) {
            turn = Turn.HUMAN;
        }
    }

    public void resetTurn() {
        turn = Turn.HUMAN;
    }
}
