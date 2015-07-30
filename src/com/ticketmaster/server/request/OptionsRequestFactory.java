package com.ticketmaster.server.request;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;

import java.io.IOException;
import java.util.List;

/**
 * Created by yen.hoang on 7/23/15.
 */
public class OptionsRequestFactory extends RequestFactory{
    @Override
    Request generateRequest(List<String> input) throws IOException {
        Request request = new Request();
        request.setHttpMethod(Method.OPTIONS);

        request = setPathAndHttpVersion(request, input);

        request = setHeaders(request, input);

        return request;
    }
}
