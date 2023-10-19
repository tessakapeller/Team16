package com.example.teammain;

import com.example.teammain.DBUtils.DBMethods;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import com.example.teammain.MainController;

import java.io.IOException;
import javafx.util.Duration;

public class HelloApplication extends Application {

    static MainController myControllerHandle;

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
            try {
                FXMLLoader playerEntryLoader = new FXMLLoader(HelloApplication.class.getResource("/PlayerEntry.fxml"));
                Scene playerEntryScene = new Scene(playerEntryLoader.load());
                myControllerHandle = (MainController)playerEntryLoader.getController();

                playerEntryScene.setOnKeyPressed((KeyEvent ke) -> { // Create a key event that execute when any key pressed from your keyboard
                    if (ke.getCode().equals(KeyCode.F5)) {
                        System.out.println("pressed F5"); //test to make sure press registers
                        //code for count down timer
                        PauseTransition pause2 = new PauseTransition(Duration.seconds(3)); //pause to wait while timer
                        pause2.setOnFinished(event2 ->{
                            stage.setScene(gameStartScene); //prove that transition works
                        });
                        pause2.play();
                    }
                    else{
                        myControllerHandle.keyEventHandler(ke);
                    }
                });
                stage.setTitle("Hello!");
                stage.setScene(playerEntryScene);

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
