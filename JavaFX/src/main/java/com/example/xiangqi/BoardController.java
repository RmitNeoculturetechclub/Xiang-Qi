package com.example.xiangqi;

import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Model.Piece;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Objects;

import static com.example.xiangqi.Enums.Constant.InitPieceSetup.XiangQiBoard;

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
	}

	public void generalMove (MouseEvent mouseEvent) {
	}

	public void horseMove (MouseEvent mouseEvent) {
	}

	public void elephantMove (MouseEvent mouseEvent) {
	}

	public void canonMove (MouseEvent mouseEvent) {
		ImageView tmp = (ImageView) mouseEvent.getSource();
//		int[] currentPos = {(int) (tmp.getLayoutX() / 50), (int) ((508 - tmp.getLayoutY()) / 50)};
		Piece canon;
		if (Objects.equals(tmp.getId(), "canon_b1") || Objects.equals(tmp.getId(), "canon_b2")) {

			// Need updates
			canon = new Piece("0", "White", "Canon_Black");
		} else {
			canon = new Piece("0", "White", "Canon_Red");
		}

		List <int[]> possiblePositions = canon.getAllPossibleMoves(this.getBoard(tmp));
		// Remove the previous canon moves.
		board.getChildren().removeIf(node -> node instanceof Rectangle);

		// Create the canon moves.
		for (int[] possiblePosition : possiblePositions) {
			Rectangle rec = new Rectangle();
			rec.setX(possiblePosition[0] * 50);
			rec.setY(508 - 50 * possiblePosition[1]);
			rec.setFill(Color.YELLOW);
			rec.setOpacity(0.5);
			rec.setWidth(50);
			rec.setHeight(50);
			board.getChildren().add(rec);
			rec.setOnMouseClicked(e -> {
				tmp.relocate(rec.getX(), rec.getY());
				// Remove the previous canon moves.
				board.getChildren().removeIf(node -> node instanceof Rectangle);
			});
		}
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

	public Cell[][] getBoard (ImageView images) {
		Cell[][] Cell = new Cell[10][9];
		for (int i = 0; i < XiangQiBoard.length; i++) {
			for (int j = 0; j < XiangQiBoard[i].length; j++) {
				if (! XiangQiBoard[i][j].isEmpty()) {
					Piece piece = new Piece(i + j + " ", "White", XiangQiBoard[i][j]);
					Cell[i][j] = new Cell(i, j);
					Cell[i][j].setPiece(piece);
					Cell[i][j].setImageView(images);
				}
			}
		}
		return Cell;
	}
}
