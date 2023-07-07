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

		int[][] FacingDirections = { { 0, 1 }, { 0, -1 } };
		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		// when facing
		if (isFacing()) {
			for (int[] direction : FacingDirections) {
				int row = x + direction[0];
				int col = y + direction[1];

				if (isValidMove(row, col, board, currentPlayer)) {
					possiblePositions.add(new int[] { row, col });
				}
			}

		} else {

			// when not facing
			for (int[] direction : directions) {
				int row = x + direction[0];
				int col = y + direction[1];

				if (isValidMove(row, col, board, currentPlayer)) {
					possiblePositions.add(new int[] { row, col });
				}
			}

		}

		return possiblePositions;
	}

	private boolean isFacing() {
		return true;
	}

	private boolean isValidMove(int x, int y, Cell[][] board, String currentPlayer) {
		// Check if the position is within the palace
		System.out.println(x + " " + y);
		if (currentPlayer == "Black") {
			if (!(x >= 0 && x <= 2 && y >= 3 && y <= 5)) {
				return false;
			}
		} else {
			if (!(x >= 6 && x <= 8 && y >= 3 && y <= 5)) {
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
