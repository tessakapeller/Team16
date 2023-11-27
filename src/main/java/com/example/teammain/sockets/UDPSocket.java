package com.example.teammain.sockets;

import com.example.teammain.HelloApplication;
import com.example.teammain.PlayerActionController;

import java.net.*;
import java.util.Arrays;
import java.io.*;
import javafx.application.Platform;

public class UDPSocket implements AutoCloseable, Runnable {

    private DatagramSocket socket;
    private InetSocketAddress sendAddress;

    private int SENDPORT = 7500;
    private int RECEIVEPORT = 7501;
    private byte[] buf;

    boolean initialized = false;

    public UDPSocket() {
        String SERVERADDRESS = "127.0.0.1";
        try {
            socket = new DatagramSocket(RECEIVEPORT);
            socket.setSoTimeout(5000); // 5 second timeout

            sendAddress = new InetSocketAddress(SERVERADDRESS, SENDPORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UDPSocket(String serverAddress) {
        try {
            socket = new DatagramSocket(RECEIVEPORT);
            socket.setSoTimeout(5000); // 5 second timeout

            sendAddress = new InetSocketAddress(serverAddress, SENDPORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            Thread.sleep(31000);
//
            System.out.println(HelloApplication.GAMETIME);

//            System.out.println("Tried to access");
            // main game loop; will run for 6 minutes
            handleGameStart();

            long start = System.currentTimeMillis();
            long elapsedTime = 0L;

            while (elapsedTime <= HelloApplication.GAMETIME) {
                String receiveData = null;
                try {
                    receiveData = receive();
                } catch (SocketTimeoutException e) {
                    System.out.println("DEBUG: Timeout");
                    handleGameStart();
                    start = System.currentTimeMillis();
                    continue;
                }
                System.out.println("DEBUG: Received: " + receiveData);

                int[] ids = processReceivedData(receiveData);
                int playerShootId = ids[0];
                int playerHitId = ids[1];

                String shooterTeam = PlayerActionController.greenScore.isPlayerExist(playerShootId) ? "Green"
                        : "Red";

                String hitTeam;
                if(playerHitId == 53 || playerHitId == 43){
                     hitTeam = "Base";
                }else {
                     hitTeam = PlayerActionController.greenScore.isPlayerExist(playerHitId) ? "Green" : "Red";
                }

                if (shooterTeam.equals(hitTeam)) {
                    // shooter and hit player are in the same team; send myself code
                    send(playerShootId);
                } else {
                    System.out.println("DEBUG: hit player");
                    Platform.runLater(
                            () -> PlayerActionController.updateScore(playerShootId, playerHitId, shooterTeam));
                    send(playerHitId);
                }

                elapsedTime = System.currentTimeMillis() - start;

                System.out.println("DEBUG: Elapsed time: " + elapsedTime);
            }
            System.out.println("DEBUG: closing game");
            handleGameEnd();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void send(int ownEquipmentId) throws IOException {
        buf = String.valueOf(ownEquipmentId).getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, sendAddress);
        System.out.println("Sending: " + String.valueOf(ownEquipmentId) + " to " + sendAddress);
        socket.send(packet);
    }

    public String receive() throws IOException, SocketTimeoutException {
        buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    public void handleGameStart() throws IOException {
        send(202); // Transmit code 202 at game start
    }

    public void handleGameEnd() throws IOException {
        for (int i = 0; i < 3; i++) {
            send(221); // Transmit code 221 three times at game end
        }
    }

    public int[] processReceivedData(String data) {
        String[] parts = data.split(":");
        return Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
    }

    @Override
    public void close() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }

    // public static void main(String[] args) {
    // try (UDPSocket client = new UDPSocket()) {
    // client.handleGameStart();
    // for (int i = 0; i < 10; i++) {
    // String receiveData = null;
    // try{
    // receiveData = client.receive();
    // } catch (SocketTimeoutException e){
    // System.out.println("DEBUG: Timeout");
    // client.handleGameStart();
    // i--;
    // continue;
    // }
    // System.out.println("DEBUG: Received: " + receiveData);
    // int playerHitId = client.processReceivedData(receiveData);
    // System.out.println("DEBUG: Player hit ID: " + playerHitId);
    // client.send(playerHitId);

    // }
    // client.handleGameEnd();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

}
