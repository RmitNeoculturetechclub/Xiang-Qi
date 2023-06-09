package com.example.xiangqi.Controller;

import com.example.xiangqi.Enums.Constant.InitPieceSetup;
import com.example.xiangqi.Enums.Model.PieceName;
import com.example.xiangqi.Enums.Model.Player;
import com.example.xiangqi.Handler.StringConversion;
import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Model.Piece;
import com.example.xiangqi.View.InitializeView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InitializeManager {
    private InitializeView initializeView;
    private Cell[][] board;
    private AnchorPane pane;
    private String currentClickedPiece;

    public InitializeManager() throws IOException {
        initializeView = new InitializeView();
        board = new Cell[8][9];
    }


    public Scene init() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(InitializeManager.class.getResource("/com/example/xiangqi/Board.fxml"));
        pane = (AnchorPane) fxmlLoader.getNamespace().get("board");

        this.initializeBoard();
        this.initializePieceImageView();

        HBox hBox =  new HBox();

        hBox.getChildren().add(pane);

        // adding scroll pane to the scene
        // this is bad practice to leave the scene in this. To be placed in the Game Manager
        Scene scene = new Scene(hBox);
        return scene;
    }

    private void initializeBoard() {
        for (int row = 0; row < InitPieceSetup.XiangQiBoard.length; row++){
            for (int col = 0; col < InitPieceSetup.XiangQiBoard[row].length; col++){
                Cell cell = new Cell(row, col);
                String pieceName = InitPieceSetup.XiangQiBoard[row][col];

                if(!pieceName.equals("")){
                    /*
                    Extract piece name to player and type
                    Todo for Lucia: extract the player and piece name from the pieceName and place to the initializer
                     */
                    Piece piece = new Piece("id", "", "");
                    cell.setPiece(piece);
                }

                board[row][col] = cell;
            }
        }
    }

    private void initializePieceImageView() {
        for (Cell[] cells : this.board) {
            for (Cell cell : cells) {
                if (cell.getOccupiedPiece() != null) {
                    ImageView pieceImageView;

                    try {
                        pieceImageView = this.initializeView.createImageView(
                                String.format("/pictures/%s.png", cell.getOccupiedPiece().getPieceImageName()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                /*
                Set on mouse click to check the piece and draw the rectangle cell
                Todo: 1. Create function movement in Piece -> return all possible moves
                Todo: 2. When rectangle clicked, then remove cell of currentClickedPiece to None.
                Todo: 3. Algorithm to find possible cells that currentClickedPiece can go

                 */
                    pieceImageView.setOnMouseClicked(e -> {

                        cell.getAllPossibleCells(this.board);
                        // Happy case first, click once
                        // Sad case: Check if the current clicked piece belongs to current player
                        // Sad case: Check if current clicked is the last piece, if not then remove all previous rectangle
                    });

                    /*
                    Todo for Lucia: Calculate the coordinate X and Y from the position in the cell.
                     */
                    cell.drawPieceImageView(pieceImageView);
                    this.pane.getChildren().add(cell.getImageView());
                }
            }
        }
    }
}
