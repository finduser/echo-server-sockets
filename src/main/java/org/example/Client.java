package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    void connect(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    String sendMessage(String message) throws IOException {
        out.println(message);
        return in.readLine();
    }
    void close() throws IOException {
        socket.close();
        out.close();
        in.close();
    }
}
