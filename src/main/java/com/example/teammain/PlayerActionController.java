package com.example.teammain;

import com.example.teammain.sockets.SocketClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerActionController {
    @FXML
    private TableView<Player> greenTV;

    @FXML
    private TableView<Player> redTV;

    @FXML
    private TableView<Player> currentGameEvents;

    public static PlayerEntryController parentController = null;

    public static final PlayerActionTable redScore = new PlayerActionTable();
    public static final PlayerActionTable greenScore = new PlayerActionTable();

    public static final PlayerActionTable currentEvents = new PlayerActionTable();

    public void setParentController()
    {
        greenScore.setPlayers(PlayerEntryController.blueTeamController.getPlayers());
        redScore.setPlayers(PlayerEntryController.redTeamController.getPlayers());
    }


    public void initialize(){
        setParentController();
        redScore.actionTables(redTV);
        greenScore.actionTables(greenTV);
        //currentEvents.actionTables(currentGameEvents);

    }

    /*

     */
}
