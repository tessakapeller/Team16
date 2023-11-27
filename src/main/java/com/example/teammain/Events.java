package com.example.teammain;

import javafx.beans.property.SimpleStringProperty;

public class Events {
    private final SimpleStringProperty name;

    public Events(String event) {
        this.name = new SimpleStringProperty(event);
    }

    public SimpleStringProperty eventProperty() {
        return name;
    }
}
