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
    private TableView<Integer> teamScore;

    @FXML
    private TableView<Events> currentGameEvents;

//    public static PlayerEntryController parentController = null;

    public static final PlayerActionTable redScore = new PlayerActionTable();
    public static final PlayerActionTable greenScore = new PlayerActionTable();

    public static final PlayerActionEventsTable currentEvents = new PlayerActionEventsTable();

    public int redTeamScore = 0;
    public int greenTeamScore = 0;


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

        System.out.println(playerShootId +" : "+ playerHitId +" : "+ shooterTeam);
        if (playerHitId == 53) {
            // score green team 100 points
            // check if player exists in green team
            System.out.println("DEBUG: Hit Before Red Base");
            System.out.println(greenScore.isPlayerExist(playerShootId));
            if (greenScore.isPlayerExist(playerShootId)) {
                Player shooter = greenScore.getPlayer(playerShootId);
                boolean isStylizedB = shooter.stylizedBProperty().getValue().equals("B");
                if(isStylizedB){
                    System.out.println("DEBUG: already b greenScore");
                }else {
                    System.out.println("DEBUG: Hit Red Base");
                    greenScore.addScore(playerShootId, 100);
                    String shooterName = shooter.codeNameProperty().getValue();
                    currentEvents.addEvent(shooterName + " has hit red score.");
                    greenScore.updateB(shooter);
                }
            }

        } else if (playerHitId == 43) {
            // score red team 100 points
            // check if player exists in red team
            System.out.println("DEBUG: Hit Before Green Base");
            System.out.println(redScore.isPlayerExist(playerShootId));
            if (redScore.isPlayerExist(playerShootId)) {
                Player shooter = redScore.getPlayer(playerShootId);

                System.out.println("DEBUG: Hit Green Base");
                boolean isStylizedB = shooter.stylizedBProperty().getValue().equals("B");
                if(isStylizedB){
                    System.out.println("DEBUG: already b redScore");
                }else {
                    redScore.addScore(playerShootId, 100);
                    String shooterName = shooter.codeNameProperty().getValue();
                    currentEvents.addEvent(shooterName + " has hit green score.");
                    redScore.updateB(shooter);
                }
            }
        }else {
            // shooter and hit player are in different teams; send hit player code
            if (shooterTeam.equals("Green")) {
                greenScore.addScore(playerShootId, 10);
                Player temp = greenScore.getPlayer(playerShootId);
                String event = temp.codeNameProperty().getValue() + "has shot a player";
                currentEvents.addEvent(event);
            } else {
                redScore.addScore(playerShootId, 10);
                Player temp = redScore.getPlayer(playerShootId);
                String event = temp.codeNameProperty().getValue() + "has shot a player";
                currentEvents.addEvent(event);
            }
        }

    }
}
