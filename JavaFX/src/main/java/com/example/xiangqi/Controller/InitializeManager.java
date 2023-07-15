package com.example.xiangqi.Controller;

import com.example.xiangqi.Enums.Constant.InitPieceSetup;
import com.example.xiangqi.Enums.Model.Player;
import com.example.xiangqi.Enums.Model.PieceName;
import com.example.xiangqi.Handler.IdGeneration;
import com.example.xiangqi.Model.Advisor;
import com.example.xiangqi.Model.Canon;
import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Model.Chariot;
import com.example.xiangqi.Model.Elephant;
import com.example.xiangqi.Model.General;
import com.example.xiangqi.Model.Horse;
import com.example.xiangqi.Model.Piece;
import com.example.xiangqi.Model.Soldier;
import com.example.xiangqi.View.InitializeView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import com.example.xiangqi.View.DisplayPlayer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InitializeManager {
    private InitializeView initializeView;
    private Cell[][] board;
    private AnchorPane pane;
    private Piece currentClickedPiece;
    private ImageView imageView;
    private Piece piece;
    private String currentPlayer;
    private Player player;

    private ArrayList<Circle> displayCircles;

    public InitializeManager() throws IOException {
        initializeView = new InitializeView();
        currentPlayer = "Red";
    }

    public Scene init() throws IOException {
        URL url = InitializeManager.class.getResource("/com/example/xiangqi/Board.fxml");
        assert url != null;
        pane = FXMLLoader.load(url);
        displayCircles = new ArrayList<>();

        this.initializeBoard();

        HBox hBox = new HBox();
        hBox.getChildren().add(pane);
        return new Scene(hBox);
    }

    public InitializeManager(ImageView imageView, Piece piece) {
        this.imageView = imageView;
        this.piece = piece;
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

    private boolean isLastExistingPiece(Cell cell) {
        for (Cell[] row : board) {
            for (Cell c : row) {
                if (c.getPiece() != null && c != cell) {
                    return false;
                }
            }
        }
        return true;
    }

    private void isUnderThreat(Cell[][] board) {
        for (String player : new String[] { "Red", "Black" }) {
            Cell generalCell = findGeneral(board, player);
            System.out.println("generalCell: " + generalCell);
            // if general is in the board
            if (generalCell != null) {
                int generalX = generalCell.getPosition()[0];
                int generalY = generalCell.getPosition()[1];
                System.out.println("General " + player + " position: " + generalX + " " + generalY);
                boolean isChecked = false;
                int count = 0;

                // check the possible moves of the opponent pieces
                for (int row = 0; row < board.length; row++) {
                    for (int col = 0; col < board[row].length; col++) {
                        Cell cell = board[row][col];
                        Piece piece = cell.getPiece();

                        if (piece != null && !piece.getPlayerName().equals(player)) {
                            List<int[]> possibleMoves = cell.getAllPossibleCells(board);

                            for (int[] move : possibleMoves) {
                                int destRow = move[0];
                                int destCol = move[1];

                                if (destRow == generalX && destCol == generalY) {
                                    count++;
                                    System.out.println(
                                            "Opponent name: " + piece.getPlayerName() + " " + piece.getPieceName() + " "
                                                    + destRow + ", " + destCol);
                                    break;
                                }
                            }
                        }
                    }
                }

                if (count != 0) {
                    isChecked = true;
                }

                General general = (General) generalCell.getPiece();
                general.setChecked(isChecked, player);
                System.out.println("general" + player + "isChecked: " + isChecked);
            }
        }
    }

    private Cell findGeneral(Cell[][] board, String player) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Cell cell = board[row][col];
                Piece piece = cell.getPiece();

                if (piece != null && piece.getPieceName().equals("General") && piece.getPlayerName().equals(player)) {
                    return board[row][col];
                }
            }
        }
        return null; // General not found
    }

    private String switchPlayer(String currentPlayer) {
        return currentPlayer.equals("Red") ? "Black" : "Red";
    }

    private void imageViewSetOnMouseClicked(Cell cell) {
        ImageView pieceImageView;

        try {
            pieceImageView = this.initializeView.createPieceView(
                    String.format("/pictures/%s.png", cell.getPiece().getPieceImageName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        pieceImageView.setOnMouseClicked(e -> {
            // TODO: test after finishing the movement of all types of pieces
            if (isLastExistingPiece(cell)) {
                DisplayPlayer winnerDisplay = new DisplayPlayer();
                winnerDisplay.displayWinner(cell.getPiece().getPlayerName());
            } else {
                if (cell.getPiece() != null && cell.getPiece().getPlayerName().equals(currentPlayer)) {
                    pane.getChildren().removeAll(displayCircles);
                    displayCircles.clear();
                    currentClickedPiece = cell.getPiece();

                    List<int[]> possibleCells = cell.getAllPossibleCells(this.board);
                    // TODO: check if the current player's general isChecked is true or false
                    // if getChecked(currentPlayer) {
                    // } else {

                    // }

                    // if true, recreate cells: eliminate the cells that cannot protect the General
                    // from being Checked (so the user can make a good decision for their general)
                    // if false, do nothing

                    for (int[] positions : possibleCells) {
                        int positionX = positions[1];
                        int positionY = positions[0];
                        Circle circlePossible = this.initializeView.createCirclePossibleCell(positionX, positionY,
                                currentPlayer);

                        circlePossible.setOnMouseClicked(event -> {

                            // Get the new cell based on the clicked rectangle's position
                            Cell newCell = board[positionY][positionX];

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

                            // TODO: check both general isUnderThreat
                            isUnderThreat(board);

                            // TODO: if one of them in checked, both players should be notified of the check

                            // switch the current player
                            currentPlayer = switchPlayer(currentPlayer);
                        });

                        this.pane.getChildren().add(circlePossible);
                        this.displayCircles.add(circlePossible);
                    }
                } else { // if the user clicks on the opposite side of the current player
                    // DisplayPlayer currentPlayerDisplay = new DisplayPlayer();
                    // currentPlayerDisplay.displayPlayer(currentPlayer);
                }
            }
        });

        cell.drawPieceImageView(pieceImageView);
        this.pane.getChildren().add(cell.getImageView());

    }
}
