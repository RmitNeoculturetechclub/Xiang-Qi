package com.example.xiangqi.Controller;

import com.example.xiangqi.Enums.Constant.InitPieceSetup;
import com.example.xiangqi.Enums.Model.Player;
import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Model.General;
import com.example.xiangqi.Model.Piece;
import com.example.xiangqi.View.InitializeView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import com.example.xiangqi.View.DisplayPlayer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class InitializeManager {
    private InitializeView initializeView;
    private Cell[][] board;
    private AnchorPane pane;
    private Piece currentClickedPiece;
    private ImageView imageView;
    private Piece piece;
    private String currentPlayer;
    private Player player;
    private List<Pair<String, Circle>> previousGeneralCircles;

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
        initializeView = new InitializeView();

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
            Cell generalCell = findGeneral(player);
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
                                    break;
                                }
                            }
                        }
                    }
                }

                if (count != 0) {
                    isChecked = true;
                }

                // Remove the previously created circles if they exist and not checked anymore
                if (previousGeneralCircles != null) {
                    Iterator<Pair<String, Circle>> iterator = previousGeneralCircles.iterator();
                    while (iterator.hasNext()) {
                        Pair<String, Circle> pair = iterator.next();
                        Circle circle = pair.getValue();
                        if (pair.getKey().equals(player) && !isChecked) {
                            pane.getChildren().remove(circle);
                            iterator.remove();
                        }
                    }
                }

                if (isChecked) {
                    // mark it as the opponent's color
                    InitializeView initializeView = new InitializeView();
                    Color circleColor = (player.equals("Black")) ? Color.RED : Color.BLACK;

                    boolean circleExists = false;
                    if (previousGeneralCircles != null) {
                        for (Pair<String, Circle> pair : previousGeneralCircles) {
                            if (pair.getKey().equals(player)) {
                                circleExists = true;
                                break;
                            }
                        }
                    }

                    // avoid duplicating the mark in case the general remains checked
                    if (!circleExists) {
                        Circle generalCircle = initializeView.createGeneralColor(generalY, generalX, circleColor);
                        pane.getChildren().add(generalCircle);

                        if (previousGeneralCircles == null) {
                            previousGeneralCircles = new ArrayList<>();
                        }
                        previousGeneralCircles.add(new Pair<>(player, generalCircle));
                    }
                }

                General general = (General) generalCell.getPiece();
                general.setChecked(isChecked, player);

                System.out.println("general" + player + "isChecked: " + isChecked);
            }
        }
    }

    private boolean isProtection(Cell[][] board, String currentPlayer) {
        // check only their own general.
        String player = currentPlayer.equals("Red") ? "Red" : "Black";
        boolean isProtection = false;

        Cell generalCell = findGeneral(player);
        // if general is in the board
        if (generalCell != null) {
            int generalX = generalCell.getPosition()[0];
            int generalY = generalCell.getPosition()[1];
            System.out.println("General " + player + " position: " + generalX + " " + generalY);
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
                                break;
                            }
                        }
                    }
                }
            }

            if (count == 0) { // it can escape capture (no longer threaten)
                isProtection = true;
            }
        }
        System.out.println("isProtection" + isProtection);
        return isProtection;
    }

    private Cell findGeneral(String player) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Cell cell = board[row][col];
                if (cell != null) {
                    Piece piece = cell.getPiece();

                    if (piece != null && piece.getPieceName().equals("General")
                            && piece.getPlayerName().equals(player)) {
                        return board[row][col]; // General found
                    }
                }
            }
        }
        return null; // General not found
    }

    private static Cell[][] makeTemporaryMove(Cell[][] currentBoard, Cell sourceCell, int destRow, int destCol) {
        // Create a deep copy of the current board to create a temporary board
        Cell[][] tmpBoard = new Cell[currentBoard.length][currentBoard[0].length];
        for (int row = 0; row < currentBoard.length; row++) {
            for (int col = 0; col < currentBoard[row].length; col++) {
                Cell originalCell = currentBoard[row][col];
                if (originalCell != null) {
                    Piece piece = originalCell.getPiece();
                    Cell newCell = new Cell(row, col);
                    if (piece != null) {
                        Piece newPiece = piece.clone();
                        newCell.setPiece(newPiece);
                    }
                    tmpBoard[row][col] = newCell;
                } else {
                    tmpBoard[row][col] = null;
                }
            }
        }

        // 1. Get the source position of the piece to be moved
        int sourceX = sourceCell.getPosition()[0];
        int sourceY = sourceCell.getPosition()[1];

        // 2. Remove the piece from the source position
        Piece pieceToMove = tmpBoard[sourceX][sourceY].getPiece();
        tmpBoard[sourceX][sourceY].setPiece(null);

        // 3. set the destination position as null (previous piece)
        tmpBoard[destRow][destCol].setPiece(null);

        // 4. Set the piece in the destination position
        tmpBoard[destRow][destCol].setPiece(pieceToMove);

        return tmpBoard;
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

                    // check if the current player's general isChecked is true or false
                    // if checked, regenerate the possible cells
                    General general = (General) findGeneral(currentPlayer).getPiece();
                    if (general != null) {
                        boolean isChecked = general.getChecked(currentPlayer);

                        if (isChecked) {
                            List<int[]> cellsToRemove = new ArrayList<>();
                            // for every possible cell, simulate -> tmp board -> isProtection
                            for (int[] possibleCell : possibleCells) {
                                int positionX = possibleCell[0];
                                int positionY = possibleCell[1];

                                Cell[][] tmpBoard = makeTemporaryMove(board, cell, positionX, positionY);

                                // filter the cells with isProtection
                                // if isProtection false, delete the cell from the possible cells
                                if (!isProtection(tmpBoard, currentPlayer)) {
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

                            // check both general isUnderThreat
                            isUnderThreat(board);

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
