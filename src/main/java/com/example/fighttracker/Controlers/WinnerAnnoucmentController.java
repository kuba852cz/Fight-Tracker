package com.example.fighttracker.Controlers;

import com.example.fighttracker.Models.Config;
import com.example.fighttracker.Models.FightResult;
import com.example.fighttracker.Models.Fighter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the Winner Announcement popup screen.
 * Handles the display of the fight simulation results, including fighter details,
 * the specific outcome (winner, loser, or draw), and fight statistics (round, time, method).
 */

public class WinnerAnnoucmentController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML private Label labelWinnerLeft;
    @FXML private Label labelNameLeft;
    @FXML private Label labelNicknameLeft;
    @FXML private Label labelWinnerRight;
    @FXML private Label labelNameRight;
    @FXML private Label labelNicknameRight;
    @FXML private Label labelTypeOfFinish;
    @FXML private Label labelRound;
    @FXML private Label labelTime;
    @FXML private ImageView imageLeft;
    @FXML private ImageView imageRight;


    /**
     * Initializes the controller after its root element has been completely processed.
     * Applies the dark mode theme if enabled in the global configuration.
     */

    @FXML
    public void initialize() {

        if (Config.isDarkMode) {
            Platform.runLater(() -> mainAnchorPane.getScene().getRoot().setStyle("-fx-base: #1a1a1a; -fx-background-color: #1a1a1a;"));
        }

    }

    /**
     * Populates the announcement screen with data from the completed fight simulation.
     * Dynamically updates the UI to reflect who won, lost, or if it was a draw,
     * and loads the corresponding fighter images.
     *
     * @param fighterLeft The fighter displayed on the left side of the screen.
     * @param fighterRight The fighter displayed on the right side of the screen.
     * @param result The FightResult object containing the outcome of the simulation.
     */
    public void setWinnerAnnoucmentData(Fighter fighterLeft, Fighter fighterRight, FightResult result){
        labelNameLeft.setText(fighterLeft.getName());
        labelNicknameLeft.setText("\"" + fighterLeft.getNickname() + "\"");
        labelNameRight.setText(fighterRight.getName());
        labelNicknameRight.setText("\"" + fighterRight.getNickname() + "\"");
        labelRound.setText("Round " + result.getEndRound());
        labelTypeOfFinish.setText(result.getFinishType());
        labelTime.setText(result.getTime());

        if (fighterLeft == result.getWinner()){
            labelWinnerLeft.setText("WINNER!");
            labelWinnerRight.setText("LOSER");
        } else if (fighterRight == result.getWinner()){
            labelWinnerLeft.setText("LOSER");
            labelWinnerRight.setText("WINNER!");
        } else {
            labelWinnerLeft.setText("DRAW");
            labelWinnerRight.setText("DRAW");
        }

        try {
            String urlPhoto = fighterLeft.getImagePath();

            javafx.scene.image.Image photoImage = new javafx.scene.image.Image(urlPhoto, true);
            imageLeft.setImage(photoImage);
        } catch (Exception e) {
            System.out.println("Couldnt load the photo.");
        }

        try {
            String urlPhoto = fighterRight.getImagePath();

            javafx.scene.image.Image photoImage = new javafx.scene.image.Image(urlPhoto, true);
            imageRight.setImage(photoImage);
        } catch (Exception e) {
            System.out.println("Couldnt load the photo.");
        }

    }

    /**
     * Closes the winner announcement modal window and returns the user to the simulation screen.
     *
     * @param event The action event triggered by clicking the back/close button.
     */

    @FXML
    public void onBackButtonClick(ActionEvent event) {

        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        stage.close();
    }


}
