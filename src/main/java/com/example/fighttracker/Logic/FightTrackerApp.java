package com.example.fighttracker.Logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The core JavaFX application class.
 * Responsible for initializing the primary stage and loading the initial Title Screen.
 */

public class FightTrackerApp extends Application {

    /**
     * The main entry point for the JavaFX application.
     * Loads the FXML layout for the title screen and displays the main window.
     *
     * @param stage The primary stage for this application.
     * @throws IOException If the specified FXML file cannot be loaded.
     */

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FightTrackerApp.class.getResource("/com/example/fighttracker/TitleScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setTitle("Fight Tracker");
        stage.setScene(scene);
        stage.show();
    }
}
