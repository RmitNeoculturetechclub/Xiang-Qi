package com.example.xiangqi.View;

import com.example.xiangqi.Controller.InitializeManager;
import com.example.xiangqi.Enums.Constant.CellConstant;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;

public class InitializeView {

    public InitializeView() {
    }

    public static Image createImage(String resourceName) {
        System.out.println(resourceName);
        URL _url = InitializeManager.class.getResource(resourceName);
        assert _url != null;
        return new Image(_url.toExternalForm());
    }

    public ImageView createPieceView(String imageLink) throws IOException {
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


    /**
     * Apply position to set X and Y
     * Note, if the y is more than 7, then set the height with +- constant number
     * Rectangle on clicked then
     * Todo: For each cell create draw function to draw rectangle on listen mouse clicked.
     *          If there is a cell and player enemy then remove image view, and set piece isAlive to False, set Piece to the currentClickedPiece
     */
    public Rectangle createRectanglePossibleCell(int positionX, int positionY){
        Rectangle rec = new Rectangle();
        // draw rectangle with position X and Y

        return rec;
    }
}
