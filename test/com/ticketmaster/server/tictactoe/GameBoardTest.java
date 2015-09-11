package com.ticketmaster.server.tictactoe;


import com.ticketmaster.server.tictactoe.player.PlayerType;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {

    private GameBoard gameBoard;

    @Before
    public void setUp() throws Exception {
        gameBoard = new GameBoard();
    }

    @Test
    public void testCheckForWinner() {

        // test horizontal
        gameBoard = new GameBoard();
        gameBoard.pickSpot(1, PlayerType.HUMAN);
        gameBoard.pickSpot(2, PlayerType.HUMAN);
        Assert.assertEquals(false, gameBoard.checkForWinner(PlayerType.HUMAN));
        gameBoard.pickSpot(3, PlayerType.HUMAN);
        Assert.assertEquals(true, gameBoard.checkForWinner(PlayerType.HUMAN));
        gameBoard = new GameBoard();
        gameBoard.pickSpot(4, PlayerType.COMPUTER);
        gameBoard.pickSpot(5, PlayerType.COMPUTER);
        Assert.assertEquals(false, gameBoard.checkForWinner(PlayerType.COMPUTER));
        gameBoard.pickSpot(6, PlayerType.COMPUTER);
        Assert.assertEquals(true, gameBoard.checkForWinner(PlayerType.COMPUTER));
        gameBoard = new GameBoard();
        gameBoard.pickSpot(7, PlayerType.HUMAN);
        gameBoard.pickSpot(8, PlayerType.HUMAN);
        Assert.assertEquals(false, gameBoard.checkForWinner(PlayerType.HUMAN));
        gameBoard.pickSpot(9, PlayerType.HUMAN);
        Assert.assertEquals(true, gameBoard.checkForWinner(PlayerType.HUMAN));

        // test vertical
        gameBoard = new GameBoard();
        gameBoard.pickSpot(1, PlayerType.HUMAN);
        gameBoard.pickSpot(4, PlayerType.HUMAN);
        Assert.assertEquals(false, gameBoard.checkForWinner(PlayerType.HUMAN));
        gameBoard.pickSpot(7, PlayerType.HUMAN);
        Assert.assertEquals(true, gameBoard.checkForWinner(PlayerType.HUMAN));
        gameBoard = new GameBoard();
        gameBoard.pickSpot(2, PlayerType.COMPUTER);
        gameBoard.pickSpot(5, PlayerType.COMPUTER);
        Assert.assertEquals(false, gameBoard.checkForWinner(PlayerType.COMPUTER));
        gameBoard.pickSpot(8, PlayerType.COMPUTER);
        Assert.assertEquals(true, gameBoard.checkForWinner(PlayerType.COMPUTER));
        gameBoard = new GameBoard();
        gameBoard.pickSpot(3, PlayerType.HUMAN);
        gameBoard.pickSpot(6, PlayerType.HUMAN);
        Assert.assertEquals(false, gameBoard.checkForWinner(PlayerType.HUMAN));
        gameBoard.pickSpot(9, PlayerType.HUMAN);
        Assert.assertEquals(true, gameBoard.checkForWinner(PlayerType.HUMAN));

        //test diagonal
        gameBoard = new GameBoard();
        gameBoard.pickSpot(1, PlayerType.HUMAN);
        gameBoard.pickSpot(5, PlayerType.HUMAN);
        Assert.assertEquals(false, gameBoard.checkForWinner(PlayerType.HUMAN));
        gameBoard.pickSpot(9, PlayerType.HUMAN);
        Assert.assertEquals(true, gameBoard.checkForWinner(PlayerType.HUMAN));
        gameBoard = new GameBoard();
        gameBoard.pickSpot(3, PlayerType.COMPUTER);
        gameBoard.pickSpot(5, PlayerType.COMPUTER);
        Assert.assertEquals(false, gameBoard.checkForWinner(PlayerType.COMPUTER));
        gameBoard.pickSpot(7, PlayerType.COMPUTER);
        Assert.assertEquals(true, gameBoard.checkForWinner(PlayerType.COMPUTER));

    }

    @Test
    public void testCheckForDraw() {
        gameBoard.pickSpot(1, PlayerType.COMPUTER);
        gameBoard.pickSpot(3, PlayerType.HUMAN);
        gameBoard.pickSpot(2, PlayerType.COMPUTER);
        gameBoard.pickSpot(4, PlayerType.HUMAN);
        gameBoard.pickSpot(5, PlayerType.COMPUTER);
        gameBoard.pickSpot(8, PlayerType.HUMAN);
        gameBoard.pickSpot(6, PlayerType.COMPUTER);
        gameBoard.pickSpot(9, PlayerType.HUMAN);
        gameBoard.pickSpot(7, PlayerType.COMPUTER);

        Assert.assertEquals(false, gameBoard.checkForWinner(PlayerType.COMPUTER));
        Assert.assertEquals(false, gameBoard.checkForWinner(PlayerType.HUMAN));
        Assert.assertEquals(true, gameBoard.checkForDraw());

    }

    @Test
    public void testPickSpot() {

        boolean result = gameBoard.pickSpot(1, PlayerType.HUMAN);
        Assert.assertEquals(true, result);


        result = gameBoard.pickSpot(1, PlayerType.COMPUTER);
        Assert.assertEquals(false, result);

    }
}
