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

        // figure out if we get single or multiple games

        if (isValidEndpointForAllGames(request.getUrl())) {
            response.setStatusCode(Response.STATUS_CODE_OK);
            response.setMessage((GameUtils.printAllGames(TicTacToeApp.retrieveAllGames())).getBytes());
        } else if (isValidEndpointForSingleGame(request.getUrl())) {
            // retrieve resource_id
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
    @Override public Response POST(Request request) {
        // TODO: take in parameter to differentiate between human/computer player
        Response response = new Response();
        response.setHttpVersion(HTTP_VERSION);


        // TODO: validate endpoint
        // add new game
        GameBoard game = TicTacToeApp.addNewGame();
        // put game into message
        response.setStatusCode(Response.STATUS_CODE_OK);
        response.setMessage((GameUtils.printGame(game)).getBytes());
        // return empty game object with new game_id?
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


    private boolean isValidEndpointForAllGames(String path) {
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
        path = path.substring(1);
        List<String> paths = Arrays.asList(path.split("/"));
        if (paths.size() != 2) {
            return false;
        } else {
            return true;
        }
    }

    private int retrieveGameId(String path) {
        int lastIdx = path.lastIndexOf("/");
        try {
            return Integer.parseInt(path.substring(lastIdx + 1));
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

    private boolean isPostNewGame(String path) {
        return path.equals("/games");
    }

    // games/{game_id}/moves
    private boolean isPostNewMove(String path) {
        return false;
    }

}
