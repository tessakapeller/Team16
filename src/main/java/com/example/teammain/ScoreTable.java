package com.example.teammain;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

class Scores{
    private SimpleStringProperty redTotalScore;
    private SimpleStringProperty greenTotalScore;

    Scores(){
        this.redTotalScore = new SimpleStringProperty("0");
        this.greenTotalScore = new SimpleStringProperty("0");
    }

    public SimpleStringProperty redTotalScoreProperty() {
        return redTotalScore;
    }

    public SimpleStringProperty greenTotalScoreProperty() {
        return greenTotalScore;
    }

    public void setRedTotalScore(String redTotalScore) {
        this.redTotalScore.set(redTotalScore);
    }

    public void setGreenTotalScore(String greenTotalScore) {
        this.greenTotalScore.set(greenTotalScore);
    }
}

public class ScoreTable {


    public int teamScore = 0;

    public ObservableList<Scores> score = FXCollections.observableArrayList();

    @FXML
    public void actionTables(TableView<Scores> tableView) {

        score.add(new Scores());

        TableColumn<Scores, String> green = new TableColumn<>("green");
        green.setCellValueFactory(cellData -> cellData.getValue().greenTotalScoreProperty());

        TableColumn<Scores, String> red = new TableColumn<>("red");
        red.setCellValueFactory(cellData -> cellData.getValue().redTotalScoreProperty()); // Updated line


        green.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));
        red.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));

        tableView.getColumns().setAll(green, red);


        tableView.setItems(score);

    }


}
