package com.example.xiangqi;

import com.example.xiangqi.Controller.InitializeManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.xiangqi.View.DisplayPlayer;

public class Game extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		InitializeManager initializeManager = new InitializeManager();
		Scene scene = initializeManager.init();
		stage.setTitle("XiangQi");
		stage.setScene(scene);
		stage.show();

		// display the current player state on the UI view
		// DisplayPlayer currentPlayerDisplay = new DisplayPlayer();
		// currentPlayerDisplay.displayPlayer("Red");

	}

	public static void main(String[] args) {
		launch();
	}
}