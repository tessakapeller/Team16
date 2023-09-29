package com.example.teammain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import com.example.teammain.PlayerEntryController;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField playerIDField;

    @FXML
    public TextField firstNameField;

    @FXML
    private TableView<Player> redTeamTableView;

    @FXML
    private TableView<Player> blueTeamTableView;

    private final PlayerEntryController redTeamController = new PlayerEntryController();
    private final PlayerEntryController blueTeamController = new PlayerEntryController();

    public void initialize() {
        firstNameField.setVisible(false);
        redTeamController.initTable(redTeamTableView);
        blueTeamController.initTable(blueTeamTableView);
    }

    @FXML
    private ChoiceBox<String> teamChoiceBox;

    @FXML
    private Button resetButton;

    @FXML
    public void handleAddPlayer(ActionEvent event) {
        String team = teamChoiceBox.getValue();
        String playerID = playerIDField.getText();
        boolean setIDVisible = false;
        if (!playerID.isEmpty()) {
            if ("Blue".equals(team)) {
                setIDVisible = blueTeamController.addPlayer(playerID);
            } else if ("Red".equals(team)) {
                setIDVisible = redTeamController.addPlayer(playerID);
            }
            if (setIDVisible == true) {
                playerIDField.setVisible(false);
                firstNameField.setVisible(true);
            } else {
                playerIDField.clear();
            }
        }


    }

    public void handleAddFirstName(ActionEvent event)
    {
        System.out.println("test");
        String team = teamChoiceBox.getValue();
        String firstName = firstNameField.getText();
         if (!firstName.isEmpty())
        {
            if ("Blue".equals(team)) {
                blueTeamController.updateFirstName(firstName);
            } else if ("Red".equals(team)) {
                redTeamController.updateFirstName(firstName);
            }
        }
    }

    @FXML
    private void handleResetButton(ActionEvent event) {
        redTeamController.getPlayers().clear();
        blueTeamController.getPlayers().clear();
        teamChoiceBox.setValue("Blue"); // if you want to reset it to a default team
    }
}
