package com.example.teammain.sockets;

import java.net.*;
import java.io.*;

class SocketServer {
    public static void main(String[] args) {
        int sum = 0;

        try (ServerSocket serverSocket = new ServerSocket(7501)) {
            System.out.println("Server started. Waiting for client connection...");

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.println("Client connected.");

                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    try {
                        int number = Integer.parseInt(inputLine);
                        sum += number;
                        System.out.println("Current Sum: " + sum);
                    } catch (NumberFormatException e) {
                        System.out.println("Received non-numeric input: " + inputLine);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
