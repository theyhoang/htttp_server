package com.ticketmaster.server.tictactoe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TurnManagerTest {

    private TurnManager turnManager;

    @Before
    public void setUp() throws Exception {
        turnManager = new TurnManager();

    }

    @Test
    public void testGetTurn() throws Exception {
        Assert.assertEquals(Turn.HUMAN, turnManager.getTurn());
    }

    @Test
    public void testSwitchTurn() throws Exception {
        turnManager.switchTurn();
        Assert.assertEquals(Turn.COMPUTER, turnManager.getTurn());

    }

    @Test
    public void testResetTurn() throws Exception {
        turnManager.resetTurn();
        Assert.assertEquals(Turn.HUMAN, turnManager.getTurn());

    }
}
