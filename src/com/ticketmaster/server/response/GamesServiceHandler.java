package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.tictactoe.GameBoard;
import com.ticketmaster.server.tictactoe.GameUtils;
import com.ticketmaster.server.tictactoe.TicTacToeApp;

/**
 * Created by yen.hoang on 9/10/15.
 */
public class GamesServiceHandler implements ServiceHandler{

    @Override public Response GET(Request request) {
        Response response = new Response();
        response.setHttpVersion(HTTP_VERSION);

        // figure out if we get single or multiple games

        if (retrieveAllGames(request.getUrl())) {
            response.setStatusCode(Response.STATUS_CODE_OK);
            response.setMessage((GameUtils.printAllGames(TicTacToeApp.retrieveAllGames())).getBytes());
        } else {
            // retrieve resource_id
            GameBoard game = retrieveGame(request.getUrl());
            if (game != null) {
                response.setStatusCode(Response.STATUS_CODE_OK);
                response.setMessage((GameUtils.printGame(game)).getBytes());
            } else {
                response.setStatusCode(Response.STATUS_CODE_NOT_FOUND);
            }
        }


        return response;
    }

    @Override public Response PUT(Request request) {
        return null;
    }

    @Override public Response POST(Request request) {
        Response response = new Response();
        response.setHttpVersion(HTTP_VERSION);

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


    private boolean retrieveAllGames(String path) {
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

    private int retrieveGameId(String path) {
        int lastIdx = path.lastIndexOf("/");
        try {
            return Integer.parseInt(path.substring(lastIdx + 1));
        } catch( NumberFormatException nfe) {
            return -1;
        }
    }


}
