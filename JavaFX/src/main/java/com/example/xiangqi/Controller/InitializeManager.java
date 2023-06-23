package com.example.xiangqi.Controller;

import com.example.xiangqi.Enums.Constant.InitPieceSetup;
import com.example.xiangqi.Enums.Model.PieceName;
import com.example.xiangqi.Enums.Model.Player;
import com.example.xiangqi.Handler.StringConversion;
import com.example.xiangqi.Model.*;
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
import com.example.xiangqi.Handler.IdGeneration;

public class InitializeManager {
    private InitializeView initializeView;
    private Cell[][] board;
    private AnchorPane pane;
    private String currentClickedPiece;

    public InitializeManager() throws IOException {
        initializeView = new InitializeView();
    }

    public Scene init() throws IOException {

        URL url = InitializeManager.class.getResource("/com/example/xiangqi/Board.fxml");
        assert url != null;
        pane = FXMLLoader.load(url);

        this.initializeBoard();
        this.initializePieceImageView();

        HBox hBox = new HBox();

        hBox.getChildren().add(pane);
        // adding scroll pane to the scene
        // this is bad practice to leave the scene in this. To be placed in the Game
        // Manager
        Scene scene = new Scene(hBox);
        return scene;
    }

    private void initializeBoard() {
        board = new Cell[InitPieceSetup.XiangQiBoard.length][InitPieceSetup.XiangQiBoard[0].length];
        for (int row = 0; row < InitPieceSetup.XiangQiBoard.length; row++) {
            for (int col = 0; col < InitPieceSetup.XiangQiBoard[row].length; col++) {
                Cell cell = new Cell(row, col);
                String pieceName = InitPieceSetup.XiangQiBoard[row][col];

                if (!pieceName.equals("")) {
                    // Extract player and piece type from the pieceName
                    String[] nameParts = pieceName.split("_");
                    String pieceType = nameParts[0];
                    String player = nameParts[1];

                    if (pieceType.equals("General")) {
                        General general = new General("", player, "Chariot");
                        general.setNumPieces(1);
                        general.setId(IdGeneration.generate(general.getId(), general.getNumPiece()));
                        cell.setPiece(general);
                    } else if (pieceType.equals("Chariot")) {
                        Chariot chariot = new Chariot("", player, "Chariot");
                        chariot.setNumPieces(1);
                        chariot.setId(IdGeneration.generate(chariot.getId(), chariot.getNumPiece()));
                        cell.setPiece(chariot);
                    } else if (pieceType.equals("Horse")) {
                        Horse horse = new Horse("", player, "Horse");
                        horse.setNumPieces(1);
                        horse.setId(IdGeneration.generate(horse.getId(), horse.getNumPiece()));
                        cell.setPiece(horse);
                    } else if (pieceType.equals("Elephant")) {
                        Elephant elephant = new Elephant("", player, "Elephant");
                        elephant.setNumPieces(1);
                        elephant.setId(IdGeneration.generate(elephant.getId(), elephant.getNumPiece()));
                        cell.setPiece(elephant);
                    } else if (pieceType.equals("Advisor")) {
                        Advisor advisor = new Advisor("", player, "Advisor");
                        advisor.setNumPieces(1);
                        advisor.setId(IdGeneration.generate(advisor.getId(), advisor.getNumPiece()));
                        cell.setPiece(advisor);
                    } else if (pieceType.equals("Soldier")) {
                        Soldier soldier = new Soldier("", player, "Soldier");
                        soldier.setNumPieces(1);
                        soldier.setId(IdGeneration.generate(soldier.getId(), soldier.getNumPiece()));
                        cell.setPiece(soldier);
                    } else if (pieceType.equals("Canon")) {
                        Canon canon = new Canon("", player, "Canon");
                        canon.setNumPieces(1);
                        canon.setId(IdGeneration.generate(canon.getId(), canon.getNumPiece()));
                        cell.setPiece(canon);
                    }
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
                     * Set on mouse click to check the piece and draw the rectangle cell
                     * Todo: 1. Create function movement in Piece -> return all possible moves
                     * Todo: 2. When rectangle clicked, then remove cell of currentClickedPiece to
                     * None.
                     * Todo: 3. Algorithm to find possible cells that currentClickedPiece can go
                     * 
                     */
                    pieceImageView.setOnMouseClicked(e -> {

                        cell.getAllPossibleCells(this.board);
                        // Happy case first, click once
                        // Sad case: Check if the current clicked piece belongs to current player
                        // Sad case: Check if current clicked is the last piece, if not then remove all
                        // previous rectangle
                    });

                    cell.drawPieceImageView(pieceImageView);
                    this.pane.getChildren().add(cell.getImageView());
                }
            }
        }
    }
}