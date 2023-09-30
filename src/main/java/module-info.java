module com.example.teammain {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.teammain to javafx.fxml;
    exports com.example.teammain;
}