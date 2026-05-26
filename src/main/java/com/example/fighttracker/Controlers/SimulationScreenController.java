package com.example.fighttracker.Controlers;

import com.example.fighttracker.Models.Config;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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


}
