package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

public class Soldier extends Piece {
	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		List<int[]> possiblePositions = new ArrayList<>();
		int x = currentPosition[0];
		int y = currentPosition[1];
		String currentPlayer = getPlayerName();

		// Determine the direction of movement based on the player
		int forwardDirection = currentPlayer.equals("Black") ? 1 : -1;

		// Check if the Soldier has crossed the river
		boolean crossedRiver = (currentPlayer.equals("Black") && x >= 5) || (currentPlayer.equals("Red") && x <= 4);

		// Add the forward move
		int[] forwardMove = { x + forwardDirection, y };
		if (isValidMove(forwardMove, board)) {
			possiblePositions.add(forwardMove);
		}

		// Add the sideways moves if the Soldier has crossed the river
		if (crossedRiver) {
			int[] leftMove = { x, y - 1 };
			int[] rightMove = { x, y + 1 };
			if (isValidMove(leftMove, board)) {
				possiblePositions.add(leftMove);
			}
			if (isValidMove(rightMove, board)) {
				possiblePositions.add(rightMove);
			}
		}

		return possiblePositions;
	}

	private boolean isValidMove(int[] position, Cell[][] board) {
		int x = position[0];
		int y = position[1];

		// Check if the position is within the board bounds
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
			return false;
		}

		return board[x][y].getPiece() == null || board[x][y].getPiece().getPlayerName() != this.getPlayerName();
		// Check if the position is empty
		// return board[x][y].getPiece() == null;
	}
}
