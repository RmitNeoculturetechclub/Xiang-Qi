package com.example.xiangqi.Controller;

import java.util.ArrayList;
import java.util.List;

import com.example.xiangqi.Model.Cell;
import com.example.xiangqi.Model.General;

public class BoardManager {

    public List<int[]> regeneratePossibleCells(List<int[]> possibleCells, General general, String currentPlayer,
            Cell[][] board, Cell cell) {
        boolean isChecked = general.getChecked(currentPlayer);
        CheckGeneral IsCheckGeneral = new CheckGeneral();

        if (isChecked) {
            List<int[]> cellsToRemove = new ArrayList<>();
            // for every possible cell, simulate -> tmp board -> isProtection
            for (int[] possibleCell : possibleCells) {
                int positionX = possibleCell[0];
                int positionY = possibleCell[1];

                Cell[][] tmpBoard = CheckGeneral.makeTemporaryMove(board, cell, positionX, positionY);

                // filter the cells with isProtection
                // if isProtection false, delete the cell from the possible cells
                if (!IsCheckGeneral.isProtection(tmpBoard, currentPlayer)) {
                    cellsToRemove.add(possibleCell);
                }
            }
            // display only helpful movements for their general
            possibleCells.removeAll(cellsToRemove);
        }

        return possibleCells;
    };
}
