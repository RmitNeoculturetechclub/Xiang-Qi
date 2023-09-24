package com.example.xiangqi.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Model.Piece;
import com.example.xiangqi.View.InitializeView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

public class SubGeneralMethods {

    public static void processBoard(Cell[][] board, BiConsumer<Cell, Piece> processor) {
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

    public static int countThreatenedGenerals(String player, Cell[][] board, int generalX, int generalY) {
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

    public static void removePreviousCircles(String player, boolean isChecked,
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

    public static void addNewCircle(String player, Color circleColor, int generalX, int generalY, AnchorPane pane,
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

}
