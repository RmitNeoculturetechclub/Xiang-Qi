package com.example.xiangqi.Model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        this.position = new int[]{row, col};
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

    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Apply position to set X and Y
     * Note, if the y is more than 7, then set the height with +- constant number
     * Rectangle on clicked then
     * Todo: 4. For each cell create draw function to draw rectangle on listen mouse clicked.
     *          If there is a cell and player enemy then remove image view, and set piece isAlive to False, set Piece to the currentClickedPiece
     */
    public void drawRectangle(Cell clickedCell){}

    public void drawPieceImageView(ImageView pieceImageView){
        pieceImageView.setX(50);
        pieceImageView.setY(508.0);
        this.imageView = pieceImageView;
    }

    public Piece getPiece () {
        return piece;
    }

    public int[] getPosition () {
        return position;
    }

    public void setPosition (int[] position) {
        this.position = position;
    }

    public void getAllPossibleCells(Cell[][] GlobalBoard){
        /*
        Change the list name
        Change the new int to find all possible positions
         */
        List<int[]> possiblePositions = this.piece.getAllPossibleMoves(GlobalBoard);

        for (int[] positions : possiblePositions){
            // Get cell
            int row = positions[0];
            int col = positions[1];
            Cell cell = GlobalBoard[row][col];

            // draw rectangle
            cell.drawRectangle(this);
        }
    }
}
