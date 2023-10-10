package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

public class Elephant extends Piece {

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		List<int[]> possiblePositions = new ArrayList<>();
		int x = currentPosition[0];
		int y = currentPosition[1];
		String currentPlayer = getPlayerName();

		int[][] directions = { { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };

		for (int[] direction : directions) {
			int firstRow = x + direction[0];
			int firstCol = y + direction[1];

			// check if there's a piece blocking the path
			if (isNotMiddleBlocked(firstRow, firstCol, board, currentPlayer)) {
				int secondRow = firstRow + direction[0];
				int secondCol = firstCol + direction[1];

				if (isValidMove(secondRow, secondCol, board, currentPlayer)) {
					possiblePositions.add(new int[] { secondRow, secondCol });
				}

			}

		}
		return possiblePositions;
	}

	private boolean isValidMove(int x, int y, Cell[][] board, String currentPlayer) {
		// Check if the position is within the board bounds
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
			return false;
		}

		// Check if the Soldier has crossed the river
		if ((currentPlayer.equals("Black") && x >= 5) || (currentPlayer.equals("Red") && x <= 4)) {
			return false;
		}

		return board[x][y].getPiece() == null || board[x][y].getPiece().getPlayerName() != this.getPlayerName();
	}

	private boolean isNotMiddleBlocked(int x, int y, Cell[][] board, String currentPlayer) {
		// Check if the position is within the board bounds
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
			return false;
		}

		// Check if the Soldier has crossed the river
		if ((currentPlayer.equals("Black") && x >= 5) || (currentPlayer.equals("Red") && x <= 4)) {
			return false;
		}

		return board[x][y].getPiece() == null;
	}

	@Override
	public Elephant clone() {
		Elephant clonedElephant = (Elephant) super.clone();
		return clonedElephant;
	}
}
