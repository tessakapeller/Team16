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

        TableColumn<Player, String> stylizedB = new TableColumn<>("B");
        stylizedB.setCellValueFactory(cellData -> cellData.getValue().stylizedBProperty());  //cellData.getValue().codeNameProperty()); // Updated line

        codeNameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.6));
        playerScoreColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        stylizedB.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));

        tableView.getColumns().setAll(codeNameColumn, playerScoreColumn, stylizedB);

        tableView.setItems(players);

    }

    public void setPlayers(ObservableList<Player> p)
    {
        this.players = p;
    }
}
