module com.example.fighttracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.fighttracker to javafx.fxml;
    exports com.example.fighttracker.Controlers;
    opens com.example.fighttracker.Controlers to javafx.fxml;
    exports com.example.fighttracker.Logic;
    opens com.example.fighttracker.Logic to javafx.fxml;
}