package com.ticketmaster.server.tictactoe;

import com.ticketmaster.server.tictactoe.input.Input;
import com.ticketmaster.server.tictactoe.input.ScannerInput;
import com.ticketmaster.server.tictactoe.player.ComputerPlayer;
import com.ticketmaster.server.tictactoe.player.HumanPlayer;
import com.ticketmaster.server.tictactoe.player.Player;
import com.ticketmaster.server.tictactoe.player.PlayerType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PlayerTest {


    private Player humanPlayer;
    private Player computerPlayer;
    private GameBoard gameBoard;

    @Before
    public void setUp() throws Exception {
        // TODO: input for humanplayer
        gameBoard = new GameBoard();
        computerPlayer = new ComputerPlayer(false, gameBoard);
    }

    @Test
    public void testPickSpot_ComputerRandom() throws Exception {
        int firstChoice = computerPlayer.pickSpot();
        Assert.assertTrue(firstChoice > 0 && firstChoice < 10);
    }

    // TODO: Add more test cases
    @Test
    public void testPickSpot_ComputerUnbeatable() throws Exception {
        gameBoard.pickSpot(1, PlayerType.HUMAN);
        computerPlayer = new ComputerPlayer(true, gameBoard);
        int choice = computerPlayer.pickSpot();
        gameBoard.pickSpot(choice, PlayerType.COMPUTER);
        gameBoard.pickSpot(2, PlayerType.HUMAN);
        choice = computerPlayer.pickSpot();
        gameBoard.pickSpot(choice, PlayerType.COMPUTER);
        // computer should block us
        Assert.assertEquals(3, choice);
        gameBoard.pickSpot(4, PlayerType.HUMAN);
        choice = computerPlayer.pickSpot();
        gameBoard.pickSpot(choice, PlayerType.COMPUTER);
        // computer should have picked 7 to win
        Assert.assertEquals(7, choice);
        Assert.assertTrue(gameBoard.checkForWinner(PlayerType.COMPUTER));
        gameBoard.printBoard();
    }

    @Test
    public void testPickSpot_ComputerUnbeatableRigged() throws Exception {
        computerPlayer = new ComputerPlayer(true, gameBoard);

        gameBoard.pickSpot(2, PlayerType.HUMAN);
        gameBoard.pickSpot(6, PlayerType.HUMAN);
        gameBoard.pickSpot(9, PlayerType.HUMAN);

        gameBoard.pickSpot(7, PlayerType.COMPUTER);
        gameBoard.pickSpot(8, PlayerType.COMPUTER);

        int choice = computerPlayer.pickSpot();
        // should go to 3 to block
        Assert.assertEquals(3, choice);
    }

    @Test
    public void testPickSpot_Human() throws Exception {
        String input = "1 2 3";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        Input in = new ScannerInput(is);
        humanPlayer = new HumanPlayer(in);

        Assert.assertEquals(1, humanPlayer.pickSpot());
        Assert.assertEquals(2, humanPlayer.pickSpot());
        Assert.assertEquals(3, humanPlayer.pickSpot());
    }
}
