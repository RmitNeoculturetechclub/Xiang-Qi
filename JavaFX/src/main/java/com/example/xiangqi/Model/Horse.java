package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

public class Horse extends Piece {
	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		List<int[]> possiblePositions = new ArrayList<>();
		int x = currentPosition[0];
		int y = currentPosition[1];

		int[][] firstDirections = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int[][] secondDirections = { { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };

		for (int[] firstDirection : firstDirections) {
			int firstRow = x + firstDirection[0];
			int firstColumn = y + firstDirection[1];

			if (isValidMove(firstRow, firstColumn, board)) {
				for (int[] secondDirection : secondDirections) {
					int secondRow = firstRow + secondDirection[0];
					int secondColumn = firstColumn + secondDirection[1];

					if (isValidMove(secondRow, secondColumn, board)) {
						possiblePositions.add(new int[] { secondRow, secondColumn });
					}
				}
			}

		}
		return possiblePositions;
	}

	private boolean isValidMove(int x, int y, Cell[][] board) {
		// Check if the position is within the board bounds
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
			return false;
		}

		return board[x][y].getPiece() == null || board[x][y].getPiece().getPlayerName() != this.getPlayerName();
	}

	public String getPieceImageName() {
		return "Horse_" + getPlayerName();
	}
}
