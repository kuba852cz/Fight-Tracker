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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SimulationScreenController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private ListView<Fighter> listOfFightersLeft;

    @FXML
    private ListView<Fighter> listOfFightersRight;

    @FXML
    private Label labelWeightClass;


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

    private final String[] weightClasses = {"Flyweight", "Bantamweight", "Featherweight", "Lightweight", "Welterweight", "Middleweight", "Light heavyweight", "Heavyweight"};
    private int currentWeightIndex = 3;

    @FXML
    public void initialize() {

        updateWeightClassLabel();
        setupListView();

        if (Config.isDarkMode) {
            Platform.runLater(() -> mainAnchorPane.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a;"));
            listOfFightersLeft.setStyle("-fx-control-inner-background: #1a1a1a; -fx-background-color: #1a1a1a; -fx-padding: -1;");
            listOfFightersRight.setStyle("-fx-control-inner-background: #1a1a1a; -fx-background-color: #1a1a1a; -fx-padding: -1;");
        }

        loadDataForCurrentWeight();
    }

    @FXML
    public void onBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fighttracker/TitleScreen.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setMaximized(false);
        stage.setTitle("Fight Tracker");
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.show();
    }

    private void updateWeightClassLabel() {
        labelWeightClass.setText(weightClasses[currentWeightIndex]);
    }

    private void setupListView() {
        listOfFightersLeft.setCellFactory(listView -> new ListCell<>() {
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

        listOfFightersRight.setCellFactory(listView -> new ListCell<>() {
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

        listOfFightersLeft.setItems(fightersData);
        listOfFightersRight.setItems(fightersData);
    }

}
