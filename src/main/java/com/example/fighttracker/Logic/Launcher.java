package com.example.fighttracker.Logic;

import javafx.application.Application;

/**
 * The main entry point for the application.
 * This separate launcher class is used to start the JavaFX application
 * properly without requiring strict module-path configurations.
 */

public class Launcher {

    /**
     * The main method that launches the JavaFX application lifecycle.
     */
    public static void main(String[] args) {
        Application.launch(FightTrackerApp.class, args);
    }

}
