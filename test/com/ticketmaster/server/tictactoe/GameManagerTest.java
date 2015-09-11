package com.ticketmaster.server.tictactoe;

import com.ticketmaster.server.tictactoe.player.PlayerType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameManagerTest {

    private GameManager gameManager;

    @Before
    public void setUp() throws Exception {
        gameManager = new GameManager(false);
    }

    @Test
    public void testComputerPickSpot() throws Exception {
        int choice = gameManager.computerPickSpot();
        Assert.assertTrue(choice > 0 && choice < 10);
    }

    @Test
    public void testGetTurn() throws Exception {
        Assert.assertEquals(Turn.HUMAN, gameManager.getTurn());
    }

    @Test
    public void testSwitchTurn() throws Exception {
        gameManager.switchTurn();
        Assert.assertEquals(Turn.COMPUTER, gameManager.getTurn());
    }

    @Test
    public void testCheckForEndOfGame() throws Exception {
        // check for human win
        gameManager.pickSpot(1, PlayerType.HUMAN);
        gameManager.pickSpot(2, PlayerType.HUMAN);
        Assert.assertFalse(gameManager.checkForEndOfGame(PlayerType.HUMAN));
        gameManager.pickSpot(3, PlayerType.HUMAN);

        Assert.assertFalse(gameManager.checkForEndOfGame(PlayerType.COMPUTER));
        Assert.assertTrue(gameManager.checkForEndOfGame(PlayerType.HUMAN));

        gameManager.resetGame();

        // check for computer win
        gameManager.pickSpot(1, PlayerType.COMPUTER);
        gameManager.pickSpot(4, PlayerType.COMPUTER);
        Assert.assertFalse(gameManager.checkForEndOfGame(PlayerType.COMPUTER));
        gameManager.pickSpot(7, PlayerType.COMPUTER);

        Assert.assertFalse(gameManager.checkForEndOfGame(PlayerType.HUMAN));
        Assert.assertTrue(gameManager.checkForEndOfGame(PlayerType.COMPUTER));

        gameManager.resetGame();

        // check for draw
        gameManager.pickSpot(1, PlayerType.COMPUTER);
        gameManager.pickSpot(3, PlayerType.HUMAN);
        gameManager.pickSpot(2, PlayerType.COMPUTER);
        gameManager.pickSpot(4, PlayerType.HUMAN);
        gameManager.pickSpot(5, PlayerType.COMPUTER);
        gameManager.pickSpot(8, PlayerType.HUMAN);
        gameManager.pickSpot(6, PlayerType.COMPUTER);
        gameManager.pickSpot(9, PlayerType.HUMAN);
        gameManager.pickSpot(7, PlayerType.COMPUTER);
        Assert.assertTrue(gameManager.checkForEndOfGame(PlayerType.COMPUTER));
        Assert.assertTrue(gameManager.checkForEndOfGame(PlayerType.HUMAN));
    }


    @Test
    public void testPickSpot() throws Exception {
        boolean result = gameManager.pickSpot(1, PlayerType.HUMAN);
        Assert.assertEquals(true, result);


        result = gameManager.pickSpot(1, PlayerType.COMPUTER);
        Assert.assertEquals(false, result);

        result = gameManager.pickSpot(1, PlayerType.HUMAN);
        Assert.assertEquals(false, result);
    }
}
