package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

public class Advisor extends Piece {
	static int advisorCounter = 0;

	public Advisor(String id, String player) {
		super(id, player);
	}

	public Advisor() {
	}

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		List<int[]> possiblePositions = new ArrayList<>();
		int x = currentPosition[0];
		int y = currentPosition[1];
		String currentPlayer = getPlayerName();

		int[][] directions = { { -1, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 } };

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

	public void setNumPieces(int numPieces) {
		Advisor.advisorCounter = numPieces;
	}

	public void setNumPiece(int numPiece) {
		Advisor.advisorCounter += numPiece;
	}

	public int getNumPiece() {
		return Advisor.advisorCounter;
	}

	public String getPieceImageName() {
		return "Advisor_" + getPlayerName();
	}
}
