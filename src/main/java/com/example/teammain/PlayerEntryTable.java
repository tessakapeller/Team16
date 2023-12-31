package com.example.teammain;

import com.example.teammain.DBUtils.DBMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PlayerEntryTable {
    public ObservableList<Player> players = FXCollections.observableArrayList();
    //public

    @FXML
    public void initTable(TableView<Player> tableView) {
        TableColumn<Player, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty()); // Updated line

        TableColumn<Player, String> firstNameColumn = new TableColumn<>("FirstName");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty()); // Updated line
        TableColumn<Player, String> lastNameColumn = new TableColumn<>("LastName");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty()); // Updated line
        TableColumn<Player, String> codeNameColumn = new TableColumn<>("CodeName");
        codeNameColumn.setCellValueFactory(cellData -> cellData.getValue().codeNameProperty()); // Updated line
        TableColumn<Player, String> equipmentIdColumn = new TableColumn<>("equipment id");
        equipmentIdColumn.setCellValueFactory(cellData -> cellData.getValue().equipmentCodeProperty()); // Updated line
        idColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        firstNameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        lastNameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        codeNameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        equipmentIdColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        tableView.getColumns().setAll(idColumn, firstNameColumn, lastNameColumn, codeNameColumn, equipmentIdColumn);
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

    public boolean addPlayer(String id) {

        //check the database
        Player player = DBMethods.find(id);
        System.out.println(player.firstNameProperty().getValue());
        if(player.firstNameProperty().getValue() != "")
        {
            players.add(new Player(id, player.firstNameProperty().getValue(), player.lastNameProperty().getValue(), player.codeNameProperty().getValue()));
            return false;
        }
        else {
            players.add(new Player(id));
            return true;
        }
    }

    public void updateFirstName(String firstName)
    {
        Player temp = players.get(players.size() - 1);
        temp.setFirstName(firstName);
        players.set((players.size() - 1), temp);
    }

    public void updateLastName(String lastname)
    {
        Player temp = players.get(players.size() - 1);
        temp.setLastName(lastname);
        players.set((players.size() - 1), temp);
    }

    public void updateCodeName(String codename)
    {
        Player temp = players.get(players.size() - 1);
        temp.setCodeName(codename);
        players.set((players.size() - 1), temp);
        DBMethods.insert(temp);
    }

    public void updateEquipmentCode(String codename)
    {
        Player temp = players.get(players.size() - 1);
        temp.setEqupimentCode(codename);
        players.set((players.size() - 1), temp);
    }



    public ObservableList<Player> getPlayers() {
        return players;
    }

    public void resetPlayers(){
        players = FXCollections.observableArrayList();
    }


}

