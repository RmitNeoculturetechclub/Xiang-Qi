package com.example.xiangqi.View;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class StatusView {
    private final AnchorPane pane;
    private Label playerStatus;

    public StatusView() {
        this.pane = new AnchorPane();
        this.initializePane();
    }

    private void initializePane(){
        HBox hBox = new HBox();

        // Create a text field and set its text to "Current Player".
        playerStatus = new Label();
        playerStatus.setFont(new Font("Arial", 30));

        Label labelPlayerText = new Label();
        labelPlayerText.setText("Current Player: ");
        labelPlayerText.setFont(new Font("Arial", 30));
        hBox.getChildren().addAll(labelPlayerText, playerStatus);

        pane.getChildren().add(hBox);
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void updatePlayerStatus(String currentPlayer){
        playerStatus.setText(currentPlayer);
    }
}