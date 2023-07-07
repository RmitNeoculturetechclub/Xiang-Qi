package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

public class General extends Piece {
	static int generalCounter = 0;
	Boolean isCheck = false;
	Boolean isCheckMate = false;

	public General(String id, String player) {
		super(id, player);
	}

	public General() {
	}

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

			if (isValidMove(row, col, board, currentPlayer)) {
				possiblePositions.add(new int[] { row, col });
			}
		}

		return possiblePositions;
	}

	private boolean isValidMove(int x, int y, Cell[][] board, String currentPlayer) {
		// Check if the position is within the palace
		if (currentPlayer == "Black") {
			if (!(x >= 3 && x <= 5 && y >= 0 && y <= 2)) {
				return false;
			}
		} else {
			if (!(x >= 3 && x <= 5 && y >= 7 && y <= 9)) {
				return false;
			}
		}

		return board[x][y].getPiece() == null || board[x][y].getPiece().getPlayerName() != this.getPlayerName();
	}

	public void setNumPieces(int numPieces) {
		General.generalCounter += numPieces;
	}

	public int getNumPiece() {
		return General.generalCounter;
	}

	public String getPieceImageName() {
		return "General_" + getPlayerName();
	}
}
