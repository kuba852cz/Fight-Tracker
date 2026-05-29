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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the Ranking screen.
 * Displays a ranked list of fighters by weight division and handles navigation to detailed fighter biographies.
 */

public class RankingScreenController {

    private final String[] weightClasses = {"Flyweight", "Bantamweight", "Featherweight", "Lightweight", "Welterweight", "Middleweight", "Light heavyweight", "Heavyweight"};
    private int currentWeightIndex = 3;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private ListView<Fighter> listOfFighters;

    @FXML
    private Label labelWeightClass;

    /**
     * Initializes the ranking screen.
     * Applies the current theme, loads the default weight class data, and sets up a double-click listener
     * on the list view to navigate to a specific fighter's biography screen.
     */

    @FXML
    public void initialize(){

        updateWeightClassLabel();
        setupListView();

        if (Config.isDarkMode){
            Platform.runLater(()-> mainAnchorPane.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a;"));
            listOfFighters.setStyle("-fx-control-inner-background: #1a1a1a; -fx-background-color: #1a1a1a; -fx-padding: -1;");
        }

        loadDataForCurrentWeight();

        listOfFighters.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Fighter selected = listOfFighters.getSelectionModel().getSelectedItem();

                if (selected != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fighttracker/BioScreen.fxml"));
                        Parent root = loader.load();

                        BioScreenCotroller bioScreenCotroller = loader.getController();
                        bioScreenCotroller.setFighterData(selected);

                        Scene currentScene = ((javafx.scene.Node) event.getSource()).getScene();

                        currentScene.setRoot(root);

                        Stage stage = (Stage) currentScene.getWindow();
                        stage.setTitle("Fight Tracker - " + selected.getName() + " BIO");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    /**
     * Navigates to the previous weight class, updating the UI and reloading the fighter list.
     */

    @FXML
    public void onLeftButtonClick() {
        if (currentWeightIndex > 0) {
            currentWeightIndex--;
            updateWeightClassLabel();
            loadDataForCurrentWeight();
        }
    }

    /**
     * Navigates to the next weight class, updating the UI and reloading the fighter list.
     */

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

    /**
     * Configures the custom cell factory for the ListView to render each fighter using the FighterCell layout.
     */

    private void setupListView() {
        listOfFighters.setCellFactory(listView -> new ListCell<>() {
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

    /**
     * Loads fighter data from the data source for the currently selected weight class
     * and updates the ListView items.
     */

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

    /**
     * Returns the user to the main title screen.
     */

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
