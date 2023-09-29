package com.example.xiangqi.View;

import com.example.xiangqi.Controller.CheckGeneral;
import com.example.xiangqi.Controller.InitializeManager;
import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Model.General;
import com.example.xiangqi.Enums.Constant.PointConstant;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
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
    private Label redPointStatus;
    private Label blackPointStatus;
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

        // Red Points status
        redPointStatus = new Label();
        redPointStatus.setFont(new Font("Arial", 30));

        // Black Points status
        blackPointStatus = new Label();
        blackPointStatus.setFont(new Font("Arial", 30));

        VBox vBox = new VBox(); // for two vertical rows
        vBox.getChildren().addAll(playerBox, timerStatus, redPointStatus, blackPointStatus);
        pane.getChildren().add(vBox);
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void updatePlayerStatus(String currentPlayer) {
        playerStatus.setText(currentPlayer);
    }

    public void updatePointStatus(Double blackPoint, Double redPoint) {
        redPointStatus.setText("Red points: " + redPoint);
        blackPointStatus.setText("Black points: " + blackPoint);
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
                        displayWinner();
                    }

                }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat the timer indefinitely
        timeline.play();
    }

    public void displayWinner() {
        String winnerPlayer = "";
        if (PointConstant.BLACK > PointConstant.RED) {
            winnerPlayer = "Black";
        } else if (PointConstant.BLACK < PointConstant.RED) {
            winnerPlayer = "Red";
        } else {
            winnerPlayer = "Tie";
        }

        AnchorPane anchorPane = new AnchorPane();

        Text text = new Text("Winner is " + winnerPlayer);
        text.setFont(Font.font("Arial", 24));

        AnchorPane.setTopAnchor(text, 50.0);
        AnchorPane.setLeftAnchor(text, 50.0);

        anchorPane.getChildren().add(text);

        Stage primaryStage = new Stage();
        Scene scene = new Scene(anchorPane, 700, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        // TODO: end the game
    }
}
