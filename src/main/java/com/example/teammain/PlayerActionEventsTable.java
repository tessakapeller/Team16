package com.example.teammain;

import com.example.teammain.DBUtils.DBMethods;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PlayerActionEventsTable {
    public ObservableList<Events> events = FXCollections.observableArrayList();

    @FXML
    public void actionTables(TableView<Events> tableView) {

        TableColumn<Events, String> event = new TableColumn<>("Event");
        event.setCellValueFactory(cellData -> cellData.getValue().eventProperty()); // Updated line
        event.prefWidthProperty().bind(tableView.widthProperty().multiply(1));
        tableView.getColumns().setAll(event);
        tableView.setItems(events);
    }

    public void addEvent(String event) {
        Events newEvent = new Events(event);
        events.add(newEvent);
    }
}
