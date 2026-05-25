package com.example.fighttracker.Controlers;

import com.example.fighttracker.Models.Config;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TitleScreenController {

    @FXML
    private VBox mainVBox;

    @FXML
    private ImageView logoImageView;

    @FXML
    public void initialize(){

        if (Config.isDarkMode){
            Platform.runLater(()->{
                mainVBox.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a;");
            });
        }

        String imagePath = Objects.requireNonNull(getClass().getResource("/FightTracker_Logo.png")).toExternalForm();
        logoImageView.setImage(new Image(imagePath));
        logoImageView.fitWidthProperty().bind(mainVBox.widthProperty());
    }

    @FXML
    protected void onRankingButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fighttracker/RankingScreen.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1024, 576);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Fight Tracker - Ranking");
        stage.show();
    }

    @FXML
    protected void onSimulationButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fighttracker/SimulationScreen.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1024, 576);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Fight Tracker - Simulation");
        stage.show();
    }

    @FXML
    protected void onSettingsButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fighttracker/SettingsScreen.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Fight Tracker - Settings");
        stage.show();
    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }
}
