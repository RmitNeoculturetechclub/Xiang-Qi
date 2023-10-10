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

			if (isMiddleBlocked(firstRow, firstColumn, board)) {
				for (int[] secondDirection : secondDirections) {
					int secondRow = firstRow + secondDirection[0];
					int secondColumn = firstColumn + secondDirection[1];

					// non-zero coordinates in the second dir if they were non-zero in the first dir
					if ((firstDirection[0] != 0 && firstDirection[0] == secondDirection[0])
							|| (firstDirection[1] != 0 && firstDirection[1] == secondDirection[1])) {

						// Check if the move is valid in the second direction
						if (isValidMove(secondRow, secondColumn, board)) {
							possiblePositions.add(new int[] { secondRow, secondColumn });
						}
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

	private boolean isMiddleBlocked(int x, int y, Cell[][] board) {

		// Check if the position is within the board bounds
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
			return false;
		}

		return board[x][y].getPiece() == null;

	}

	@Override
	public Horse clone() {
		Horse clonedHorse = (Horse) super.clone();
		return clonedHorse;
	}
}
