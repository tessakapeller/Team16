package com.example.teammain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PlayerEntryController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField username_xml;

    @FXML
    private GridPane blue_grid_pane;

    @FXML
    private Text text1;
    @FXML
    public void submit (ActionEvent event)
    {
        //send name to database
        try {
            String username = username_xml.getText();
//            blue_grid_pane.setAccessibleText(username);
//            Text new_text =
            text1.setText(username);
            System.out.println(username);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}