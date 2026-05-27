package com.example.fighttracker.Controlers;

import com.example.fighttracker.Models.Config;
import com.example.fighttracker.Models.Fighter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class SimulationScreenController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    public void initialize(){
        if (Config.isDarkMode){
            Platform.runLater(()-> mainAnchorPane.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a;"));
        }



    }

    @FXML
    public void onBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fighttracker/TitleScreen.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        stage.setMaximized(false);
        stage.setTitle("Fight Tracker");
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.show();
    }

    public void fightSimulation(Fighter fighterA, Fighter fighterB){
        int totalFightsA = (fighterA.getWins() + fighterA.getLosses() + fighterA.getDraws());
        int totalFightsB = (fighterB.getWins() + fighterB.getLosses() + fighterB.getDraws());

        double winRatioA;
        double winRatioB;

        if (totalFightsA == 0){
            winRatioA = 0.5;
        } else {
            winRatioA = (double) (fighterA.getWins()) / totalFightsA;
        }

        if (totalFightsB == 0){
            winRatioB = 0.5;
        } else {
            winRatioB = (double) (fighterB.getWins()) / totalFightsB;
        }

        double totalWinRatio = winRatioA+winRatioB;
        double drawMargin = totalWinRatio*0.025;

        Random random = new Random();

        double winRandom = random.nextDouble(0,(winRatioA+winRatioB));


        if (winRandom > (winRatioA + drawMargin)){
            //B vyhra
        } else if (winRandom < (winRatioA - drawMargin)) {
            // A vyhra
        } else  {
            //remiza
        }




    }

}
