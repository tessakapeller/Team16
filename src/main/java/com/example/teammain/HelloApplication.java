package com.example.teammain;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.util.Duration;

public class HelloApplication extends Application {

    static PlayerEntryController myControllerHandle;
    static PlayerActionController playerActionHandle;

    synchronized void Countdown() {
            Timer timer = new Timer();
        final int[] i = {10};
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if(i[0] > 0)
                    {
                        Platform.setImplicitExit(false);
                        Platform.runLater(() -> myControllerHandle.CountDown.setText("Time til game: "+ i[0]));
                        System.out.println(i[0]);
                        i[0]--;
                    }
                    else
                        timer.cancel();
                }
            }, 1000,1000);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader gameStartLoader = new FXMLLoader(HelloApplication.class.getResource("/gamestart.fxml"));
        Scene gameStartScene = new Scene(gameStartLoader.load());
        stage.setScene(gameStartScene);
        stage.setTitle("Game Starting...");
        stage.show();


        PauseTransition pause = new PauseTransition(Duration.seconds(3));// set this to 3 when done testing
        pause.setOnFinished(event -> {
            // After 3 seconds, switch to PlayerEntry.fxml
            try
            {
                FXMLLoader playerEntryLoader = new FXMLLoader(HelloApplication.class.getResource("/PlayerEntry.fxml"));
                Scene playerEntryScene = new Scene(playerEntryLoader.load());
                myControllerHandle = (PlayerEntryController)playerEntryLoader.getController();

                FXMLLoader gameActionLoader = new FXMLLoader(HelloApplication.class.getResource("/PlayerActionDisplay.fxml"));
                Scene gameActionScreen = new Scene(gameActionLoader.load());
                playerActionHandle = (PlayerActionController)gameActionLoader.getController();
//                stage.setScene(gameActionScreen);
//                stage.setTitle("Game Action");


                playerEntryScene.setOnKeyPressed((KeyEvent ke) -> { // Create a key event that execute when any key pressed from your keyboard
                    if (ke.getCode().equals(KeyCode.F5)) {
                        System.out.println("pressed F5"); //test to make sure press registers
                        //code for count down timer
                        playerActionHandle.setParentController();
                        Countdown();

                        PauseTransition pause2 = new PauseTransition(Duration.seconds(11)); //pause to wait while timer
                        pause2.setOnFinished(event2 ->{
                            stage.setScene(gameActionScreen); //prove that transition works
                        });
                        pause2.play();

                    }
                    else{
                        myControllerHandle.keyEventHandler(ke);
                    }
                });
                stage.setTitle("Hello!");
                stage.setScene(playerEntryScene);

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        });

        pause.play();


    }

    public static void main(String[] args) {
        launch();
    }
}
