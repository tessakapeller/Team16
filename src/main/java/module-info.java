module com.example.teammain {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.teammain to javafx.fxml;
    exports com.example.teammain;
}