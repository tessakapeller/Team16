module com.example.teammain {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.teammain to javafx.fxml;
    exports com.example.teammain;
}