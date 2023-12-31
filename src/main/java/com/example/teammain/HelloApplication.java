package com.example.teammain;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.example.teammain.sockets.UDPSocket;

public class HelloApplication extends Application {

    static PlayerEntryController myControllerHandle;
    static PlayerActionController playerActionHandle;

    public static final int GAMETIME = 360000; // 6 minutes
    public static boolean RUNNING = false;

    synchronized void StartCountdown() {
        Timer timer = new Timer();
        final int[] i = { 30 };
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (i[0] > 0) {
                    Platform.setImplicitExit(false);
                    Platform.runLater(() -> myControllerHandle.CountDown.setText("Time til game: " + i[0]));
                    System.out.println(i[0]);
                    i[0]--;
                } else
                    timer.cancel();
            }
        }, 1000, 1000);

        RUNNING = true;
    }

    synchronized void GameCountdown() {
        Timer timer = new Timer();
        final int[] i = { 360 };
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (i[0] > 0) {
                    Platform.setImplicitExit(false);
                    Platform.runLater(() -> playerActionHandle.PlayActionTime.setText("" + i[0] + ""));
                    System.out.println(i[0]);
                    i[0]--;
                } else
                    timer.cancel();
            }
        }, 1000, 1000);

        RUNNING = true;
    }

    public static void PlayMusic(String location, int thing) {

        try {
            File musicPath = new File("src/main/resources/" + location);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                if (thing == 1) {
                    clip.start();
                } else {
                    clip.stop();
                }
            } else {
                System.out.println("can't find file");
                System.out.println(musicPath);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader gameStartLoader = new FXMLLoader(HelloApplication.class.getResource("/gamestart.fxml"));
        Scene gameStartScene = new Scene(gameStartLoader.load());
        stage.setScene(gameStartScene);
        stage.setTitle("Game Starting...");
        stage.show();


        // CREATE ARRAY OF TRACKS
        ArrayList<String> ar = new ArrayList<String>();
        int arlength = 0;

        for (int i = 1; i < 9; i++) {
            ar.add("Track0" + i + ".wav");
            arlength++;
        }

        // PICK RANDOM TRACK FROM ARRAY
        Random random = new Random();
        int index = random.nextInt(arlength);
        String filePath = ar.get(index);
        System.out.println("FILE PATH: " + filePath);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));// set this to 3 when done testing
        pause.setOnFinished(event -> {
            // After 3 seconds, switch to PlayerEntry.fxml
            try {
                FXMLLoader playerEntryLoader = new FXMLLoader(HelloApplication.class.getResource("/PlayerEntry.fxml"));
                Scene playerEntryScene = new Scene(playerEntryLoader.load());
                myControllerHandle = (PlayerEntryController) playerEntryLoader.getController();

                FXMLLoader gameActionLoader = new FXMLLoader(
                        HelloApplication.class.getResource("/PlayerActionDisplay.fxml"));
                Scene gameActionScreen = new Scene(gameActionLoader.load());
                playerActionHandle = (PlayerActionController) gameActionLoader.getController();

                playerEntryScene.setOnKeyPressed((KeyEvent ke) -> { // Create a key event that execute when any key
                                                                    // pressed from your keyboard
                    if (ke.getCode().equals(KeyCode.F5)) {
                        System.out.println("pressed F5"); // test to make sure press registers
                        // code for count down timer
                        playerActionHandle.setParentController();
                        StartCountdown();
                        PauseTransition pause2 = new PauseTransition(Duration.seconds(30)); // pause to wait while timer
                        PauseTransition pause3 = new PauseTransition(Duration.seconds(360));
                        pause2.setOnFinished(event2 -> {
                            stage.setScene(gameActionScreen);
                            PlayMusic(filePath, 1);
                            GameCountdown();

                            // prove that transition works
                        });
                        pause2.play();
                        pause3.setOnFinished(event3 -> {
                            PlayMusic(filePath, 0);
                        });
                        pause3.play();

                        PlayerEntryController.greenTeamController.getPlayers().forEach(player -> {
                            System.out.println(player.codeNameProperty().get() +" "+ player.equipmentCodeProperty());
                        });
                        PlayerEntryController.redTeamController.getPlayers().forEach(player -> {
                            System.out.println(player.codeNameProperty().get() +" "+ player.equipmentCodeProperty());
                        });


                        // PlayMusic(filePath,0);
                        UDPSocket udpSocket = new UDPSocket();
                        Thread udpThread = new Thread(udpSocket);
                        udpThread.start();
//                        try {
//                            udpThread.join();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

                    } else {
                        myControllerHandle.keyEventHandler(ke);
                    }
                });
                stage.setTitle("Hello!");
                stage.setScene(playerEntryScene);
                gameActionScreen.setOnKeyPressed((KeyEvent key) -> {
                    if (key.getCode().equals(KeyCode.F5)) {
                        stage.setScene(playerEntryScene);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        pause.play();


    }

    public static void main(String[] args) {
        launch();
    }

}
