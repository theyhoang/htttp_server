package com.ticketmaster.server;

import com.ticketmaster.server.input.InputReader;
import com.ticketmaster.server.model.Method;
import com.ticketmaster.server.model.Request;
import com.ticketmaster.server.model.Response;
import com.ticketmaster.server.output.OutputWriter;
import com.ticketmaster.server.request.RequestHandler;
import com.ticketmaster.server.response.*;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

    private ServiceRegistry serviceRegistry;


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

        // initialize registry
        // TODO: register each of service handler, set default service handler
        serviceRegistry = initializeServiceRegistry();

        // Start server here
        startServer();
    }

    public ServiceRegistry initializeServiceRegistry() {
        return ServiceRegistry.initialize();
    }

    public void run() {
        start();
    }

    public void startServer() {
        Socket clientSocket = null;
        final int NUMBER_OF_THREADS = 50;

        try {

            ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS) ;
//            ExecutorService executor = Executors.newCachedThreadPool();

//            requestHandler.start();
            serverSocket = new ServerSocket(portNumber);
            FileUtils.publicDirPath = publicDirPath;
            // remove form file if exists
//            FileUtils.deleteFile("form");

            while (true) {
                clientSocket = serverSocket.accept();

                final Socket finalClientSocket = clientSocket;
                executor.submit(new Runnable() {
                    @Override public void run() {
                        try {
                            processRequest(finalClientSocket);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }



    }

    private void processRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()) );
        InputReader inputReader = new InputReader(in);
        List<String> input = inputReader.readInput();

        RequestHandler requestHandler = new RequestHandler();

        Request request = requestHandler.readRequest(input);
        if (input.isEmpty()) {
            return;
        }

        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

        //                ResponseHandler responseHandler = new ResponseHandler();
        OutputWriter outputWriter = new OutputWriter(out);

        // TODO: based on type of request, have factory generate appropriate response handler
        //                Response response = responseHandler.getResponse(request);
        Response response = serviceRegistry.generateResponse(request);
        outputWriter.outputResponse(response);

        clientSocket.close();
    }

}
