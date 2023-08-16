package com.example.xiangqi.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Model.General;
import com.example.xiangqi.Model.Piece;
import com.example.xiangqi.View.InitializeView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

public class CheckGeneral {

    public void isUnderThreat(Cell[][] board, AnchorPane pane, List<Pair<String, Circle>> previousGeneralCircles) {
        for (String player : new String[] { "Red", "Black" }) {
            Cell generalCell = findGeneral(player, board);
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
                System.out.println(previousGeneralCircles + " " + "previousGeneralCircles");

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
                    System.out.println(previousGeneralCircles + " " + "previousGeneralCircles");
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

    public boolean isProtection(Cell[][] board, String currentPlayer) {
        // check only their own general.
        String player = currentPlayer.equals("Red") ? "Red" : "Black";
        boolean isProtection = false;

        Cell generalCell = findGeneral(player, board);
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

    public Cell findGeneral(String player, Cell[][] board) {
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

    // makeTemporaryMove
    public static Cell[][] makeTemporaryMove(Cell[][] currentBoard, Cell sourceCell, int destRow, int destCol) {
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

}
