package com.example.xiangqi.Enums.Constant;

import com.example.xiangqi.Model.Cell;

import java.util.HashMap;
import java.util.Map;

public class PointConstant {
    // Total point
    public static Double BLACK = 0.0;
    public static Double RED = 0.0;

    // Map for points they got
    public static HashMap<String, Double> redPointMap = new HashMap<>();
    public static HashMap<String, Double> blackPointMap = new HashMap<>();

    // Piece point
    public static Map<String, Double> PointConstant = new HashMap<>();
    static {
        PointConstant.put("Canon", 4.5);
        PointConstant.put("Horse", 4.0);
        PointConstant.put("Advisor", 2.0);
        PointConstant.put("Elephant", 2.0);
        PointConstant.put("Chariot", 9.0);
        PointConstant.put("Soldier", 1.0);
        PointConstant.put("General", 1000.0);
    }

    public void addPoint(Cell newCell, Cell cell, String currentPlayer) {
        if (newCell.getPiece() != null) {
            String pointPieceName = newCell.getPiece().getPieceName();
            String pointPieceColor = newCell.getPiece().getPlayerName();

            // get the corresponding point
            if ((currentPlayer.equals("Black") && pointPieceColor.equals("Red")) ||
                    (currentPlayer.equals("Red") && pointPieceColor.equals("Black"))) {

                double pointsToAdd = PointConstant.get(pointPieceName);
                double currentPoints = (currentPlayer.equals("Black"))
                        ? blackPointMap.getOrDefault(pointPieceName, 0.0)
                        : redPointMap.getOrDefault(pointPieceName, 0.0);

                // Handle the case where the soldier has crossed the river
                if (pointPieceName.equals("Soldier")) {
                    int x = cell.getPosition()[0];
                    if ((currentPlayer.equals("Black") && x >= 5) ||
                            (currentPlayer.equals("Red") && x <= 4)) {
                        pointsToAdd += 1.0;
                        currentPoints = (currentPlayer.equals("Black"))
                                ? blackPointMap.getOrDefault("Soldier", 0.0)
                                : redPointMap.getOrDefault("Soldier", 0.0);
                    }
                }

                // Put the points into the map
                if (currentPlayer.equals("Black")) {
                    BLACK += pointsToAdd;
                    blackPointMap.put(pointPieceName, currentPoints + pointsToAdd);
                } else {
                    RED += pointsToAdd;
                    redPointMap.put(pointPieceName, currentPoints + pointsToAdd);
                }

            }
        }
    }

}
