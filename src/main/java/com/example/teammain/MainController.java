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
    private TextField playerNameField;

    @FXML
    private TableView<Player> redTeamTableView;

    @FXML
    private TableView<Player> blueTeamTableView;

    private final PlayerEntryController redTeamController = new PlayerEntryController();
    private final PlayerEntryController blueTeamController = new PlayerEntryController();

    public void initialize() {
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
        String playerName = playerNameField.getText().trim();

        if (!playerName.isEmpty()) {
            if ("Blue".equals(team)) {
                blueTeamController.addPlayer(playerName);
            } else if ("Red".equals(team)) {
                redTeamController.addPlayer(playerName);
            }
            playerNameField.clear();
        }
    }

    @FXML
    private void handleResetButton(ActionEvent event) {
        redTeamController.getPlayers().clear();
        blueTeamController.getPlayers().clear();
        teamChoiceBox.setValue("Blue"); // if you want to reset it to a default team
    }
}
