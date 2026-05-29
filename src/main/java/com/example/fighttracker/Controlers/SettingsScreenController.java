package com.example.fighttracker.Controlers;

import com.example.fighttracker.Models.Config;
import javafx.application.Platform;
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

/**
 * Controller for the Settings screen.
 * Manages user preferences such as unit formats (weight/height) and application appearance (dark mode).
 */

public class SettingsScreenController {

    @FXML
    private ChoiceBox<String> weightFormat;

    @FXML
    private ChoiceBox<String> heightFormat;

    @FXML
    private ToggleSwitch darkModeSwitch;

    @FXML
    private VBox mainVBox;

    /**
     * Initializes the settings UI components.
     * Populates the choice boxes, sets their values based on the current global configuration,
     * and attaches a listener to the dark mode toggle for real-time theme switching.
     */

    @FXML
    public void initialize(){
     weightFormat.getItems().addAll("kg", "lb");
     heightFormat.getItems().addAll("cm", "ft");

     weightFormat.setValue(Config.weightUnit);
     heightFormat.setValue(Config.heightUnit);
     darkModeSwitch.setSelected(Config.isDarkMode);

        if (Config.isDarkMode) {
            Platform.runLater(() -> mainVBox.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a;"));
        }

     darkModeSwitch.selectedProperty().addListener((obs, oldValue, newValue) -> {
         Config.isDarkMode = newValue;

         if (newValue){
             darkModeSwitch.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a");
         } else {
             darkModeSwitch.getScene().getRoot().setStyle("");
         }

     });
    }

    /**
     * Saves the currently selected settings to the global configuration
     * and navigates the user back to the main title screen.
     */

    @FXML
    public void onBackButtonClick(ActionEvent event) throws IOException {
        Config.isDarkMode = darkModeSwitch.isSelected();
        Config.weightUnit = weightFormat.getValue();
        Config.heightUnit = heightFormat.getValue();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fighttracker/TitleScreen.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Fight Tracker");
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.show();
    }

}
