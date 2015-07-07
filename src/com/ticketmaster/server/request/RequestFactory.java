package com.ticketmaster.server.request;

import com.ticketmaster.server.model.Request;

import java.io.IOException;
import java.util.List;

/**
 * Created by yen.hoang on 7/7/15.
 */
public interface RequestFactory {

    Request generateRequest(List<String> input) throws IOException;
}
