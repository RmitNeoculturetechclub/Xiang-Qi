package com.example.xiangqi.View;

import com.example.xiangqi.Controller.InitializeManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class InitializeView {

    public InitializeView() {
    }

    public static Image createImage(String resourceName) {
        URL _url = InitializeManager.class.getResource(resourceName);
        assert _url != null;
        return new Image(_url.toExternalForm());
    }

    public ImageView createImageView(String imageLink) throws IOException {


        Image image = createImage(imageLink);

        //creating ImageView for adding image
        ImageView imageView=new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        // set layout X and Y
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        return imageView;
    }
}
