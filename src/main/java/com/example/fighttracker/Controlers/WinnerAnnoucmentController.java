package com.example.fighttracker.Controlers;

import com.example.fighttracker.Models.Config;
import com.example.fighttracker.Models.FightResult;
import com.example.fighttracker.Models.Fighter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class WinnerAnnoucmentController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    public void initialize() {

        if (Config.isDarkMode) {
            Platform.runLater(() -> mainAnchorPane.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a;"));
        }

    }

    public void setWinnerAnnoucmentData(Fighter fighterLeft, Fighter fighterRight, FightResult result){


    }


}
