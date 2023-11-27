package com.example.teammain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;

public class PlayerActionTable {


    public int teamScore = 0;

    public ObservableList<Player> players = null;

    @FXML
    public void actionTables(TableView<Player> tableView) {

        TableColumn<Player, String> stylizedB = new TableColumn<>("B");
        stylizedB.setCellValueFactory(cellData -> cellData.getValue().stylizedBProperty());  //cellData.getValue().codeNameProperty()); // Updated line

        TableColumn<Player, String> codeNameColumn = new TableColumn<>("CodeName");
        codeNameColumn.setCellValueFactory(cellData -> cellData.getValue().codeNameProperty()); // Updated line

        TableColumn<Player, String> playerScoreColumn = new TableColumn<>("PlayerScore");
        playerScoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());  //cellData.getValue().codeNameProperty()); // Updated line


        stylizedB.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        codeNameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.6));
        playerScoreColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));

        tableView.getColumns().setAll(stylizedB, codeNameColumn, playerScoreColumn);


        tableView.setItems(players);

    }

    public void setPlayers(ObservableList<Player> p)
    {
        this.players = p;
    }

    public ObservableList<Player> getPlayers() {
        return players;
    }

    public boolean isPlayerExist(int id) {
        return players.stream().anyMatch(player -> player.equipmentCodeProperty().get().equals(String.valueOf(id)));
    }

    public Player getPlayer(int id) {
        return players.stream().filter(player -> player.equipmentCodeProperty().get().equals(String.valueOf(id))).findFirst().get();
    }

    public void addScore(int id, int score) {
        Player player = getPlayer(id);
        player.scoreProperty().set(String.valueOf(Integer.parseInt(player.scoreProperty().get()) + score));
        teamScore += score;
    }

    public void updateB(Player player)
    {
        player.stylizedBProperty().set("B");
    }
}
