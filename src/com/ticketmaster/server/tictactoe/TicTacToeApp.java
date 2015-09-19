package com.ticketmaster.server.tictactoe;

import com.ticketmaster.server.tictactoe.player.PlayerType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yen.hoang on 9/3/15.
 */
public class TicTacToeApp {

    private static List<GameManager> gameManagers = new ArrayList<GameManager>();
    private static AtomicInteger counter = new AtomicInteger();
    private static List<GameBoard> gameBoards = new ArrayList<GameBoard>();

    public static GameBoard addNewGame() {
        GameBoard newGame = new GameBoard(counter.incrementAndGet());
        gameBoards.add(newGame);
        gameManagers.add(new GameManager(true, newGame));
        return newGame;
    }

    public static List<GameBoard> retrieveAllGames() {
        return gameBoards;
    }

    public static GameBoard pickSpot(int game_id, int spot) {
        GameManager manager = retrieveGameManager(game_id);
        manager.pickSpot(spot, PlayerType.HUMAN);
        if (!manager.checkForEndOfGame(PlayerType.HUMAN) && !manager.checkForEndOfGame(PlayerType.COMPUTER)) {
            int computerChoice = manager.computerPickSpot();
            manager.pickSpot(computerChoice, PlayerType.COMPUTER);
        }
        return manager.getGameBoard();
    }

    private static GameManager retrieveGameManager(int game_id) {
        for (GameManager manager : gameManagers) {
            if (game_id == manager.getGameBoard().getGame_id()) {
                return manager;
            }
        }
        return null;
    }

    public static GameBoard retrieveGame(int gameId) {
        for (GameBoard gameBoard : gameBoards) {
            if (gameBoard.getGame_id() == gameId) {
                return  gameBoard;
            }
        }
        return null;
    }

}
