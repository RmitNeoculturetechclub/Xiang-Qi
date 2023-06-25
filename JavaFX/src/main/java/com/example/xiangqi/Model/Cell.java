package com.example.xiangqi.Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.example.xiangqi.Global.Constant.CellConstant;

import java.util.ArrayList;
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

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
    public ImageView getImageView() {
        return imageView;
    }

    public void drawPieceImageView(ImageView pieceImageView){
        
        int cellX = (CellConstant.TOTAL_COL - this.position[1]) * CellConstant.CELL_SIZE; // Calculate the X coordinate (row)
        int cellY = (CellConstant.ROW_STARTING_INDEX + this.position[0]) * CellConstant.CELL_SIZE; // Calculate the Y coordinate (col)

        pieceImageView.setX(cellX);
        pieceImageView.setY(cellY);

        this.setImageView(pieceImageView);
    }

    public List<int[]> getAllPossibleCells(Cell[][] board){
        /*
        Change the list name
        Change the new int to find all possible positions
         */

//        List<int[]> possiblePositions = this.piece.getAllPossibleMoves(board);

        List<int[]> example = new ArrayList<>();
        example.add(new int[]{1, 2});
        example.add(new int[]{5, 5});
        example.add(new int[]{6, 6});
        example.add(new int[]{7, 7});
        example.add(new int[]{4, 7});

        return example;
    }
}