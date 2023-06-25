package com.example.xiangqi.Model;

import com.example.xiangqi.Enums.Constant.CellConstant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Cell {

  private ImageView imageView;
  private Piece piece;

  // Position: x and y
  // Example: {0,0}
  // Example: {6,9}
  private int[] position;

  public Cell(int row, int col) {
    this.imageView = null;
    this.piece = null;
    this.position = new int[] { row, col };
  }

  public int[] getPosition() {
    return position;
  }

  public Cell(ImageView imageView, Piece piece) {
    this.imageView = imageView;
    this.piece = piece;
  }

  public void setImageView(ImageView imageView) {
    this.imageView = imageView;
  }

  public void setPiece(Piece piece) {
    this.piece = piece;
  }

  public Piece getOccupiedPiece() {
    return piece;
  }

  public Piece getPiece() {
    return piece;
  }

  public ImageView getImageView() {
    return imageView;
  }

  public boolean isEnemy(Piece piece) {
    if (this.piece.player != piece.player) {
      return true; // if it's an enemy
    }
    return false; // if it's the same side
  }

  public void removeImageView() {
    this.imageView = null;
  }


  public void drawRectangle(Cell clickedCell) {
    // clickedCell => the user's current position, this object => the next position (from possible moves)

    // Apply position to set X and Y
    int x = clickedCell.position[0];
    int y = clickedCell.position[1];

    // if y is more than 7
    if (y > 7) {
      // Set the height with +- constant number
      clickedCell.position[1] += CellConstant.CELL_SIZE;
    }
    
    // TODO: draw a rectangle

    // if they are the opposite side (enemy)
    if (isEnemy(clickedCell.getPiece())) {
      clickedCell.removeImageView(); // remove the image view
      clickedCell.getPiece().setAlive(false); //set isAlive to False
      // Set the piece of clickedCell to the cell of the current object
      clickedCell.setPiece(this.getPiece());
    }
  }

  public void drawPieceImageView(ImageView pieceImageView) {
    int cellX =
      (CellConstant.TOTAL_COL - this.position[1]) * CellConstant.CELL_SIZE; // Calculate the X coordinate (row)
    int cellY =
      (CellConstant.ROW_STARTING_INDEX + this.position[0]) *
      CellConstant.CELL_SIZE; // Calculate the Y coordinate (col)

    pieceImageView.setX(cellX);
    pieceImageView.setY(cellY);
    this.imageView = pieceImageView;
  }

  public void getAllPossibleCells(Cell[][] GlobalBoard) {
    /*
        Change the list name
        Change the new int to find all possible positions
         */
    List<int[]> possiblePositions = this.piece.getAllPossibleMoves(GlobalBoard);

    for (int[] positions : possiblePositions) {
      // Get cell
      int row = positions[0];
      int col = positions[1];
      Cell cell = GlobalBoard[row][col];

      // draw rectangle
      cell.drawRectangle(this);
    }
  }
}
