package com.example.teammain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PlayerEntryController {
    private ObservableList<Player> players = FXCollections.observableArrayList();

    @FXML
    public void initTable(TableView<Player> tableView) {
        TableColumn<Player, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject()); // Updated line
        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty()); // Updated line
        idColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        nameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.7));
        tableView.getColumns().setAll(idColumn, nameColumn);
        tableView.setItems(players);
    }

//    @FXML
//    public void handleAddPlayer(ActionEvent event) {
//        String playerName = playerNameField.getText().trim();
//        if (!playerName.isEmpty()) {
//            addPlayer(playerName);
//            playerNameField.clear();
////            playerTableView.refresh();
//        }
//    }

    public void addPlayer(String name) {
        players.add(new Player(players.size() + 1, name));
    }

    public ObservableList<Player> getPlayers() {
        return players;
    }

    public void resetPlayers(){
        players = FXCollections.observableArrayList();
    }


}

class Player {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;

    public Player(int id, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

}
