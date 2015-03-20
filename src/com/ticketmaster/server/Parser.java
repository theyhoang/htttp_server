package com.ticketmaster.server;

import com.ticketmaster.server.model.Request;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by yen.hoang on 3/20/15.
 */
public interface Parser {

    public Object parse(BufferedReader input) throws IOException;
}
