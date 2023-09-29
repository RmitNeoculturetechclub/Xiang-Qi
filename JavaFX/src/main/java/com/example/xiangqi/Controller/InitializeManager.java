package com.example.xiangqi.Controller;

import com.example.xiangqi.Enums.Constant.InitPieceSetup;
import com.example.xiangqi.Enums.Constant.PlayerConstant;
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

    // private boolean isLastExistingPiece(Cell cell) {
    // for (Cell[] row : board) {
    // for (Cell c : row) {
    // if (c.getPiece() != null && c != cell) {
    // return false;
    // }
    // }
    // }
    // return true;
    // }

    public void switchPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer.equals("Red") ? "Black" : "Red";
    }

    private void imageViewSetOnMouseClicked(Cell cell) {
        ImageView pieceImageView;
        CheckGeneral InsCheckGeneral = new CheckGeneral();

        try {
            pieceImageView = this.initializeView.createPieceView(
                    String.format("/com/example/xiangqi/pictures/%s.png", cell.getPiece().getPieceImageName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Click on a piece to move
        pieceImageView.setOnMouseClicked(e -> {
            // TODO: test after finishing the movement of all types of pieces
            // if (isLastExistingPiece(cell)) {
            // StatusView winnerDisplay = new StatusView();
            // winnerDisplay.displayWinner();
            // } else {
            // only if the piece is not null && the current player
            if (cell.getPiece() != null && cell.getPiece().getPlayerName().equals(currentPlayer)) {
                pane.getChildren().removeAll(displayCircles);
                displayCircles.clear();
                currentClickedPiece = cell.getPiece();

                List<int[]> possibleCells = cell.getAllPossibleCells(this.board);

                // check if the current player's general isChecked is true or false
                // if checked, regenerate the possible cells
                General general = (General) InsCheckGeneral.findGeneral(currentPlayer, this.board).getPiece();
                if (general != null) {
                    boolean isChecked = general.getChecked(currentPlayer);

                    if (isChecked) {
                        List<int[]> cellsToRemove = new ArrayList<>();
                        // for every possible cell, simulate -> tmp board -> isProtection
                        for (int[] possibleCell : possibleCells) {
                            int positionX = possibleCell[0];
                            int positionY = possibleCell[1];

                            Cell[][] tmpBoard = CheckGeneral.makeTemporaryMove(board, cell, positionX, positionY);

                            // filter the cells with isProtection
                            // if isProtection false, delete the cell from the possible cells
                            if (!InsCheckGeneral.isProtection(tmpBoard, currentPlayer)) {
                                cellsToRemove.add(possibleCell);
                            }
                        }
                        // display only helpful movements for their general
                        possibleCells.removeAll(cellsToRemove);
                    }
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
                        if (newCell.getPiece() != null) {
                            String pointPieceName = newCell.getPiece().getPieceName();

                            String pointPieceColor = newCell.getPiece().getPlayerName();
                            if (currentPlayer == "Black" && pointPieceColor == "Red") {
                                PointConstant.BLACK += PointConstant.PointConstant.get(pointPieceName);

                                // Handle different soldier points
                                if (cell.getPiece().getPieceName().equals("Soldier")) {
                                    int x = cell.getPosition()[0];
                                    if (x >= 5) {
                                        PointConstant.BLACK += 1.0;
                                    }
                                }

                            } else if (currentPlayer == "Red" && pointPieceColor == "Black") {
                                PointConstant.RED += PointConstant.PointConstant.get(pointPieceName);

                                // Handle different soldier points
                                if (cell.getPiece().getPieceName().equals("Soldier")) {
                                    int x = cell.getPosition()[0];
                                    if (x <= 4) {
                                        PointConstant.RED += 1.0;
                                    }
                                }
                            }

                            if (pointPieceName == "General") {
                                StatusView winnerDisplay = new StatusView();
                                winnerDisplay.displayWinner();
                            }

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
                        InsCheckGeneral.isUnderThreat(board, this.pane, this.previousGeneralCircles);

                        // switch the current player
                        switchPlayer(currentPlayer);
                        statusView.updateBoard(this.board);
                        statusView.updatePlayerStatus(currentPlayer);
                        statusView.updatePointStatus(PointConstant.BLACK, PointConstant.RED);

                        // statusView.resetTimer(60);

                    });

                    this.pane.getChildren().add(circlePossible);
                    this.displayCircles.add(circlePossible);
                }
            }
            // }
        });

        cell.drawPieceImageView(pieceImageView);
        this.pane.getChildren().add(cell.getImageView());

    }
}