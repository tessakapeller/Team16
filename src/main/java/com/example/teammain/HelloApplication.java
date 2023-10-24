package com.example.teammain;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.util.Duration;

public class HelloApplication extends Application {

    static PlayerEntryController myControllerHandle;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader gameStartLoader = new FXMLLoader(HelloApplication.class.getResource("/gamestart.fxml"));
        Scene gameStartScene = new Scene(gameStartLoader.load());
        stage.setScene(gameStartScene);
        stage.setTitle("Game Starting...");
        stage.show();

        stage.show();


        PauseTransition pause = new PauseTransition(Duration.seconds(3));// set this to 3 when done testing
        pause.setOnFinished(event -> {
            // After 3 seconds, switch to PlayerEntry.fxml
            try
            {
                FXMLLoader playerEntryLoader = new FXMLLoader(HelloApplication.class.getResource("/PlayerEntry.fxml"));
                Scene playerEntryScene = new Scene(playerEntryLoader.load());
                myControllerHandle = (PlayerEntryController)playerEntryLoader.getController();

                playerEntryScene.setOnKeyPressed((KeyEvent ke) -> { // Create a key event that execute when any key pressed from your keyboard
                    myControllerHandle.keyEventHandler(ke);
                });
                stage.setTitle("Hello!");
                stage.setScene(playerEntryScene);

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        });

        pause.play();

        FXMLLoader gameActionLoader = new FXMLLoader(HelloApplication.class.getResource("/PlayerActionDisplay.fxml"));
        Scene gameActionScreen = new Scene(gameActionLoader.load());
        stage.setScene(gameActionScreen);
        stage.setTitle("Game Action");

    }

    public static void main(String[] args) {
        launch();
    }
}
