package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

import com.example.xiangqi.Enums.Model.PieceName;

public class Horse extends Piece {
	static int horseCounter = 0;

	public Horse(String id, String player) {
		super(id, player);
	}

	public Horse() {
	}

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		List<int[]> possiblePositions = new ArrayList<>();
		int[][] directions = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };

		for (int[] direction : directions) {
			int newRow = currentPosition[0] + direction[0];
			int newColumn = currentPosition[1] + direction[1];
			if (isValidMove(newRow, newColumn, board)) {
				possiblePositions.add(new int[] { newRow, newColumn });
			}
		}

		return possiblePositions;
	}

	private boolean isValidMove(int row, int column, Cell[][] board) {
		int numRows = board.length;
		int numColumns = board[0].length;

		return row >= 0 && row < numRows && column >= 0 && column < numColumns;
	}

	public void setNumPieces(int numPieces) {
		Horse.horseCounter += numPieces;
	}

	public int getNumPiece() {
		return Horse.horseCounter;
	}

	public String getPieceImageName() {
		return "Horse_" + getPlayerName();
	}
}
