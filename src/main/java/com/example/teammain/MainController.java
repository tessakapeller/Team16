package com.example.teammain;

import com.example.teammain.sockets.SocketClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import com.example.teammain.PlayerEntryController;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {

    @FXML
    private TextField playerIDField;

    @FXML
    public TextField firstNameField;

    @FXML
    public TextField lastNameField;

    @FXML
    public TextField codeNameField;

    @FXML
    public TextField equipCodeField;

    @FXML
    private TableView<Player> redTeamTableView;

    @FXML
    private TableView<Player> blueTeamTableView;

    private final PlayerEntryController redTeamController = new PlayerEntryController();
    private final PlayerEntryController blueTeamController = new PlayerEntryController();

    public void initialize() {
        firstNameField.setVisible(false);
        lastNameField.setVisible(false);
        codeNameField.setVisible(false);
        equipCodeField.setVisible(false);
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
            if (setIDVisible) {
                playerIDField.setVisible(false);
                firstNameField.setVisible(true);
            } else {
                equipCodeField.setVisible(true);
            }
        }
        playerIDField.clear();
    }

    public void handleAddFirstName(ActionEvent event) {
        String team = teamChoiceBox.getValue();
        String firstName = firstNameField.getText();
        if (!firstName.isEmpty()) {
            if ("Blue".equals(team)) {
                blueTeamController.updateFirstName(firstName);
            } else if ("Red".equals(team)) {
                redTeamController.updateFirstName(firstName);
            }
            firstNameField.setVisible(false);
            lastNameField.setVisible(true);
        }
        firstNameField.clear();
    }

    public void handleAddLastName(ActionEvent event)
    {
        String team = teamChoiceBox.getValue();
        String lastname = lastNameField.getText();
        if (!lastname.isEmpty()) {
            if ("Blue".equals(team)) {
                blueTeamController.updateLastName(lastname);
            } else if ("Red".equals(team)) {
                redTeamController.updateLastName(lastname);
            }
            lastNameField.setVisible(false);
            codeNameField.setVisible(true);
        }
        lastNameField.clear();
    }
    public void handleAddCodeName(ActionEvent event)
    {
        String team = teamChoiceBox.getValue();
        String codename = codeNameField.getText();
        if (!codename.isEmpty()) {
            if ("Blue".equals(team)) {
                blueTeamController.updateCodeName(codename);
            } else if ("Red".equals(team)) {
                redTeamController.updateCodeName(codename);
            }
            codeNameField.setVisible(false);
            equipCodeField.setVisible(true);
        }
        codeNameField.clear();
    }
    public void handleAddEquipCode(ActionEvent event)
    {
        String team = teamChoiceBox.getValue();
        String equipmentCode = equipCodeField.getText();
        if (!equipmentCode.isEmpty()) {
            if ("Blue".equals(team)) {
                blueTeamController.updateEquipmentCode(equipmentCode);
            } else if ("Red".equals(team)) {
                redTeamController.updateEquipmentCode(equipmentCode);
            }
            equipCodeField.setVisible(false);
            playerIDField.setVisible(true);
            //will send equipment code to signal 7500 after output, signal isn't set up.
            SocketClient.sendEquipmentCode(equipmentCode);
        }
        equipCodeField.clear();
    }

//    Handler for any type of key inputs
    public void keyEventHandler(KeyEvent ke)
    {
        if (ke.getCode().equals(KeyCode.F12)) {
            //System.out.println("pressed F12");
            this.resetEntry();
        }
    }

    public void resetEntry(){
        this.handleResetButton(new ActionEvent());
    }
    @FXML
    private void handleResetButton(ActionEvent event) {
        redTeamController.getPlayers().clear();
        blueTeamController.getPlayers().clear();
        teamChoiceBox.setValue("Blue"); // if you want to reset it to a default team
        playerIDField.setVisible(true);
        firstNameField.setVisible(false);
        lastNameField.setVisible(false);
        codeNameField.setVisible(false);
        playerIDField.clear();
        firstNameField.clear();
        lastNameField.clear();
        codeNameField.clear();

    }
}