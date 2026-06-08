package com.example.fighttracker.Controlers;

import com.example.fighttracker.Models.Config;
import com.example.fighttracker.Models.Fighter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Controller for the custom list cell representing a single fighter.
 * Manages the visual layout and data binding for individual items in the ListView.
 */

public class FighterCellController {

    @FXML
    private HBox mainHBox;

    @FXML
    private Label labelRank;

    @FXML
    private ImageView imageFlag;

    @FXML
    private Label labelName;

    @FXML
    private Label labelRecord;

    /**
     * Initializes the cell controller.
     * Applies dark mode styling to the cell components if configured globally.
     */

    @FXML
    public void initialize() {
        if (Config.isDarkMode) {
            mainHBox.setStyle("-fx-background-color: #2b2b2b;");

            labelName.setStyle("-fx-text-fill: white;");
            labelRecord.setStyle("-fx-text-fill: white;");
            labelRank.setStyle("-fx-text-fill: white;");
        }
    }

    /**
     * Populates the cell's UI elements with data from the provided Fighter object.
     * Sets the text fields and dynamically loads the fighter's country flag image.
     *
     * @param fighter The Fighter object containing the data to display.
     */

    public void setFighter(Fighter fighter) {
        labelRank.setText(String.valueOf(fighter.getRank()));
        labelName.setText(fighter.getName());
        labelRecord.setText(fighter.getRecord());

        try {
            String url = fighter.getFlag();
            javafx.scene.image.Image flagImage = new javafx.scene.image.Image(url, true);
            imageFlag.setImage(flagImage);
        } catch (Exception e) {
            System.out.println("Couldnt load the photo.");
        }
    }
}


