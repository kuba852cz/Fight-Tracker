module com.example.fighttracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.fighttracker to javafx.fxml;
    exports com.example.fighttracker;
}