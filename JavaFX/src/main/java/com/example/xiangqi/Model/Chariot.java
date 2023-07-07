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

		// Check for valid moves up vertically
		checkValidMovesInDirection(currentPosition, -1, 0, possiblePositions, board);

		// Check for valid moves down vertically
		checkValidMovesInDirection(currentPosition, 1, 0, possiblePositions, board);

		// Check for valid moves horizontally to the right
		checkValidMovesInDirection(currentPosition, 0, -1, possiblePositions, board);

		// Check for valid moves horizontally to the left
		checkValidMovesInDirection(currentPosition, 0, 1, possiblePositions, board);

		return possiblePositions;
	}

	// Helper method to check for valid moves in a given direction
	private void checkValidMovesInDirection(int[] currentPosition, int dx, int dy, List<int[]> possiblePositions,
			Cell[][] board) {
		int isBlocked = 0;
		int x = currentPosition[0] + dx;
		int y = currentPosition[1] + dy;

		while (x >= 0 && x <= 9 && y >= 0 && y <= 8) {
			if (board[x][y].getPiece() != null) {
				isBlocked++;
			} else {
				if (isBlocked == 0) {
					possiblePositions.add(new int[] { x, y });
				}
			}

			x += dx;
			y += dy;
		}
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
