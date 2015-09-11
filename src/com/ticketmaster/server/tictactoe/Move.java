package com.ticketmaster.server.tictactoe;

/**
 * Created by yen.hoang on 10/20/14.
 */
public class Move {
    private int spot;
    private int score;

    public Move(int spot, int score) {
        this.spot = spot;
        this.score = score;
    }

    public int getSpot() {
        return spot;
    }

    public void setSpot(int spot) {
        this.spot = spot;
    }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }
}
