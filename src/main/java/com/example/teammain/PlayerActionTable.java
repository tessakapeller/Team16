package com.example.teammain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;

public class PlayerActionTable {


    public ObservableList<Player> players = null;

    @FXML
    public void actionTables(TableView<Player> tableView) {

        TableColumn<Player, String> codeNameColumn = new TableColumn<>("CodeName");
        codeNameColumn.setCellValueFactory(cellData -> cellData.getValue().codeNameProperty()); // Updated line

        TableColumn<Player, String> playerScoreColumn = new TableColumn<>("PlayerScore");
        playerScoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());  //cellData.getValue().codeNameProperty()); // Updated line

        codeNameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.7));
        playerScoreColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));

        tableView.getColumns().setAll(codeNameColumn, playerScoreColumn);

        tableView.setItems(players);

    }

    public void setPlayers(ObservableList<Player> p)
    {
        this.players = p;
    }
}
