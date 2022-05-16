module com.gallifrey.mazegame4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gallifrey.mazegame4 to javafx.fxml;
    exports com.gallifrey.mazegame4;
}