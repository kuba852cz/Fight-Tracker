package com.example.fighttracker.Controlers;

import com.example.fighttracker.Models.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;
import java.sql.SQLXML;

public class SettingsScreenController {

    @FXML
    private ChoiceBox<String> weightFormat;

    @FXML
    private ChoiceBox<String> heightFormat;

    @FXML
    private ToggleSwitch darkModeSwitch;

    @FXML
    private VBox mainVBox;

    @FXML
    public void initialize(){
     weightFormat.getItems().addAll("kg", "lb");
     heightFormat.getItems().addAll("cm", "ft");

     weightFormat.setValue(Config.weightUnit);
     heightFormat.setValue(Config.heightUnit);
     darkModeSwitch.setSelected(Config.isDarkMode);

     darkModeSwitch.selectedProperty().addListener((obs, oldValue, newValue) -> {
         Config.isDarkMode = newValue;

         if (newValue){
             darkModeSwitch.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a; -fx-text-base-color: white;");         } else {
             darkModeSwitch.getScene().getRoot().setStyle("");
         }

     });
    }

    @FXML
    public void onBackButtonClick(ActionEvent event) throws IOException {
        Config.isDarkMode = darkModeSwitch.isSelected();
        Config.weightUnit = weightFormat.getValue();
        Config.heightUnit = heightFormat.getValue();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fighttracker/TitleScreen.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1024, 576);
        stage.setScene(scene);
        stage.show();
    }

}
