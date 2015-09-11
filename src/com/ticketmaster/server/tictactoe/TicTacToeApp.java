package com.ticketmaster.server.tictactoe;

import com.ticketmaster.server.tictactoe.player.PlayerType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yen.hoang on 9/3/15.
 */
public class TicTacToeApp {

    private List<GameManager> gameManagers = new ArrayList<GameManager>();
    private final AtomicInteger counter = new AtomicInteger();
    private List<GameBoard> gameBoards = new ArrayList<GameBoard>();

    public void newGame() {
        GameBoard newGame = new GameBoard(counter.incrementAndGet());
        gameBoards.add(newGame);
        gameManagers.add(new GameManager(true, newGame));
    }

    public GameBoard pickSpot(int game_id, int spot) {
        GameManager manager = retrieveGameManager(game_id);
        manager.pickSpot(spot, PlayerType.HUMAN);
        if (!manager.checkForEndOfGame(PlayerType.HUMAN) && !manager.checkForEndOfGame(PlayerType.COMPUTER)) {
            int computerChoice = manager.computerPickSpot();
            manager.pickSpot(computerChoice, PlayerType.COMPUTER);
        }
        return manager.getGameBoard();
    }

    private GameManager retrieveGameManager(int game_id) {
        for (GameManager manager : gameManagers) {
            if (game_id == manager.getGameBoard().getGame_id()) {
                return manager;
            }
        }
        return null;
    }

}
