package com.example.teammain;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PlayerActionEventsTable {
    public ObservableList<String> events = FXCollections.observableArrayList();

    @FXML
    public void actionTables(TableView<String> tableView) {

        TableColumn<ObservableValue<String>, String> eventColumn = new TableColumn<>("Events");
        eventColumn.setCellValueFactory(cellData -> cellData.getValue()); // Updated line

        tableView.setItems(events);
    }

    public void addEvent(String event) {
        events.add(event);
//        if(events.size() >= 11) {
//            for(int i = 0; i < 10; i++) {
//                events.set(i, events.get(i + 1));
//            }
//        }
    }
}
