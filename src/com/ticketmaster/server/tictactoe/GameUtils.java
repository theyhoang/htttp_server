package com.ticketmaster.server.tictactoe;

/**
 * Created by yen.hoang on 9/11/15.
 */
public class GameUtils {

    public static String printGame(GameBoard game) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"game_id\":").append(game.getGame_id()).append(",");
        boolean[][] occupiedMap = game.getOccupiedMap();
        stringBuilder.append("\"occupiedMap\":[[").append(occupiedMap[0][0]).append(",").append(occupiedMap[0][1])
            .append(",").append(occupiedMap[0][2]).append("],");
        stringBuilder.append("[").append(occupiedMap[1][0]).append(",").append(occupiedMap[1][1])
            .append(",").append(occupiedMap[1][2]).append("],");
        stringBuilder.append("[").append(occupiedMap[2][0]).append(",").append(occupiedMap[2][1])
            .append(",").append(occupiedMap[2][2]).append("]],");
        char[][] markerMap = game.getMarkerMap();
        stringBuilder.append("\"markerMap\":[\"").append(markerMap[0][0]).append(markerMap[0][1])
            .append(markerMap[0][2]).append("\",");
        stringBuilder.append("\"").append(markerMap[1][0]).append(markerMap[1][1])
            .append(markerMap[1][2]).append("\",");
        stringBuilder.append("\"").append(markerMap[2][0]).append(markerMap[2][1])
            .append(markerMap[2][2]).append("\"]}");

        return stringBuilder.toString();
    }
}
