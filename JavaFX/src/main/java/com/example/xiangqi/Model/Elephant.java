package com.example.xiangqi.Model;

import java.util.List;
import java.util.ArrayList;

public class Elephant extends Piece {

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		// TODO: Implement this method
		return super.getAllPossibleMoves(currentPosition, board);
	}

	public List<int[]> getElephantMoves(int fromX, int fromY) {
		List<int[]> possibleMoves = new ArrayList<int[]>();
		// Loop through all possible positions within the elephant's range
		for (int toX = 0; toX <= 8; toX++) {
			for (int toY = 0; toY <= 9; toY++) {
				// Check if the Elephant is moving within its range and following the diagonal pattern
				if (Math.abs(toX - fromX) == 2 && Math.abs(toY - fromY) == 2
						&& ((toX <= 4 && fromX <= 4) || (toX >= 5 && fromX >= 5))
						&& ((toY <= 2 && fromY <= 2) || (toY >= 7 && fromY >= 7))) {
					// Check if the path is not blocked by any piece
					if (!isPathBlocked(fromX, fromY, toX, toY)) {
						// Add the move to the list of possible moves
						int[] move = {fromX, fromY, toX, toY};
						possibleMoves.add(move);
					}
				}
			}
		}
		return possibleMoves;
	}

	private boolean isPathBlocked(int fromX, int fromY, int toX, int toY) {
		// Check if the path between the starting and ending position is blocked by any piece
		int offsetX = (toX - fromX) / 2;
		int offsetY = (toY - fromY) / 2;
		int midX = fromX + offsetX;
		int midY = fromY + offsetY;
		if (board[midX][midY] != null) {
			return true;
		}
		return false;
	}
}
