package com.ticketmaster.server;

import com.ticketmaster.server.input.InputReader;
import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.request.RequestHandler;
import com.ticketmaster.server.response.ResponseHandler;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yen.hoang on 3/5/15.
 */
public class Server extends Thread{

    private static ServerSocket serverSocket;

        @Option(name = "-p", usage = "port number")
        private int portNumber = -1;

        @Option(name = "-d", usage = "path to public directory")
        private String publicDirPath = "";

        // receives other command line parameters than options
        @Argument
        private List<String> arguments = new ArrayList();


    public static void main(String[] args) {
        new Server().doMain(args);

    }


    public void doMain(String[] args) {
        System.out.println("STARTING SERVER!!!!!");

        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);

        } catch (CmdLineException e) {
            System.out.println(e.getMessage());

//            e.printStackTrace();
        }
        // get port number and public directory from args

        System.out.println("Port Number: " + portNumber);
        System.out.println("Public Directory: " + publicDirPath);

        // Start server here
        startServer();
    }
    public void run() {
        start();
    }

    public void startServer() {
        Socket clientSocket = null;

        try {



//            requestHandler.start();
            serverSocket = new ServerSocket(portNumber);


            while (true) {
                clientSocket = serverSocket.accept();

                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                ResponseHandler responseHandler = new ResponseHandler(out);

                BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()) );
                InputReader inputReader = new InputReader(in);
                List<String> input = inputReader.readInput();

                RequestHandler requestHandler = new RequestHandler();

                Request request = requestHandler.readRequest(input);

                FileUtils fileUtils = new FileUtils(publicDirPath);
                responseHandler.setFileUtils(fileUtils);

                // TODO: based on type of request, have factory generate appropriate response handler
                Response response = responseHandler.getResponse(request);
                responseHandler.writeResponseToClient(response);

                clientSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
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
