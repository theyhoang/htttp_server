package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.tictactoe.GameBoard;
import com.ticketmaster.server.tictactoe.GameUtils;
import com.ticketmaster.server.tictactoe.TicTacToeApp;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yen.hoang on 9/10/15.
 */

public class GamesServiceHandler implements ServiceHandler{

    // /games
    // /games/{game_id}
    @Override public Response GET(Request request) {
        Response response = new Response();
        response.setHttpVersion(HTTP_VERSION);

        // TODO: get spot_id info games/{game_id}/spot
        if (isValidEndpointGames(request.getUrl())) {
            response.setStatusCode(Response.STATUS_CODE_OK);
            response.setMessage((GameUtils.printAllGames(TicTacToeApp.retrieveAllGames())).getBytes());
        } else if (isValidEndpointForSingleGame(request.getUrl())) {
            GameBoard game = retrieveGame(request.getUrl());
            if (game != null) {
                response.setStatusCode(Response.STATUS_CODE_OK);
                response.setMessage((GameUtils.printGame(game)).getBytes());
            } else {
                response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);
            }
        } else {
            response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);
        }


        return response;
    }

    @Override public Response PUT(Request request) {
        return null;
    }

    // POST games/
    // POST games/{game_id}/moves with message
    // POST games/{game_id}/spots {"spot_id": [1-9]}
    @Override public Response POST(Request request) {
        // TODO: take in parameter to differentiate between human/computer player
        Response response = new Response();
        response.setHttpVersion(HTTP_VERSION);

        if (isValidEndpointGames(request.getUrl())) {
            GameBoard game = TicTacToeApp.addNewGame();
            response.setStatusCode(Response.STATUS_CODE_OK);
            response.setMessage((GameUtils.printGame(game)).getBytes());
        } else if (isValidEndpointForSpots(request.getUrl())) {

            int spotId = parseSpotId(request.getMessage());
            GameBoard game = retrieveGame(request.getUrl());
            if (spotId != -1 && game != null) {
                game = TicTacToeApp.pickSpot(game.getGame_id(), spotId);
                response.setStatusCode(Response.STATUS_CODE_OK);
                response.setMessage((GameUtils.printGame(game)).getBytes());
            } else {
                response.setStatusCode(Response.STATUS_CODE_BAD_REQUEST);
            }

        } else {
            response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);
        }
        return response;
    }

    @Override public Response DELETE(Request request) {
        return null;
    }

    @Override public Response HEAD(Request request) {
        return null;
    }

    @Override public Response OPTIONS(Request request) {
        return null;
    }

    @Override public Response PATCH(Request request) {
        return null;
    }


    private boolean isValidEndpointGames(String path) {
        if (path.equals("/games")) {
            return true;
        } else {
            return false;
        }
    }

    private GameBoard retrieveGame(String path) {
        int gameId = retrieveGameId(path);
        if (gameId != -1) {
            return TicTacToeApp.retrieveGame(retrieveGameId(path));
        }
        return null;
    }

    private boolean isValidEndpointForSingleGame(String path) {
        // get rid of initial slash /
        // games/{game_id}
        path = path.substring(1);
        List<String> paths = Arrays.asList(path.split("/"));
        if (paths.size() != 2) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidEndpointForSpots(String path) {
        // games/{game_id}/spots
        path = path.substring(1);
        List<String> paths = Arrays.asList(path.split("/"));
        if (paths.size() == 3) {
            // check if game_id is valid number
            Integer gameId = null;
            try {
                gameId  = Integer.parseInt(paths.get(1));
            } catch( NumberFormatException nfe) {
                return false;
            }
            if (paths.get(2).equals("spots") && TicTacToeApp.retrieveGame(gameId) != null) {
                return true;
            }
        }

        return false;
    }

    private int retrieveGameId(String path) {
        path = path.substring(1);// get rid of inital /
        List<String> paths = Arrays.asList(path.split("/"));
        try {
            // games/{game_id}
            return Integer.parseInt(paths.get(1));
        } catch( NumberFormatException nfe) {
            return -1;
        }
    }

    private int parseSpotId(String message) {
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(message);
        try {
            int spotId = element.getAsJsonObject().get("spot_id").getAsInt();
            return spotId;
        } catch( NumberFormatException nfe) {
            return -1;
        }

    }

}
