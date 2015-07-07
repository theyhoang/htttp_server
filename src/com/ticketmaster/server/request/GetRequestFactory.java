package com.ticketmaster.server.request;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.request.RequestFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yen.hoang on 7/7/15.
 */
public class GetRequestFactory implements RequestFactory {


    @Override
    public Request generateRequest(List<String> input) throws IOException {
        Request request = new Request();
        request.setHttpMethod(Method.GET);

        // TODO: put inside a method for readability
        String initialRequestLine = input.get(0);
        List<String> initialRequest = Arrays.asList(initialRequestLine.split("\\s+"));

        // then get path
        String path = initialRequest.get(1);


        // TODO: validation
        request.setUrl(path);

        // finally http version
        String httpVersion = initialRequest.get(2);
        request.setHttpVersion(httpVersion);

        // TODO: read headers
        List<String> headers = new ArrayList<String>();
        for (int i = 0; i < input.size(); i++) {
            // look for blank line to end headers
            if (!input.get(i).isEmpty()) {
                headers.add(input.get(i));
            }
        }

        request.setHeaders(headers);

        return request;
    }
}
