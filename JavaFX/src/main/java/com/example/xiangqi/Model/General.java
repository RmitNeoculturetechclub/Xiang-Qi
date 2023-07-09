package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

public class General extends Piece {
	Boolean isCheck = false;
	Boolean isCheckMate = false;

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		List<int[]> possiblePositions = new ArrayList<>();
		int x = currentPosition[0];
		int y = currentPosition[1];
		String currentPlayer = getPlayerName();

		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		for (int[] direction : directions) {
			int row = x + direction[0];
			int col = y + direction[1];

			if (isValidMove(row, col, board, currentPlayer) && isFacing(row, col, board, x) == false) {
				possiblePositions.add(new int[] { row, col });
			}
		}

		return possiblePositions;
	}

	// check if it's facing the enemy general directly
	private boolean isFacing(int x, int y, Cell[][] board, int currentX) {
		boolean isBlocked = false;
		boolean hasEnemyGeneral = false;
		int enemyGeneralX = -1;

		// Find the position of the enemy general in the same column
		for (int i = 0; i < 10; i++) {
			if (i == currentX) {
				continue;
			}
			if (board[i][y].getPiece() != null && board[i][y].getPiece().getPieceName().equals("General")) {

				hasEnemyGeneral = true;
				enemyGeneralX = i;
				break;

			}
		}

		if (hasEnemyGeneral == false) {
			return false;
		} else {
			// Check for another piece between the two generals
			int start = Math.min(x, enemyGeneralX) + 1;
			int end = Math.max(x, enemyGeneralX);

			for (int i = start; i < end; i++) {
				if (board[i][y].getPiece() != null) {
					isBlocked = true;

					break;
				}
			}

			return !isBlocked;
		}
	}

	private boolean isValidMove(int x, int y, Cell[][] board, String currentPlayer) {
		// Check if the position is within the palace
		if (currentPlayer == "Black") {
			if (!(x >= 0 && x <= 2 && y >= 3 && y <= 5)) {
				return false;
			}
		} else {
			if (!(x >= 7 && x <= 9 && y >= 3 && y <= 5)) {
				return false;
			}
		}

		return board[x][y].getPiece() == null || board[x][y].getPiece().getPlayerName() != this.getPlayerName();
	}

	public String getPieceImageName() {
		String PieceName = this.getPieceName();
		return PieceName + "_" + getPlayerName();
	}

	public String getPieceName() {
		String className = this.getClass().getSimpleName();
		String[] array = className.split("_");
		return array[0];
	}

	public void isChecked() {
		this.isCheck = true;
	}

}
