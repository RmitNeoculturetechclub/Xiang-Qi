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
        URL _url = InitializeManager.class.getResource(resourceName);
        assert _url != null;
        return new Image(_url.toExternalForm());
    }

    // create an Image object based on the specified image link
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

    /*
     * Rectangle on clicked then
     * Todo: For each cell create draw function to draw rectangle on listen mouse
     * clicked.
     */

    // draw rectangle with position X and Y
    // create a rectangle for representing a possible cell that a piece can move to
    public Rectangle createRectanglePossibleCell(int positionX, int positionY) {
        Rectangle rec = new Rectangle();

        // Apply position to set X and Y
        rec.setX(positionX * CellConstant.CELL_SIZE);
        rec.setY(positionY * CellConstant.CELL_SIZE);

        // Set size
        rec.setWidth(CellConstant.CELL_SIZE);
        rec.setHeight(CellConstant.CELL_SIZE);

        // Adjust y if it is more than 7
        if (positionY > 7) {
            rec.setY(rec.getY() + CellConstant.CELL_SIZE);
        }

        // Set other properties
        rec.setFill(Color.BLUE);
        rec.setStroke(Color.BLUE);
        rec.setStrokeWidth(2);
        rec.setOpacity(0.5); // Set transparency to 50%

        return rec;
    }

}
