package com.example.xiangqi;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class BoardController {
	public ImageView general_r;
	public ImageView general_b;
	public ImageView chariot_b2;
	public ImageView chariot_b1;
	public ImageView horse_b1;
	public ImageView horse_b2;
	public ImageView elephant_b1;
	public ImageView elephant_b2;
	public ImageView canon_r1;
	public ImageView canon_r2;
	public ImageView canon_b1;
	public ImageView canon_b2;
	public ImageView soldier_b1;
	public ImageView soldier_b3;
	public ImageView soldier_b2;
	public ImageView soldier_b4;
	public ImageView soldier_b5;
	public ImageView soldier_r1;
	public ImageView soldier_r2;
	public ImageView soldier_r3;
	public ImageView soldier_r4;
	public ImageView soldier_r5;
	public ImageView advisor_b1;
	public ImageView advisor_b2;
	public ImageView horse_r1;
	public ImageView horse_r2;
	public ImageView elephant_r1;
	public ImageView elephant_r2;
	public ImageView advisor_r1;
	public ImageView advisor_r2;
	public ImageView chariot_r1;
	public ImageView chariot_r2;
	public AnchorPane board;

	public String current_clicked_piece;

	public void chariotMove (MouseEvent mouseEvent) {
		ImageView tmp = (ImageView) mouseEvent.getSource();
		Rectangle rec = new Rectangle();
		rec.setId("move");
		rec.setX(0);
		rec.setY(458);
		rec.setFill(Color.YELLOW);
		rec.setOpacity(0.5);
		rec.setWidth(50);
		rec.setHeight(50);
		board.getChildren().add(rec);
		rec.setOnMouseClicked((e) -> {
			tmp.relocate(rec.getX(), rec.getY());
			for (Node node : board.getChildren()) {
				if (Objects.equals(node.getId(), rec.getId())) {
					board.getChildren().remove(node);
					break;
				}
			}
		});
	}

	public void generalMove (MouseEvent mouseEvent) {
	}

	public void horseMove (MouseEvent mouseEvent) {
	}

	public void elephantMove (MouseEvent mouseEvent) {
	}

	public void canonMove (MouseEvent mouseEvent) {
	}

	public void soliderMove (MouseEvent mouseEvent) {
		// get X and Y of the soldier
		// create all rectangles that soldier can go
		// ignore the other pieces
		// set on mouse clicked for soldier
		ImageView tmp = (ImageView) mouseEvent.getSource();

		// Check if the current click is that piece, if not reset all rectangles
		// And create another rectangles with statement above
		this.current_clicked_piece = tmp.getId();

	}

	public void advisorMove (MouseEvent mouseEvent) {
	}

	public void setPosition (double x, double y, ImageView piece) {
		piece.setX(x);
		piece.setY(y);
	}
}
