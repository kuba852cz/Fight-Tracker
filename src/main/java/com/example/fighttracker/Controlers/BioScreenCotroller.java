package com.example.fighttracker.Controlers;

import com.example.fighttracker.Models.Fighter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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
    @FXML private ImageView labelFlag;
    @FXML private ImageView labelPhoto;




    @FXML
    public void initialize(){

    }

    public void setFighterData(Fighter fighter){

    }
}
