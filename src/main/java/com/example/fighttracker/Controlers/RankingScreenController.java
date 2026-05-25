package com.example.fighttracker.Controlers;

import com.example.fighttracker.Logic.AppData;
import com.example.fighttracker.Models.Config;
import com.example.fighttracker.Models.Fighter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class RankingScreenController {

    private final String[] weightClasses = {"Flyweight", "Bantamweight", "Featherweight", "Lightweight", "Welterweight", "Middleweight", "Light heavyweight", "Heavyweight"};
    private int currentWeightIndex = 3;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private ListView<Fighter> listOfFighters;

    @FXML
    private Label labelWeightClass;

    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;

    @FXML
    public void initialize(){

        updateWeightClassLabel();
        setupListView();

        if (Config.isDarkMode){
            Platform.runLater(()->{
                mainAnchorPane.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a;");
            });
        }

        loadDataForCurrentWeight();
    }

    @FXML
    public void onLeftButtonClick() {
        if (currentWeightIndex > 0) {
            currentWeightIndex--;
            updateWeightClassLabel();
            loadDataForCurrentWeight();
        }
    }

    @FXML
    public void onRightButtonClick() {
        if (currentWeightIndex < weightClasses.length - 1) {
            currentWeightIndex++;
            updateWeightClassLabel();
            loadDataForCurrentWeight();
        }
    }

    private void updateWeightClassLabel() {
        labelWeightClass.setText(weightClasses[currentWeightIndex]);
    }

    private void setupListView() {
        listOfFighters.setCellFactory(listView -> new ListCell<Fighter>() {
            @Override
            protected void updateItem(Fighter fighter, boolean empty) {
                super.updateItem(fighter, empty);
                if (empty || fighter == null) {
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fighttracker/FighterCell.fxml"));
                        Parent root = loader.load();

                        FighterCellController cellController = loader.getController();
                        cellController.setFighter(fighter);

                        setGraphic(root);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void loadDataForCurrentWeight() {
        String currentCategory = weightClasses[currentWeightIndex];
        ObservableList<Fighter> fightersData = FXCollections.observableArrayList();

        AppData appData = new AppData();
        appData.loadFighters();
        List<Fighter> fighters = appData.getDivision(currentCategory);

        if (fighters != null) {
            fightersData.addAll(fighters);
        }

        listOfFighters.setItems(fightersData);
    }

}
