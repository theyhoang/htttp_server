package com.ticketmaster.server;

import com.ticketmaster.server.input.InputReader;
import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.output.OutputWriter;
import com.ticketmaster.server.request.RequestHandler;
import com.ticketmaster.server.response.ResponseHandler;
import com.ticketmaster.server.response.ServiceHandler;
import com.ticketmaster.server.response.ServiceRegistry;
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
        private int portNumber = 5000;

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
            FileUtils.publicDirPath = publicDirPath;
            // remove form file if exists
//            FileUtils.deleteFile("form");

            while (true) {
                clientSocket = serverSocket.accept();

                BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()) );
                InputReader inputReader = new InputReader(in);
                List<String> input = inputReader.readInput();
                if (input.isEmpty()) {
                    continue;
                }

                RequestHandler requestHandler = new RequestHandler();

                Request request = requestHandler.readRequest(input);

                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                ServiceRegistry serviceRegistry = new ServiceRegistry();
//                ResponseHandler responseHandler = new ResponseHandler();
                OutputWriter outputWriter = new OutputWriter(out);

                // TODO: based on type of request, have factory generate appropriate response handler
//                Response response = responseHandler.getResponse(request);
                Response response = serviceRegistry.generateResponse(request);
                outputWriter.outputResponse(response);

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
