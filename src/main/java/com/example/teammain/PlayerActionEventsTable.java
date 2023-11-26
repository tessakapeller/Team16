package com.example.teammain;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PlayerActionEventsTable {
    public ObservableList<String> Events = null;

    @FXML
    public void actionTables(TableView<String> tableView) {

        TableColumn<ObservableValue<String>, String> eventColumn = new TableColumn<>("Events");
        eventColumn.setCellValueFactory(cellData -> cellData.getValue()); // Updated line

        tableView.setItems(Events);
    }

    public void addEvent(String event) {
        Events.add(event);
        if(Events.size() >= 11) {
            for(int i = 0; i < 10; i++) {
                Events.set(i, Events.get(i + 1));
            }
        }
    }
}
