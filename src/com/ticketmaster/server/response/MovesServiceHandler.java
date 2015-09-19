package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.tictactoe.GameBoard;
import com.ticketmaster.server.tictactoe.GameUtils;
import com.ticketmaster.server.tictactoe.TicTacToeApp;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;

/**
 * Created by yen.hoang on 9/10/15.
 */
public class MovesServiceHandler implements  ServiceHandler {
    @Override public Response GET(Request request) {
        return null;
    }

    @Override public Response PUT(Request request) {
        return null;
    }

    @Override public Response POST(Request request) {
        Response response = new Response();
        response.setHttpVersion(HTTP_VERSION);

        // TODO: POST moves/{game_id}
        // TODO: POST retrieve data to post to spot "spot" : [0-9], "marker" : "[X/O]"
        GameBoard game = retrieveGame(request.getUrl());
        if (game != null) {
            // parse spot id from message body
            int spotId = parseSpotId(request.getMessage());
            if (spotId != -1) {
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
