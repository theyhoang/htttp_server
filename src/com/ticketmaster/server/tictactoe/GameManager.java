package com.ticketmaster.server.tictactoe;

import com.ticketmaster.server.tictactoe.player.ComputerPlayer;
import com.ticketmaster.server.tictactoe.player.Player;
import com.ticketmaster.server.tictactoe.player.PlayerType;

/**
 * Created by yen.hoang on 10/27/14.
 */
public class GameManager {

    private GameBoard gameBoard;

    private TurnManager turnManager;

    private Player computerPlayer;

    private boolean unbeatable;

    public GameManager(boolean unbeatable) {
        this.unbeatable = unbeatable;
        this.gameBoard = new GameBoard();
        this.turnManager = new TurnManager();
        this.computerPlayer = new ComputerPlayer(unbeatable, gameBoard);
    }

    public GameManager(boolean unbeatable, GameBoard board) {
        this.unbeatable = unbeatable;
        this.gameBoard = board;
        this.turnManager = new TurnManager();
        this.computerPlayer = new ComputerPlayer(unbeatable, gameBoard);
    }

    public int computerPickSpot() {
        return computerPlayer.pickSpot();
    }

    public Turn getTurn() {
        return turnManager.getTurn();
    }

    public void switchTurn() {
        turnManager.switchTurn();
    }

    public boolean checkForEndOfGame(PlayerType playerType) {
        boolean endGame = false;
        if (checkForWinner(playerType)) {
            if (playerType.equals(PlayerType.COMPUTER))
                System.out.println("Computer beat you!");
            else if (playerType.equals(PlayerType.HUMAN))
                System.out.println("You won!");

            endGame = true;
        } else {
            if (checkForDraw()) {
                System.out.println("Game is a draw!");
                endGame = true;
            }
        }

        return endGame;
    }

    private boolean checkForWinner(PlayerType playerType) {
        return gameBoard.checkForWinner(playerType);
    }

    private boolean checkForDraw() {
        return gameBoard.checkForDraw();
    }

    public boolean pickSpot(int spot, PlayerType playerType) {
        return gameBoard.pickSpot(spot, playerType);
    }

    public void resetGame() {
        this.gameBoard = new GameBoard();
        this.computerPlayer = new ComputerPlayer(unbeatable, gameBoard);
        turnManager.resetTurn();
    }

    public void resetGame(boolean unbeatable) {
        this.unbeatable = unbeatable;
        this.gameBoard = new GameBoard();
        this.computerPlayer = new ComputerPlayer(unbeatable, gameBoard);
        turnManager.resetTurn();
    }

    public void printBoard() {
        gameBoard.printBoard();
    }


    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public TurnManager getTurnManager() {
        return turnManager;
    }
}
