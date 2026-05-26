package com.example.fighttracker.Controlers;

import com.example.fighttracker.Models.Config;
import com.example.fighttracker.Models.Fighter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BioScreenCotroller {

    @FXML
    private BorderPane mainBorderPane;
    @FXML private Label labelName;
    @FXML private Label labelNickname;
    @FXML private Label labelCountry;
    @FXML private Label labelAge;
    @FXML private Label labelHeight;
    @FXML private Label labelWeight;
    @FXML private Label labelReach;
    @FXML private Label labelWeightClass;
    @FXML private Label labelRank;
    @FXML private Label labelFightingStyle;
    @FXML private Label labelStance;
    @FXML private Label labelWins;
    @FXML private Label labelLosses;
    @FXML private Label labelDraws;
    @FXML private Label labelKo;
    @FXML private Label labelSub;
    @FXML private Label labelDec;
    @FXML private ImageView imageFlag;
    @FXML private ImageView imagePhoto;
    @FXML private ProgressBar progressBarWins;
    @FXML private ProgressBar progressBarLosses;
    @FXML private ProgressBar progressBarDraws;




    @FXML
    public void initialize(){

        if (Config.isDarkMode){
            Platform.runLater(()-> mainBorderPane.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a;"));
        }

    }

    public void setFighterData(Fighter fighter){
        labelName.setText(fighter.getName());
        labelNickname.setText(fighter.getNickname());
        labelCountry.setText(fighter.getCountry());
        labelAge.setText(String.valueOf(fighter.getAge()));
        labelWeightClass.setText(fighter.getWeightClass());
        labelRank.setText(fighter.getRank());
        labelFightingStyle.setText(fighter.getFightingStyle());
        labelStance.setText(fighter.getStance());
        labelWins.setText(String.valueOf(fighter.getWins()));
        labelLosses.setText(String.valueOf(fighter.getLosses()));
        labelDraws.setText(String.valueOf(fighter.getDraws()));
        labelKo.setText(String.valueOf(fighter.getKo()));
        labelSub.setText(String.valueOf(fighter.getSubmissions()));
        labelDec.setText(String.valueOf(fighter.getDecisions()));

        if (Config.weightUnit.equals("lb")){
            double weight = fighter.getWeight()*2.20462262;
            labelWeight.setText(String.valueOf(weight) + " lb");
        }else{
            labelWeight.setText(String.valueOf(fighter.getWeight()) + " kg");
        }
        if (Config.heightUnit.equals("ft")){
            double height = fighter.getHeight()*0.032808399;
            double reach = fighter.getReach()*0.032808399;
            labelHeight.setText(String.valueOf(height));
            labelReach.setText(String.valueOf(reach));
        }else{
            labelHeight.setText(String.valueOf(fighter.getHeight()));
            labelReach.setText(String.valueOf(fighter.getReach()));
        }

        try {
            String urlFlag = fighter.getFlag();
            String urlPhoto = fighter.getImagePath();

            javafx.scene.image.Image flagImage = new javafx.scene.image.Image(urlFlag, true);
            imageFlag.setImage(flagImage);
            javafx.scene.image.Image photoImage = new javafx.scene.image.Image(urlPhoto, true);
            imagePhoto.setImage(photoImage);
        } catch (Exception e) {
            System.out.println("Nepodařilo se načíst vlajku z odkazu.");
        }

        progressBarWins.setProgress((double) fighter.getWins() /(fighter.getWins()+fighter.getDraws()+fighter.getLosses()));
        progressBarLosses.setProgress((double) fighter.getLosses() /(fighter.getWins()+fighter.getDraws()+fighter.getLosses()));
        progressBarDraws.setProgress((double) fighter.getDraws() /(fighter.getWins()+fighter.getDraws()+fighter.getLosses()));

    }

    @FXML
    public void onBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fighttracker/RankingScreen.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Fight Tracker - Rankings");
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
}
