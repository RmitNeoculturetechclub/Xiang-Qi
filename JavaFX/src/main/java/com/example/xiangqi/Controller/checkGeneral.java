package com.example.xiangqi.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Model.General;
import com.example.xiangqi.Model.Piece;
import com.example.xiangqi.View.InitializeView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

public class CheckGeneral {

    private void processBoard(Cell[][] board, BiConsumer<Cell, Piece> processor) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Cell cell = board[row][col];
                if (cell != null) {
                    Piece piece = cell.getPiece();
                    if (piece != null) {
                        processor.accept(cell, piece);
                    }
                }
            }
        }
    }

    public Cell findGeneral(String player, Cell[][] board) {
        Cell[] generalCell = new Cell[1]; // Using an array to hold the result
        processBoard(board, (cell, piece) -> {
            if (piece.getPieceName().equals("General") && piece.getPlayerName().equals(player)) {
                generalCell[0] = cell;
            }
        });
        return generalCell[0];
    }

    public int countThreatenedGenerals(String player, Cell[][] board, int generalX, int generalY) {
        int[] count = new int[1];
        processBoard(board, (cell, piece) -> {
            if (!piece.getPlayerName().equals(player)) {
                List<int[]> possibleMoves = cell.getAllPossibleCells(board);
                for (int[] move : possibleMoves) {
                    int destRow = move[0];
                    int destCol = move[1];
                    if (destRow == generalX && destCol == generalY) {
                        count[0]++;
                        break;
                    }
                }
            }
        });
        return count[0];
    }

    public void isUnderThreat(Cell[][] board, AnchorPane pane, List<Pair<String, Circle>> previousGeneralCircles) {
        for (String player : new String[] { "Red", "Black" }) {
            Cell generalCell = findGeneral(player, board);
            if (generalCell != null) {
                int generalX = generalCell.getPosition()[0];
                int generalY = generalCell.getPosition()[1];
                System.out.println("General " + player + " position: " + generalX + " " + generalY);

                int count = countThreatenedGenerals(player, board, generalX, generalY);
                boolean isChecked = (count != 0);

                removePreviousCircles(player, isChecked, previousGeneralCircles, pane);

                if (isChecked) {
                    Color circleColor = (player.equals("Black")) ? Color.RED : Color.BLACK;
                    addNewCircle(player, circleColor, generalX, generalY, pane, previousGeneralCircles);
                }

                General general = (General) generalCell.getPiece();
                general.setChecked(isChecked, player);
                System.out.println("general" + player + "isChecked: " + isChecked);
            }
        }
    }

    private void removePreviousCircles(String player, boolean isChecked,
            List<Pair<String, Circle>> previousGeneralCircles, AnchorPane pane) {
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
    }

    private void addNewCircle(String player, Color circleColor, int generalX, int generalY, AnchorPane pane,
            List<Pair<String, Circle>> previousGeneralCircles) {
        boolean circleExists = false;
        if (previousGeneralCircles != null) {
            for (Pair<String, Circle> pair : previousGeneralCircles) {
                if (pair.getKey().equals(player)) {
                    circleExists = true;
                    break;
                }
            }
        }

        if (!circleExists) {
            InitializeView initializeView = new InitializeView();
            Circle generalCircle = initializeView.createGeneralColor(generalY, generalX, circleColor);
            pane.getChildren().add(generalCircle);

            if (previousGeneralCircles == null) {
                previousGeneralCircles = new ArrayList<>();
            }
            previousGeneralCircles.add(new Pair<>(player, generalCircle));
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

            int count = countThreatenedGenerals(player, board, generalX, generalY);

            if (count == 0) { // it can escape capture (no longer threaten)
                isProtection = true;
            }
        }
        System.out.println("isProtection" + isProtection);
        return isProtection;
    }

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
