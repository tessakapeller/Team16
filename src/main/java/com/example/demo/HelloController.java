package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private Label welcomeText;
    @FXML
    private ImageView splash;

    public void initialize()
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3),this::hideImage));

        timeline.setCycleCount(1);
        timeline.play();
    };

    private void hideImage(ActionEvent event)
    {
        splash.setVisible(false);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}