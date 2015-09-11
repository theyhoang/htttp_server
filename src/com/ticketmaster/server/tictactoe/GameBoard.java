package com.ticketmaster.server.tictactoe;

import com.ticketmaster.server.tictactoe.player.PlayerType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yen.hoang on 10/13/14.
 */
public class GameBoard {

    private final char X_MARKER = 'X';
    private final char O_MARKER = 'O';
    private final char EMPTY_MARKER = ' ';

    private final int MAP_SIZE = 3;

    // This should be immutable
    private final boolean[][] occupiedMap = new boolean[MAP_SIZE][MAP_SIZE];

    // This marks X's and O's
    private char[][] markerMap = new char[MAP_SIZE][MAP_SIZE];

    private int game_id = 0;

    public GameBoard() {
        for(int i = 0; i < MAP_SIZE; i++) {
            for(int j = 0; j < MAP_SIZE; j++) {
                markerMap[i][j] = EMPTY_MARKER;
            }
        }
    }

    public GameBoard(Integer GAME_ID) {
        this.game_id = GAME_ID;
        for(int i = 0; i < MAP_SIZE; i++) {
            for(int j = 0; j < MAP_SIZE; j++) {
                markerMap[i][j] = EMPTY_MARKER;
            }
        }
    }

    public GameBoard(GameBoard board) {
        this.game_id = board.game_id;
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                this.markerMap[i][j] = board.markerMap[i][j];
                this.occupiedMap[i][j] = board.occupiedMap[i][j];
            }
        }
    }


    // This function checks for a winner
    public boolean checkForWinner(PlayerType playerType) {
        char playerMarker = ' ';

        if (playerType.equals(PlayerType.COMPUTER)) {
            playerMarker = O_MARKER;
        } else if (playerType.equals(PlayerType.HUMAN)) {
            playerMarker = X_MARKER;
        }

        if (checkHorizontalRowsForWinner(playerMarker)) {
            return true;
        }

        if (checkVerticalRowsForWinner(playerMarker)) {
            return true;
        }

        if (checkDiagonalRowsForWinner(playerMarker)) {
            return true;
        }


        return false;
    }

    private boolean checkVerticalRowsForWinner(char playerMarker) {
        boolean result = false;
        if ((markerMap[0][0] == playerMarker) && (markerMap[0][1] == playerMarker) && (markerMap[0][2] == playerMarker)) {
            result = true;
        } else if ((markerMap[1][0] == playerMarker) && (markerMap[1][1] == playerMarker) && (markerMap[1][2] == playerMarker)) {
            result = true;
        } else if ((markerMap[2][0] == playerMarker) && (markerMap[2][1] == playerMarker) && (markerMap[2][2] == playerMarker)) {
            result = true;
        }

        return result;
    }

    private boolean checkHorizontalRowsForWinner(char playerMarker) {
        boolean result = false;
        if ((markerMap[0][0] == playerMarker) && (markerMap[1][0] == playerMarker) && (markerMap[2][0] == playerMarker)) {
            result = true;
        } else if ((markerMap[0][1] == playerMarker) && (markerMap[1][1] == playerMarker) && (markerMap[2][1] == playerMarker)) {
            result = true;
        } else if ((markerMap[0][2] == playerMarker) && (markerMap[1][2] == playerMarker) && (markerMap[2][2] == playerMarker)) {
            result = true;
        }

        return result;
    }

    private boolean checkDiagonalRowsForWinner(char playerMarker) {
        boolean result = false;
        if ((markerMap[0][0] == playerMarker) && (markerMap[1][1] == playerMarker) && (markerMap[2][2] == playerMarker)) {
            result = true;
        } else if ((markerMap[0][2] == playerMarker) && (markerMap[1][1] == playerMarker) && (markerMap[2][0] == playerMarker)) {
            result = true;
        }

        return result;
    }


    public boolean checkForDraw() {
        int emptySpotsLeft = 0;
        for(int i = 0; i < MAP_SIZE; i++) {
            for(int j = 0; j < MAP_SIZE; j++) {
                if (markerMap[i][j] == EMPTY_MARKER) {
                    emptySpotsLeft++;
                }
            }
        }

        if (emptySpotsLeft == 0) {
            return true;
        } else {
            return false;
        }
    }



    // This function prints out the board
    public void printBoard() {
        System.out.println(markerMap[0][0] + "|" + markerMap[1][0] + "|" + markerMap[2][0]);
        System.out.println("-----");
        System.out.println(markerMap[0][1] + "|" + markerMap[1][1] + "|" + markerMap[2][1]);
        System.out.println("-----");
        System.out.println(markerMap[0][2] + "|" + markerMap[1][2] + "|" + markerMap[2][2]);
    }

    public boolean pickSpot(int spot, PlayerType playerType) {

        char marker = ' ';
        if (playerType.equals(PlayerType.HUMAN)) {
            marker = X_MARKER;
        } else if (playerType.equals(PlayerType.COMPUTER)) {
            marker = O_MARKER;
        }

        boolean validChoice = true;

        switch(spot) {
            case 1: if (!occupiedMap[0][0]) {
                occupiedMap[0][0] = true;
                markerMap[0][0] = marker;
            } else {
                validChoice = false;
            }
                break;
            case 2: if (!occupiedMap[1][0]) {
                occupiedMap[1][0] = true;
                markerMap[1][0] = marker;
            } else {
                validChoice = false;
            }
                break;
            case 3: if (!occupiedMap[2][0]) {
                occupiedMap[2][0] = true;
                markerMap[2][0] = marker;
            } else {
                validChoice = false;
            }
                break;
            case 4: if (!occupiedMap[0][1]) {
                occupiedMap[0][1] = true;
                markerMap[0][1] = marker;
            } else {
                validChoice = false;
            }
                break;
            case 5: if (!occupiedMap[1][1]) {
                occupiedMap[1][1] = true;
                markerMap[1][1] = marker;
            } else {
                validChoice = false;
            }
                break;
            case 6: if (!occupiedMap[2][1]) {
                occupiedMap[2][1] = true;
                markerMap[2][1] = marker;
            } else {
                validChoice = false;
            }
                break;
            case 7: if (!occupiedMap[0][2]) {
                occupiedMap[0][2] = true;
                markerMap[0][2] = marker;
            } else {
                validChoice = false;
            }
                break;
            case 8: if (!occupiedMap[1][2]) {
                occupiedMap[1][2] = true;
                markerMap[1][2] = marker;
            } else {
                validChoice = false;
            }
                break;
            case 9: if (!occupiedMap[2][2]) {
                occupiedMap[2][2] = true;
                markerMap[2][2] = marker;
            } else {
                validChoice = false;
            }
                break;
            default: System.out.println("Invalid choice! Must be 1-9");
                validChoice = false;
        }

        if (!validChoice) {
            System.out.println("Invalid choice! : " + spot);
            return false;
        }

        return true;
    }


    public List<Move> getAvailableMoves() {
        List<Move> availableMoves = new ArrayList<Move>();

        if (!occupiedMap[0][0]) {
            availableMoves.add(new Move(1, 0));
        }

        if (!occupiedMap[1][0]) {
            availableMoves.add(new Move(2, 0));
        }

        if (!occupiedMap[2][0]) {
            availableMoves.add(new Move(3, 0));
        }

        if (!occupiedMap[0][1]) {
            availableMoves.add(new Move(4, 0));
        }

        if (!occupiedMap[1][1]) {
            availableMoves.add(new Move(5, 0));
        }

        if (!occupiedMap[2][1]) {
            availableMoves.add(new Move(6, 0));
        }

        if (!occupiedMap[0][2]) {
            availableMoves.add(new Move(7, 0));
        }

        if (!occupiedMap[1][2]) {
            availableMoves.add(new Move(8, 0));
        }

        if (!occupiedMap[2][2]) {
            availableMoves.add(new Move(9, 0));
        }

        return availableMoves;
    }

    public boolean[][] getOccupiedMap() {
        return occupiedMap;
    }

    public char[][] getMarkerMap() {
        return markerMap;
    }

    public int getGame_id() {
        return game_id;
    }
}
