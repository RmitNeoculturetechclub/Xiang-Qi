package com.example.xiangqi.View;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayPlayer {
    public void displayText(String currentPlayer) {
        AnchorPane anchorPane = new AnchorPane();

        Text text = new Text("Current Player: " + currentPlayer);
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