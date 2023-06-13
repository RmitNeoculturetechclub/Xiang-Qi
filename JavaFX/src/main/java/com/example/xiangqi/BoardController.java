package com.example.xiangqi;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
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
		//rec.setFill(Color.YELLOW);
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

	public void soldierMove(MouseEvent mouseEvent) {
		ImageView tmp = (ImageView) mouseEvent.getSource();
		String currentPiece = tmp.getId();

		// Check if the clicked piece is a soldier
		if (currentPiece.matches("soldier_[br]\\d+")) {
			System.out.println("IT IS A SOLDIER");
			// Get the X and Y coordinates of the soldier
			double soldierX = tmp.getX();
			double soldierY = tmp.getY();
			System.out.println("x, y" + soldierX + soldierY);

			// Reset all rectangles
			clearRectangles();

			// Check if the soldier has crossed the river
			boolean crossedRiver = (currentPiece.startsWith("soldier_b") && soldierY <= 250)
					|| (currentPiece.startsWith("soldier_r") && soldierY > 250);

			// Create rectangles for possible soldier moves
			if (crossedRiver) {
				System.out.println("CROSSED");
				createRectangle(soldierX, soldierY + 50); // move forward
				createRectangle(soldierX - 50, soldierY); // move left
				createRectangle(soldierX + 50, soldierY); // move right
				createRectangle(soldierX, soldierY - 50); // move backward
			} else {
				System.out.println("NOT CROSSED");
				if (currentPiece.startsWith("soldier_b")) {
					createRectangle(soldierX, soldierY + 50); // move forward
				} else {
					createRectangle(soldierX, soldierY - 50); // move forward
				}
			}

			// Set onMouseClicked event handler for the soldier
			tmp.setOnMouseClicked(e -> {
				Rectangle clickedRect = findClickedRectangle(e.getX(), e.getY());
				if (clickedRect != null) {
					// Move the soldier to the clicked rectangle's position
					tmp.relocate(clickedRect.getX(), clickedRect.getY());

					// Remove the clicked rectangle from the board
					board.getChildren().remove(clickedRect);
				}
			});
		} else {
			// Reset all rectangles if the clicked piece is not a soldier
			clearRectangles();
			System.out.println("NOT A SOLDIER");
		}
	}



	private void clearRectangles() {
		List<Node> toRemove = new ArrayList<>();
		for (Node node : board.getChildren()) {
			if (node instanceof Rectangle && Objects.equals(node.getId(), "move")) {
				toRemove.add(node);
			}
		}
		board.getChildren().removeAll(toRemove);
	}

	private void createRectangle(double x, double y) {
		Rectangle rec = new Rectangle();
		rec.setId("move");
		rec.setX(x);
		rec.setY(y);
		rec.setFill(Color.YELLOW);
		rec.setOpacity(0.5);
		rec.setWidth(50);
		rec.setHeight(50);
		board.getChildren().add(rec);
	}

	private Rectangle findClickedRectangle(double x, double y) {
		for (Node node : board.getChildren()) {
			if (node instanceof Rectangle && Objects.equals(node.getId(), "move")) {
				Rectangle rect = (Rectangle) node;
				if (rect.contains(x, y)) {
					return rect;
				}
			}
		}
		return null;
	}


	public void advisorMove (MouseEvent mouseEvent) {
	}

	public void setPosition (double x, double y, ImageView piece) {
		piece.setX(x);
		piece.setY(y);
	}
}