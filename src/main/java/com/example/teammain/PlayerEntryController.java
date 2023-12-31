package com.example.teammain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class PlayerEntryController {

    @FXML
    private TextField playerIDField;

    @FXML
    public TextField firstNameField;

    @FXML
    public Text CountDown;

    @FXML
    public TextField lastNameField;


    @FXML
    public TextField codeNameField;

    @FXML
    public TextField equipCodeField;

    @FXML
    private TableView<Player> redTeamTableView;

    @FXML
    private TableView<Player> greenTeamTableView;

    public static PlayerEntryTable redTeamController = new PlayerEntryTable();
    public static PlayerEntryTable greenTeamController = new PlayerEntryTable();



    public void initialize() {
        firstNameField.setVisible(false);
        lastNameField.setVisible(false);
        codeNameField.setVisible(false);
        equipCodeField.setVisible(false);
        redTeamController.initTable(redTeamTableView);
        greenTeamController.initTable(greenTeamTableView);
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
            if ("Green".equals(team)) {
                setIDVisible = greenTeamController.addPlayer(playerID);
            } else if ("Red".equals(team)) {
                setIDVisible = redTeamController.addPlayer(playerID);
            }
            if (setIDVisible) {
                playerIDField.setVisible(false);
                firstNameField.setVisible(true);
            } else {
                playerIDField.setVisible(false);
                equipCodeField.setVisible(true);
            }
        }
        playerIDField.clear();
    }

    public void handleAddFirstName(ActionEvent event) {
        String team = teamChoiceBox.getValue();
        String firstName = firstNameField.getText();
        if (!firstName.isEmpty()) {
            if ("Green".equals(team)) {
                greenTeamController.updateFirstName(firstName);
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
            if ("Green".equals(team)) {
                greenTeamController.updateLastName(lastname);
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
            if ("Green".equals(team)) {
                greenTeamController.updateCodeName(codename);
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
            if ("Green".equals(team)) {
                greenTeamController.updateEquipmentCode(equipmentCode);
            } else if ("Red".equals(team)) {
                redTeamController.updateEquipmentCode(equipmentCode);
            }
            equipCodeField.setVisible(false);
            playerIDField.setVisible(true);
            //will send equipment code to signal 7500 after output, signal isn't set up.
//            SocketClient.sendEquipmentCode(equipmentCode);
        }
        equipCodeField.clear();
    }

//    Handler for any type of key inputs
    public void keyEventHandler(KeyEvent ke)
    {
        if (ke.getCode().equals(KeyCode.F12))
        {
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
        greenTeamController.getPlayers().clear();
        teamChoiceBox.setValue("Green"); // if you want to reset it to a default team
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
