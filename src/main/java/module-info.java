module com.example.de2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.de2 to javafx.fxml;
    exports com.example.de2;
}