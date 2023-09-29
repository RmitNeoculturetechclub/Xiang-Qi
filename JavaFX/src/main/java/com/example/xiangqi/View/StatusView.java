package com.example.xiangqi.View;

import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Enums.Constant.PointConstant;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // Determine the winner
        String winnerPlayer = "";
        if (PointConstant.BLACK > PointConstant.RED) {
            winnerPlayer = "Black";
        } else if (PointConstant.BLACK < PointConstant.RED) {
            winnerPlayer = "Red";
        } else {
            winnerPlayer = "Tie";
        }

        // Stage, Pane setup
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Game Over");

        // Winner Display
        Text winnerText = new Text("Game Over\nWinner is " + winnerPlayer);
        winnerText.setFont(Font.font("Arial", 24));
        winnerText.setTextAlignment(TextAlignment.CENTER);

        // Total points Display
        Label blackLabel = new Label("Black Total Point: " + PointConstant.BLACK);
        Label redLabel = new Label("Red Total Point: " + PointConstant.RED);
        blackLabel.setFont(Font.font("Arial", 20));
        redLabel.setFont(Font.font("Arial", 20));
        HBox totalPointBox = new HBox(20); // 20 is the spacing between elements
        totalPointBox.setAlignment(Pos.CENTER);
        totalPointBox.getChildren().addAll(blackLabel, redLabel);

        // Create tables for black and red players
        TableView<PointsData> blackTable = createTable(PointConstant.blackPointMap, "Black");
        TableView<PointsData> redTable = createTable(PointConstant.redPointMap, "Red");
        HBox tableBox = new HBox(20); // 20 is the spacing between elements
        tableBox.setAlignment(Pos.CENTER);
        tableBox.getChildren().addAll(blackTable, redTable);

        // End the game with the 'Exit' button
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });

        // Create a VBox to organize the elements
        VBox vbox = new VBox(20); // 20 is the spacing between elements
        vbox.setAlignment(Pos.CENTER);

        // Add all the elements to the VBox
        vbox.getChildren().addAll(winnerText, totalPointBox, tableBox, exitButton);

        // Create the scene using the VBox
        Scene scene = new Scene(vbox, 600, 500);
        popupStage.setScene(scene);
        popupStage.show();

    }

    private TableView<PointsData> createTable(HashMap<String, Double> pointMap, String player) {
        TableView<PointsData> table = new TableView<>();
        TableColumn<PointsData, String> pieceNameColumn = new TableColumn<>("Piece Name");
        TableColumn<PointsData, Double> pointsColumn = new TableColumn<>("Points");

        pieceNameColumn.setCellValueFactory(cellData -> {
            PointsData rowData = cellData.getValue();
            return new SimpleStringProperty(rowData.getPieceName());
        });

        pointsColumn.setCellValueFactory(cellData -> {
            PointsData rowData = cellData.getValue();
            return new SimpleDoubleProperty(rowData.getPoints()).asObject();
        });

        List<TableColumn<PointsData, ?>> columns = new ArrayList<>();
        columns.add(pieceNameColumn);
        columns.add(pointsColumn);

        table.getColumns().addAll(columns);

        for (Map.Entry<String, Double> entry : pointMap.entrySet()) {
            table.getItems().add(new PointsData(entry.getKey(), entry.getValue(), player));
        }

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        return table;
    }

    // Class to represent data in the table
    public static class PointsData {
        private final SimpleStringProperty pieceName;
        private final SimpleDoubleProperty points;
        private final SimpleStringProperty player;

        public PointsData(String pieceName, Double points, String player) {
            this.pieceName = new SimpleStringProperty(pieceName);
            this.points = new SimpleDoubleProperty(points);
            this.player = new SimpleStringProperty(player);
        }

        public String getPieceName() {
            return pieceName.get();
        }

        public Double getPoints() {
            return points.get();
        }

        public String getPlayer() {
            return player.get();
        }
    }
}
