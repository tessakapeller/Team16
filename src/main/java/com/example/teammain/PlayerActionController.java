package com.example.teammain;

//import com.example.teammain.sockets.SocketClient;
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

import com.example.teammain.sockets.UDPSocket;

public class PlayerActionController {
    @FXML
    private TableView<Player> greenTV;

    @FXML
    private TableView<Player> redTV;

    @FXML
    private TableView<String> currentGameEvents;

//    public static PlayerEntryController parentController = null;

    public static final PlayerActionTable redScore = new PlayerActionTable();
    public static final PlayerActionTable greenScore = new PlayerActionTable();

    public static final PlayerActionEventsTable currentEvents = new PlayerActionEventsTable();

    public void setParentController()
    {
        greenScore.setPlayers(PlayerEntryController.greenTeamController.getPlayers());
        redScore.setPlayers(PlayerEntryController.redTeamController.getPlayers());
    }


    public void initialize(){
        setParentController();
        redScore.actionTables(redTV);
        greenScore.actionTables(greenTV);
        currentEvents.actionTables(currentGameEvents);

    }

    public static void updateScore(int playerShootId, int playerHitId, String shooterTeam) {
        if (playerHitId == 53) {
            // score green team 100 points
            // check if player exists in green team
            if (greenScore.isPlayerExist(playerShootId)) {
                greenScore.addScore(playerShootId, 100);
                Player shooter = greenScore.getPlayer(playerShootId);
                String shooterName = shooter.codeNameProperty().getValue();
                currentEvents.addEvent(shooterName + " has hit red score.");
                // TODO: add style B code here

            }

        } else if (playerHitId == 43) {
            // score red team 100 points
            // check if player exists in red team
            if (redScore.isPlayerExist(playerShootId)) {
                redScore.addScore(playerShootId, 100);
                Player shooter = redScore.getPlayer(playerShootId);
                String shooterName = shooter.codeNameProperty().getValue();
                currentEvents.addEvent(shooterName + " has hit green score.");
                // TODO: add style B code here

            }
        }

        // shooter and hit player are in different teams; send hit player code
        if (shooterTeam.equals("Green")) {
            greenScore.addScore(playerShootId, 10);
        } else {
            redScore.addScore(playerShootId, 10);
        }

    }

    /*

     */
}
