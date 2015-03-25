package com.ticketmaster.server;

import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yen.hoang on 3/5/15.
 */
public class Server extends Thread{

    private int portNumber;
    private ServerSocket serverSocket;

    public Server(int portNumber) {
        this.portNumber = portNumber;
    }


    // Start server here
    public void run() {
        start();
    }

    public void start() {
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(portNumber);
            clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()) );
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            ResponseHandler responseHandler = new ResponseHandler(out);
            RequestHandler requestHandler = new RequestHandler(in);
            // TODO: server will listen for request, then out a response

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    // Method to read request from client
    public Request readRequest() {
        return null;
    }


    // Method to construct response
    public Response constructResponse(Method method) {
        return null;
    }

}
