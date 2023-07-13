package com.example.xiangqi.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class PlayerTurn extends Application {

    private String playerRed =  "Red turn to moves"; // the current player
    private String playerBlack = "Black turn to moves"

    @Override
    public void start(Stage stage) throws Exception {

        // create a label to display the player's turn
        Label turnLabel = new Label(playerRed);

        // update the label with the current player's turn
        //turnLabel.setText(playerBlack);

        // create a stack pane to hold the label
        StackPane pane = new StackPane(turnLabel);

        // create a scene with the stack pane
        Scene scene = new Scene(pane, 200, 100);

        // set the stage's title and scene
        stage.setTitle("Player's Turn");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void pieceEaten() {
        Image
    }
    public static void main(String[] args) {
        launch(args);
    }
}