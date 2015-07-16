package com.ticketmaster.server.request;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;

import java.io.IOException;
import java.util.List;

/**
 * Created by yen.hoang on 7/7/15.
 */
public class GetRequestFactory extends RequestFactory {


    @Override
    public Request generateRequest(List<String> input) throws IOException {
        Request request = new Request();
        request.setHttpMethod(Method.GET);

        request = setPathAndHttpVersion(request, input);

        request = setHeaders(request, input);

        return request;
    }
}
