package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

public class Chariot extends Piece {
	static int chariotCounter = 0;

	public Chariot(String id, String player) {
		super(id, player);
	}

	public Chariot() {
	}

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		List<int[]> possiblePositions = new ArrayList<>();
		int x = currentPosition[0];
		int y = currentPosition[1];

		// Check for possible moves horizontally to the right
		for (int i = x + 1; i < 9; i++) {
			if (!isValidMove(i, y, board)) {
				break;
			}
			possiblePositions.add(new int[] { i, y });
		}

		// Check for possible moves horizontally to the left
		for (int i = x - 1; i >= 0; i--) {
			if (!isValidMove(i, y, board)) {
				break;
			}
			possiblePositions.add(new int[] { i, y });
		}

		// Check for possible moves vertically upwards
		for (int j = y - 1; j >= 0; j--) {
			if (!isValidMove(x, j, board)) {
				break;
			}
			possiblePositions.add(new int[] { x, j });
		}

		// Check for possible moves vertically downwards
		for (int j = y + 1; j < 10; j++) {
			if (!isValidMove(x, j, board)) {
				break;
			}
			possiblePositions.add(new int[] { x, j });
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

	public void setNumPieces(int numPieces) {
		Chariot.chariotCounter += numPieces;
	}

	public int getNumPiece() {
		return Chariot.chariotCounter;
	}

	public String getPieceImageName() {
		return "Chariot_" + getPlayerName();
	}
}
