package com.ticketmaster.server.response;

import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.tictactoe.TicTacToeApp;

/**
 * Created by yen.hoang on 9/10/15.
 */
public class GameServiceHandler implements ServiceHandler{
    private TicTacToeApp ticTacToeApp;

    GameServiceHandler(TicTacToeApp ticTacToeApp) {
       this.ticTacToeApp = ticTacToeApp;
    }

    @Override public Response GET(Request request) {
        return null;
    }

    @Override public Response PUT(Request request) {
        return null;
    }

    @Override public Response POST(Request request) {
        return null;
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
}
