package com.example.xiangqi.View;

import com.example.xiangqi.Controller.InitializeManager;
import com.example.xiangqi.Enums.Constant.CellConstant;
import com.example.xiangqi.Enums.Constant.PlayerConstant;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

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

    public Circle createCirclePossibleCell(int positionX, int positionY, String currentPlayer) {
        Circle circle = new Circle();

        // Apply position to set center coordinates
        circle.setCenterX((CellConstant.TOTAL_COL - positionX) * CellConstant.CELL_SIZE + CellConstant.CELL_SIZE / 2);
        circle.setCenterY(
                (CellConstant.ROW_STARTING_INDEX + positionY) * CellConstant.CELL_SIZE + CellConstant.CELL_SIZE / 2);

        // Set radius
        circle.setRadius(CellConstant.CELL_SIZE / 2);

        // Set other properties
        circle.setFill(PlayerConstant.PlayerConstant.get(currentPlayer));
        circle.setStroke(PlayerConstant.PlayerConstant.get(currentPlayer));

        circle.setStrokeWidth(0.4);
        circle.setOpacity(0.5);

        return circle;
    }

    public Circle createGeneralColor(int positionX, int positionY, Color color) {
        Circle circle = new Circle();

        // Apply position to set center coordinates
        circle.setCenterX((CellConstant.TOTAL_COL - positionX) * CellConstant.CELL_SIZE + CellConstant.CELL_SIZE / 2);
        circle.setCenterY(
                (CellConstant.ROW_STARTING_INDEX + positionY) * CellConstant.CELL_SIZE + CellConstant.CELL_SIZE / 2);

        // Set radius
        circle.setRadius(CellConstant.CELL_SIZE / 2);

        // Set the specified color
        circle.setFill(color);
        circle.setStroke(color);

        circle.setStrokeWidth(0.4);
        circle.setOpacity(0.8);

        return circle;
    }
}
