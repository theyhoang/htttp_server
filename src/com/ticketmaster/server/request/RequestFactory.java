package com.ticketmaster.server.request;

import com.ticketmaster.server.model.Request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yen.hoang on 7/7/15.
 */
public abstract class RequestFactory {

    abstract Request generateRequest(List<String> input) throws IOException;

    protected Request setHeaders(Request request, List<String> input) {
        List<String> headers = new ArrayList<String>();
        for (int i = 0; i < input.size() - 1; i++) {
            // look for blank line to end headers
            if (input.get(i).isEmpty()) {
                break;
            }
            headers.add(input.get(i));
        }

        request.setHeaders(headers);
        return request;
    }

    protected Request setPathAndHttpVersion(Request request, List<String> input) {
        String initialRequestLine = input.get(0);
        List<String> initialRequest = Arrays.asList(initialRequestLine.split("\\s+"));

        // then get path
        String path = initialRequest.get(1);

        // if we have parameters
        if (path.contains("?")) {
            try {
                request = parseForParameters(request, path);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            request.setUrl(path);
        }


        // finally http version
        String httpVersion = initialRequest.get(2);
        request.setHttpVersion(httpVersion);
        return request;
    }

    protected boolean hasMessage(Request request) {
        for (String header : request.getHeaders()) {
            if (header.contains("Content-Length")) {
                return true;
            }
        }
        return false;
    }

    protected Request setMessage(Request request, List<String> input) {

        // message should be last line/header in request input
        String message = input.get(input.size() - 1);
        request.setMessage(message);

        return request;
    }

    protected Request parseForParameters(Request request, String path)
        throws UnsupportedEncodingException {
        int parameterStartIdx = path.indexOf("?");
        request.setUrl(path.substring(0, parameterStartIdx));
        String parametersString = path.substring(parameterStartIdx + 1);

        String[] parameters = parametersString.split("&");
        List<String> parametersList = Arrays.asList(parameters);

        List<String> parametersListDecoded = new ArrayList<String>();
        for (String parameter : parametersList) {
            // added spaces around '='
            String decodedParameter = java.net.URLDecoder.decode(parameter, "UTF-8");
            int equalCharIdx = decodedParameter.indexOf('=');
            String decodedParameterKey = decodedParameter.substring(0, equalCharIdx);
            String decodedParameterValue = decodedParameter.substring(equalCharIdx + 1);
            decodedParameter = decodedParameterKey + " = " + decodedParameterValue;
            parametersListDecoded.add(decodedParameter);
        }

        request.setParameters(parametersListDecoded);


        return request;
    }
}
