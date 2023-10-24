package com.example.teammain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;

public class PlayerActionTable {

    private ObservableList<Player> players = FXCollections.observableArrayList();

    @FXML
    public void actionTables(TableView<Player> tableView) {

        String tempScore = "1000";

        TableColumn<Player, String> codeNameColumn = new TableColumn<>("CodeName");
        codeNameColumn.setCellValueFactory(cellData -> cellData.getValue().codeNameProperty()); // Updated line

        TableColumn<Player, String> playerScore = new TableColumn<>("PlayerScore");
        codeNameColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());  //cellData.getValue().codeNameProperty()); // Updated line

        codeNameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.7));
        playerScore.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));

        tableView.getColumns().setAll(codeNameColumn, playerScore);
        tableView.setItems(players);


    }
}
