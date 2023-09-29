package com.example.xiangqi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.xiangqi.Controller.InitializeManager;

public class Game extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		InitializeManager initializeManager = new InitializeManager();
		Scene scene = initializeManager.init(stage.getWidth(), stage.getHeight());
		stage.setTitle("XiangQi");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch();
	}
}