package com.ticketmaster.server.request;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;

import java.io.IOException;
import java.util.List;

/**
 * Created by yen.hoang on 7/16/15.
 */
public class PutRequestFactory extends RequestFactory{
    @Override
    public Request generateRequest(List<String> input) throws IOException {
        Request request = new Request();
        request.setHttpMethod(Method.PUT);

        request = setPathAndHttpVersion(request, input);
        request = setHeaders(request, input);

        if (hasMessage(request)) {
            //            System.out.println("HAS MESSAGE!!!!!!!!!");
            request = setMessage(request, input);
        }

        return request;
    }
}
