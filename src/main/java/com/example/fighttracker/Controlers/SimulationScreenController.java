package com.example.fighttracker.Controlers;

import com.example.fighttracker.Logic.AppData;
import com.example.fighttracker.Models.Config;
import com.example.fighttracker.Models.FightResult;
import com.example.fighttracker.Models.FightSimulation;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the Fight Simulation screen.
 * Manages weight class navigation, fighter selection logic, and prepares data for the fight simulation.
 */

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
    private Label labelNameLeft;

    @FXML
    private Label labelNicknameLeft;

    @FXML
    private Label labelNameRight;

    @FXML
    private Label labelNicknameRight;

    @FXML
    private ImageView imageFighterLeft;

    @FXML
    private ImageView imageFighterRight;

    /**
     * Navigates to the previous weight class.
     * Updates the UI, loads the corresponding fighters, and clears the current selection in the middle ring.
     */

    @FXML
    public void onLeftButtonClick() {
        if (currentWeightIndex > 0) {
            currentWeightIndex--;
            updateWeightClassLabel();
            loadDataForCurrentWeight();

            clearMiddleRing();
        }
    }

    /**
     * Navigates to the next weight class.
     * Updates the UI, loads the corresponding fighters, and clears the current selection in the middle ring.
     */

    @FXML
    public void onRightButtonClick() {
        if (currentWeightIndex < weightClasses.length - 1) {
            currentWeightIndex++;
            updateWeightClassLabel();
            loadDataForCurrentWeight();

            clearMiddleRing();
        }
    }

    private final String[] weightClasses = {"Flyweight", "Bantamweight", "Featherweight", "Lightweight", "Welterweight", "Middleweight", "Light heavyweight", "Heavyweight"};
    private int currentWeightIndex = 3;

    /**
     * Initializes the simulation screen setup.
     * Applies themes, configures list views, and loads the initial default weight class data.
     */

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
        fighterSelectedAction();
    }

    /**
     * Returns the user to the main title screen.
     */

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

    /**
     * Updates the label displaying the currently active weight class.
     */

    private void updateWeightClassLabel() {
        labelWeightClass.setText(weightClasses[currentWeightIndex]);
    }

    /**
     * Configures the custom cell factories for the left and right ListView components.
     * Implements the logic to visually lock and disable a fighter if they are already selected in the opposing list.
     */

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

                        Fighter selectedRight = listOfFightersRight.getSelectionModel().getSelectedItem();
                        if (fighter.equals(selectedRight)) {
                            root.setDisable(true);
                            root.setStyle("-fx-opacity: 0.4;");
                        } else {
                            root.setDisable(false);
                            root.setStyle("");
                        }

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

                        Fighter selectedLeft = listOfFightersLeft.getSelectionModel().getSelectedItem();
                        if (fighter.equals(selectedLeft)) {
                            root.setDisable(true);
                            root.setStyle("-fx-opacity: 0.4;");
                        } else {
                            root.setDisable(false);
                            root.setStyle("");
                        }

                        setGraphic(root);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    /**
     * Fetches fighter data for the currently selected weight class from the application data
     * and populates both the left and right list views.
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

        listOfFightersLeft.setItems(fightersData);
        listOfFightersRight.setItems(fightersData);
    }

    /**
     * Sets up double-click event handlers for fighter selection.
     * Updates the central display with the selected fighter's details (name, nickname, photo)
     * and ensures mutually exclusive selection by clearing duplicates from the opposing list.
     */

    private void fighterSelectedAction() {
        listOfFightersLeft.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Fighter selectedLeft = listOfFightersLeft.getSelectionModel().getSelectedItem();
                Fighter selectedRight = listOfFightersRight.getSelectionModel().getSelectedItem();

                if (selectedLeft != null && selectedLeft.equals(selectedRight)) {
                    listOfFightersRight.getSelectionModel().clearSelection();
                    labelNameRight.setText("");
                    labelNicknameRight.setText("");
                    imageFighterRight.setImage(null);
                }


                if (selectedLeft != null) {
                    labelNameLeft.setText(selectedLeft.getName());
                    labelNicknameLeft.setText("\"" + selectedLeft.getNickname() + "\"");
                    try {
                        String urlPhoto = selectedLeft.getImagePath();
                        javafx.scene.image.Image photoImage = new javafx.scene.image.Image(urlPhoto, true);
                        imageFighterLeft.setImage(photoImage);
                    } catch (Exception e) {
                        System.out.println("Couldnt load the photo.");
                    }
                }
            }

            listOfFightersRight.refresh();
        });

        listOfFightersRight.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Fighter selectedRight = listOfFightersRight.getSelectionModel().getSelectedItem();
                Fighter selectedLeft = listOfFightersLeft.getSelectionModel().getSelectedItem();

                if (selectedRight != null && selectedRight.equals(selectedLeft)) {
                    listOfFightersLeft.getSelectionModel().clearSelection();
                    labelNameLeft.setText("");
                    labelNicknameLeft.setText("");
                    imageFighterLeft.setImage(null);
                }

                if (selectedRight != null) {
                    labelNameRight.setText(selectedRight.getName());
                    labelNicknameRight.setText("\"" + selectedRight.getNickname() + "\"");
                    try {
                        String urlPhoto = selectedRight.getImagePath();
                        javafx.scene.image.Image photoImage = new javafx.scene.image.Image(urlPhoto, true);
                        imageFighterRight.setImage(photoImage);
                    } catch (Exception e) {
                        System.out.println("Couldnt load the photo.");
                    }
                }
            }

            listOfFightersLeft.refresh();
        });
    }

    /**
     * Clears all fighter selections, names, and images from the central match-up display.
     * Used primarily when switching between weight classes to ensure a clean slate.
     */

    private void clearMiddleRing() {
        listOfFightersLeft.getSelectionModel().clearSelection();
        listOfFightersRight.getSelectionModel().clearSelection();

        imageFighterLeft.setImage(null);
        imageFighterRight.setImage(null);

        labelNameLeft.setText("");
        labelNicknameLeft.setText("");
        labelNameRight.setText("");
        labelNicknameRight.setText("");
    }

    /**
     * Executes the fight simulation between the two selected fighters and displays the result.
     * Loads a modal popup window (Winner Announcement), passes the fighters and the simulation
     * result data to its controller, and blocks interaction with the main window until closed.
     *
     * @param event The action event triggered by clicking the fight button, used to set the modal owner.
     */

    @FXML
    public void onFightButtonClick(ActionEvent event){

        Fighter fighterLeft = listOfFightersLeft.getSelectionModel().getSelectedItem();
        Fighter fighterRight = listOfFightersRight.getSelectionModel().getSelectedItem();

        if (fighterLeft == null || fighterRight == null) {
            System.out.println("You have to select both fighters!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fighttracker/WinnerAnnoucment.fxml"));
            Parent root = loader.load();

            FightSimulation fightSimulation = new FightSimulation();
            FightResult result = fightSimulation.runSimulator(fighterLeft, fighterRight);

            WinnerAnnoucmentController winnerAnnoucmentController = loader.getController();
            winnerAnnoucmentController.setWinnerAnnoucmentData(fighterLeft, fighterRight, result);

            Stage modalStage = new Stage();
            modalStage.setTitle("Fight result!");
            modalStage.setScene(new Scene(root));

            modalStage.initModality(Modality.APPLICATION_MODAL);

            Stage parentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            modalStage.initOwner(parentStage);

            modalStage.showAndWait();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
