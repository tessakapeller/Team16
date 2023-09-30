package com.example.teammain.sockets;

import java.net.*;
import java.io.*;

class SocketClient {
    public static void main(String[] args) {
        // Create Client Socket
        try (Socket socket = new Socket("localhost", 7500);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                System.out.print("Input number (or 'exit' to quit)>");
                String input = keyboard.readLine();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                writer.println(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
