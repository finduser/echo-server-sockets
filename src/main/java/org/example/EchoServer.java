package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class EchoServer {
    static final String HOSTNAME = "127.0.0.1";
    static final int PORT = 5525;

    private ServerSocket serverSocket;
    private PrintWriter out;
    private BufferedReader in;

    private Logger logger = Logger.getLogger(EchoServer.class.getName());

    void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        Socket connectionWithClient = serverSocket.accept();
        out = new PrintWriter(connectionWithClient.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(connectionWithClient.getInputStream()));

        String message;
        logger.info("Before while");
        while((message = in.readLine()) != null) {
            logger.log(Level.INFO, "Received message: {0}", message);
            if("q!".equals(message)) {
                logger.log(Level.INFO, "Sending: Good bye!");
                out.println("Good bye!");
                break;
            }
            logger.log(Level.INFO, "Sending: {0}", message);

            out.println(message);
        }
    }

    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer();
        server.start();
    }
}
