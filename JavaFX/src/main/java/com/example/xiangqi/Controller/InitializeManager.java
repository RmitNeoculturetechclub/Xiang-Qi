package com.example.xiangqi.Controller;

import com.example.xiangqi.Enums.Constant.InitPieceSetup;
import com.example.xiangqi.Enums.Constant.PointConstant;
import com.example.xiangqi.Enums.Model.Player;
import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Model.General;
import com.example.xiangqi.Model.Piece;
import com.example.xiangqi.View.InitializeView;
import com.example.xiangqi.View.StatusView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InitializeManager {
    private InitializeView initializeView;
    private Cell[][] board;
    private AnchorPane pane;
    private Piece currentClickedPiece;
    private ImageView imageView;
    private StatusView statusView;
    private Piece piece;
    private String currentPlayer;
    private Player player;
    private List<Pair<String, Circle>> previousGeneralCircles;

    private ArrayList<Circle> displayCircles;

    public InitializeManager() throws IOException {
        initializeView = new InitializeView();
        currentPlayer = "Red";
    }

    public Scene init(double widthStage, double heightStage) throws IOException {
        URL url = InitializeManager.class.getResource("/com/example/xiangqi/Board.fxml");
        assert url != null;
        pane = FXMLLoader.load(url);
        displayCircles = new ArrayList<>();
        initializeView = new InitializeView();
        previousGeneralCircles = new ArrayList<>();

        this.initializeBoard();
        return this.initializeScene(widthStage, heightStage);
    }

    private Scene initializeScene(double widthStage, double heightStage) {
        statusView = new StatusView();
        statusView.updateBoard(this.board);
        statusView.updatePlayerStatus(currentPlayer);
        statusView.updatePointStatus(0.0, 0.0);

        pane.setPrefSize(widthStage / 3, heightStage);
        HBox hBox = new HBox();
        hBox.setPrefSize(widthStage, heightStage);
        hBox.getChildren().add(pane);
        hBox.getChildren().add(statusView.getPane());
        return new Scene(hBox);
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

                    try {
                        // Initialize Dynamic Class name
                        Class<?> class1 = Class.forName("com.example.xiangqi.Model." + pieceType);
                        Piece object1 = (Piece) class1.getDeclaredConstructor().newInstance();
                        object1.setPlayer(player);
                        cell.setPiece(object1);

                        imageViewSetOnMouseClicked(cell);
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException
                            | InvocationTargetException | InstantiationException exception) {
                        System.out.println("Exception: " + exception);
                    }
                }

                board[row][col] = cell;
            }
        }

    }

    public void switchPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer.equals("Red") ? "Black" : "Red";
    }

    private void imageViewSetOnMouseClicked(Cell cell) {
        ImageView pieceImageView;
        CheckGeneral IsCheckGeneral = new CheckGeneral();

        try {
            pieceImageView = this.initializeView.createPieceView(
                    String.format("/com/example/xiangqi/pictures/%s.png", cell.getPiece().getPieceImageName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Click on a piece to move
        pieceImageView.setOnMouseClicked(e -> {
            // only if the piece is not null && the current player
            if (cell.getPiece() != null && cell.getPiece().getPlayerName().equals(currentPlayer)) {
                pane.getChildren().removeAll(displayCircles);
                displayCircles.clear();
                currentClickedPiece = cell.getPiece();

                List<int[]> possibleCells = cell.getAllPossibleCells(this.board);

                // check if the current player's general is in checked
                General general = (General) IsCheckGeneral.findGeneral(currentPlayer, this.board).getPiece();
                if (general != null) {
                    // if checked, regenerate the possible cells
                    BoardManager boardManager = new BoardManager();
                    possibleCells = boardManager.regeneratePossibleCells(possibleCells, general,
                            currentPlayer, board,
                            cell);
                }

                for (int[] positions : possibleCells) {
                    int positionX = positions[1];
                    int positionY = positions[0];
                    Circle circlePossible = this.initializeView.createCirclePossibleCell(positionX, positionY,
                            currentPlayer);

                    // Made a decision by clicking on a possible movement
                    circlePossible.setOnMouseClicked(event -> {

                        // Get the new cell based on the clicked rectangle's position
                        Cell newCell = board[positionY][positionX];

                        // add points
                        PointConstant pointConstant = new PointConstant();
                        pointConstant.addPoint(newCell, cell, currentPlayer);

                        // Handle the case where it's general
                        if (newCell.getPiece() != null && newCell.getPiece().getPieceName().equals("General")) {
                            StatusView winnerDisplay = new StatusView();
                            winnerDisplay.displayWinner();
                        }

                        // remove images on both cells
                        pane.getChildren().remove(newCell.getImageView());
                        pane.getChildren().remove(cell.getImageView());

                        // Set the current clicked piece to the new cell
                        newCell.setPiece(currentClickedPiece);

                        // remove all the rectangles
                        pane.getChildren().removeAll(displayCircles);
                        displayCircles.clear();

                        // reset the global clicked piece
                        this.currentClickedPiece = null;

                        // Clear the old piece from the old cell
                        cell.setPiece(null);

                        // Set the image view (current piece) on the new cell
                        imageViewSetOnMouseClicked(newCell);

                        // check both general isUnderThreat
                        IsCheckGeneral.isUnderThreat(board, this.pane, this.previousGeneralCircles);

                        // switch the current player
                        switchPlayer(currentPlayer);
                        statusView.updateBoard(this.board);
                        statusView.updatePlayerStatus(currentPlayer);
                        statusView.updatePointStatus(PointConstant.BLACK, PointConstant.RED);

                    });

                    this.pane.getChildren().add(circlePossible);
                    this.displayCircles.add(circlePossible);
                }
            }
        });

        cell.drawPieceImageView(pieceImageView);
        this.pane.getChildren().add(cell.getImageView());

    }
}