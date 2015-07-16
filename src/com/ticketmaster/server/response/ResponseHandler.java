package com.ticketmaster.server.response;

import com.ticketmaster.server.FileUtils;
import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.output.OutputWriter;

import java.io.DataOutputStream;

/**
 * Created by yen.hoang on 3/25/15.
 */

// TODO: to only handle response, separate other responsibilities to downstream classes
public class ResponseHandler {

    public Response getResponse(Request request) {
        Method methodType = request.getHttpMethod();

        // TODO: check if path is valid aka build response for invalid url
        ResponseFactory responseFactory = null;
        switch (methodType) {
            case GET:
                responseFactory = new GetResponseFactory();
                break;
            case POST:
                responseFactory = new PostResponseFactory();
                break;
            case NOT_SUPPORTED:
                responseFactory = new NotSupportedResponseFactory();
                // TODO: throw exception
                break;

        }
        Response response = responseFactory.generateResponse(request);
        return response;

    }

}
