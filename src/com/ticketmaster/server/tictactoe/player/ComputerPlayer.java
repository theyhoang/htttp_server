package com.ticketmaster.server.tictactoe.player;

import com.ticketmaster.server.tictactoe.GameBoard;
import com.ticketmaster.server.tictactoe.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yen.hoang on 10/20/14.
 */
public class ComputerPlayer implements Player {

    private boolean unbeatable = false;
    private GameBoard board;
    private int choice;

    public ComputerPlayer(boolean unbeatable, GameBoard board) {
        this.unbeatable = unbeatable;
        this.board = board;
    }

    public List<Move> getAvailableMoves(GameBoard board) {
        return board.getAvailableMoves();
    }

    public int pickSpot() {

        // reset choice
        choice = 0;

        if (unbeatable) {
            // TODO : TEST
            GameBoard copyOfBoard = new GameBoard(board);
            minimax(copyOfBoard, PlayerType.COMPUTER, 0);

        } else {
            // random picking
            // implement logic for computer picking
            Random rand = new Random();

            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive
            choice = rand.nextInt((9 - 1) + 1) + 1;
        }

        System.out.println("Computer Choice: " + choice);
        return choice;
    }

    public int minimax(GameBoard copyOfBoard, PlayerType playerType, int depth) {
        // check if game is over
        if (copyOfBoard.checkForWinner(PlayerType.HUMAN)) {
            return 10 - depth;
        } else if (copyOfBoard.checkForWinner(PlayerType.COMPUTER)) {
            return depth - 10;
        } else if (copyOfBoard.checkForDraw()) {
            return 0;
        }

        depth++;

        List<Move> moves = new ArrayList<Move>();

        // populate scores array, recursing as needed
        List<Move> availableMoves = getAvailableMoves(copyOfBoard);
        for (Move possibleMove : availableMoves) {
            // possibleGame = game.getNewState(possibleMove) -> pickSpot(possibleMove.getSpot)
            GameBoard possibleGame = new GameBoard(copyOfBoard);
            possibleGame.pickSpot(possibleMove.getSpot(), playerType);

            if (playerType.equals(PlayerType.HUMAN)) {
                possibleMove.setScore(minimax(possibleGame, PlayerType.COMPUTER, depth));
            } else if (playerType.equals(PlayerType.COMPUTER)) {
                possibleMove.setScore(minimax(possibleGame, PlayerType.HUMAN, depth));
            }
            moves.add(possibleMove);
        }


        // do min/max calculation

        int choiceIdx;
        if (playerType.equals(PlayerType.COMPUTER)) {
            if (!moves.isEmpty()) {
                choiceIdx = 0;
                for (Move move : moves) {
                    if (move.getScore() < moves.get(choiceIdx).getScore()) {
                        choiceIdx = moves.indexOf(move);
                    }
                }

                choice = moves.get(choiceIdx).getSpot();
                return moves.get(choiceIdx).getScore();
            }
        } else {
            if (!moves.isEmpty()) {
                choiceIdx = 0;
                for (Move move : moves) {
                    if (move.getScore() > moves.get(choiceIdx).getScore()) {
                        choiceIdx = moves.indexOf(move);
                    }
                }

                return moves.get(choiceIdx).getScore();
            }
        }
        return 0;
    }
}
