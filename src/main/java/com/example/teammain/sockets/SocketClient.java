package com.example.teammain.sockets;

import java.net.*;
import java.io.*;

public class SocketClient {
    public static void sendEquipmentCode(String code) {
        // Create Client Socket
        try (Socket socket = new Socket("localhost", 7500);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {
             writer.println(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
