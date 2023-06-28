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

import java.io.Console;
import java.io.IOException;
import java.net.URL;

// managing the graphical representation of the XiangQi game board and its pieces
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

        // creating ImageView for adding image
        ImageView imageView = new ImageView();
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

    public Rectangle createRectanglePossibleCell(int positionX, int positionY) {
        Rectangle rec = new Rectangle();

        System.out.println("X, Y" + positionX + positionY);

        // Apply position to set X and Y
        rec.setX((CellConstant.TOTAL_COL - positionX) * CellConstant.CELL_SIZE);
        rec.setY((CellConstant.ROW_STARTING_INDEX + positionY) * CellConstant.CELL_SIZE);

        // Set size
        rec.setWidth(CellConstant.CELL_SIZE);
        rec.setHeight(CellConstant.CELL_SIZE);

        // Set other properties
        rec.setFill(Color.BLUE); // dif side dif col?
        rec.setStroke(Color.BLUE);
        rec.setStrokeWidth(2);
        rec.setOpacity(0.5);

        return rec;
    }

}
