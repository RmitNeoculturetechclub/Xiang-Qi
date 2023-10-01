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
        currentPlayer = Player.Red.toString();
        board = new Cell[InitPieceSetup.XiangQiBoard.length][InitPieceSetup.XiangQiBoard[0].length];
        displayCircles = new ArrayList<>();
        previousGeneralCircles = new ArrayList<>();
    }

    public Scene init(double widthStage, double heightStage) throws IOException {
        URL url = InitializeManager.class.getResource("/com/example/xiangqi/Board.fxml");
        assert url != null;
        pane = FXMLLoader.load(url);
        initializeBoard();
        statusView = new StatusView();
        statusView.updateBoard(board);
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

                if (!pieceName.isEmpty()) {
                    initializePiece(cell, pieceName);
                }
                board[row][col] = cell;
            }
        }
    }

    private void initializePiece(Cell cell, String pieceName) {
        String[] nameParts = pieceName.split("_");
        String pieceType = nameParts[0];
        String player = nameParts[1];

        try {
            Class<?> pieceClass = Class.forName("com.example.xiangqi.Model." + pieceType);
            Piece newPiece = (Piece) pieceClass.getDeclaredConstructor().newInstance();
            newPiece.setPlayer(player);
            cell.setPiece(newPiece);
            imageViewSetOnMouseClicked(cell);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException | InstantiationException exception) {
            System.err.println("Exception: " + exception);
        }
    }

    public void switchPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer.equals("Red") ? "Black" : "Red";
    }

    private void imageViewSetOnMouseClicked(Cell cell) {
        ImageView pieceImageView = createPieceImageView(cell);
        pieceImageView.setOnMouseClicked(event -> handlePieceImageViewClick(cell, currentPlayer));
        cell.drawPieceImageView(pieceImageView);
        this.pane.getChildren().add(cell.getImageView());
    }

    private ImageView createPieceImageView(Cell cell) {
        try {
            return this.initializeView.createPieceView(
                    String.format("/com/example/xiangqi/pictures/%s.png", cell.getPiece().getPieceImageName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handlePieceImageViewClick(Cell cell, String currentPlayer) {
        if (cell.getPiece() != null && cell.getPiece().getPlayerName() == currentPlayer) {
            clearDisplayCircles();
            currentClickedPiece = cell.getPiece();
            List<int[]> possibleCells = cell.getAllPossibleCells(this.board);
            CheckGeneral IsCheckGeneral = new CheckGeneral();

            General general = (General) IsCheckGeneral.findGeneral(currentPlayer, this.board).getPiece();
            if (general != null) {
                BoardManager boardManager = new BoardManager();
                possibleCells = boardManager.regeneratePossibleCells(possibleCells, general, currentPlayer, board,
                        cell);
            }

            for (int[] positions : possibleCells) {
                int positionX = positions[1];
                int positionY = positions[0];
                Circle circlePossible = this.initializeView.createCirclePossibleCell(positionX, positionY,
                        currentPlayer);

                circlePossible.setOnMouseClicked(event -> handlePossibleCellClick(positionX, positionY, cell));
                this.pane.getChildren().add(circlePossible);
                this.displayCircles.add(circlePossible);
            }
        }
    }

    private void handlePossibleCellClick(int positionX, int positionY, Cell cell) {
        Cell newCell = board[positionY][positionX];
        PointConstant pointConstant = new PointConstant();
        CheckGeneral checkGeneral = new CheckGeneral();
        pointConstant.addPoint(newCell, cell, currentPlayer);

        // Handle if the general got occupied
        if (newCell.getPiece() != null && newCell.getPiece().getPieceName().equals("General")) {
            new StatusView().displayWinner();
        }

        // Remove images on both cells
        pane.getChildren().remove(newCell.getImageView());
        pane.getChildren().remove(cell.getImageView());

        // Set the current clicked piece to the new cell
        newCell.setPiece(currentClickedPiece);

        clearDisplayCircles();
        currentClickedPiece = null;
        cell.setPiece(null);
        imageViewSetOnMouseClicked(newCell);

        checkGeneral.isUnderThreat(board, this.pane, this.previousGeneralCircles);
        switchPlayer(currentPlayer);
        statusView.updateBoard(this.board);
        statusView.updatePlayerStatus(currentPlayer);
        statusView.updatePointStatus(PointConstant.BLACK, PointConstant.RED);
    }

    private void clearDisplayCircles() {
        pane.getChildren().removeAll(displayCircles);
        displayCircles.clear();
    }

}