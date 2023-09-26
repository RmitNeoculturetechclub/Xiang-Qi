package com.example.xiangqi.View;

import com.example.xiangqi.Controller.CheckGeneral;
import com.example.xiangqi.Controller.InitializeManager;
import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Model.General;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class StatusView {
    private final AnchorPane pane;
    private Label playerStatus;
    private Label timerStatus;
    private int remainingSeconds = 60; // 1 minute
    private Timeline timeline;
    private Cell[][] board;

    public StatusView() {
        this.pane = new AnchorPane();
        this.initializePane();
        startTimer();
    }

    private void initializePane() {
        // Player status
        playerStatus = new Label();
        playerStatus.setFont(new Font("Arial", 30));

        Label labelPlayerText = new Label();
        labelPlayerText.setText("Current Player: ");
        labelPlayerText.setFont(new Font("Arial", 30));

        HBox playerBox = new HBox(); // for one horizontal row
        playerBox.getChildren().addAll(labelPlayerText, playerStatus);

        // Timer status
        timerStatus = new Label();
        timerStatus.setFont(new Font("Arial", 30));

        VBox vBox = new VBox(); // for two vertical rows
        vBox.getChildren().addAll(playerBox, timerStatus);
        pane.getChildren().add(vBox);
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void updatePlayerStatus(String currentPlayer) {
        playerStatus.setText(currentPlayer);
    }

    public void updateBoard(Cell[][] board) {
        this.board = board;
    }

    private void startTimer() {
        timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                event -> {
                    if (remainingSeconds > 0) {
                        remainingSeconds--;
                        timerStatus.setText("Time remaining: " + remainingSeconds + " seconds");
                    } else {
                        timerStatus.setText("Time's up!");
                        timeline.stop(); // Stop the timer

                        CheckGeneral isCheckGeneral = new CheckGeneral();
                        String currentPlayer = playerStatus.getText();
                        // TODO: update the board
                        General general = (General) isCheckGeneral.findGeneral(currentPlayer, this.board).getPiece();
                        if (general != null) {
                            boolean isChecked = general.getChecked(currentPlayer);
                            // if the general is in checked -> game over
                            if (isChecked) {
                                // TODO: game over
                                // 1. display winner
                                displayWinner(""); // TODO: get the winner player name (count points?)
                                // 2. end the program

                            }
                            // else, Change the current player status
                            else {
                                // TODO: change the current player (not working yet)
                                try {
                                    InitializeManager initializeManager = new InitializeManager();
                                    initializeManager.switchPlayer(currentPlayer);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                // reset the timer / player view
                                String nextPlayer = currentPlayer.equals("Red") ? "Black" : "Red";
                                updatePlayerStatus(nextPlayer);
                                resetTimer(60);
                            }
                        }
                    }

                }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat the timer indefinitely
        timeline.play();
    }

    public void resetTimer(int seconds) {
        remainingSeconds = seconds;
        timerStatus.setText("Time remaining: " + remainingSeconds + " seconds");
        timeline.play(); // Start the timer again
    }

    public void displayWinner(String currentPlayer) {
        AnchorPane anchorPane = new AnchorPane();

        Text text = new Text("Winner is " + currentPlayer);
        text.setFont(Font.font("Arial", 24));

        AnchorPane.setTopAnchor(text, 50.0);
        AnchorPane.setLeftAnchor(text, 50.0);

        anchorPane.getChildren().add(text);

        Stage primaryStage = new Stage();
        Scene scene = new Scene(anchorPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
