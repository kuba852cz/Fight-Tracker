package com.example.fighttracker.Controlers;

import com.example.fighttracker.Models.Config;
import com.example.fighttracker.Models.Fighter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FighterCellController {

    @FXML
    private HBox mainHBox;

    @FXML
    private Label labelRank;

    @FXML
    private Label labelFlag;

    @FXML
    private Label labelName;

    @FXML
    private Label labelRecord;

    @FXML
    public void initialize(){
        if (Config.isDarkMode){
            Platform.runLater(()->{
                mainHBox.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a;");
            });
        }
    }

    public void setFighter(Fighter fighter){
        labelRank.setText(String.valueOf(fighter.getRank()));
        labelFlag.setText(fighter.getFlag());
        labelName.setText(fighter.getName());
        labelRecord.setText(fighter.getRecord());
    }

}
