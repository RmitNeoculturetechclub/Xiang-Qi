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

	// @Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		List<int[]> possiblePositions = new ArrayList<>();

		getAdvisorMoves(currentPosition, 1, 0);
		return possiblePositions;
	}

	private List<int[]> getAdvisorMoves(int[] currentPosition, int fromX, int fromY) {
		List<int[]> possibleMoves = new ArrayList<>();
		// Loop through all possible positions within the palace
		for (int toX = 3; toX <= 5; toX++) {
			for (int toY = 0; toY <= 2; toY++) {
				// Check if the Advisor is moving diagonally within the palace
				if (Math.abs(toX - fromX) == 1 && Math.abs(toY - fromY) == 1) {
					// Add the move to the list of possible moves
					int[] move = { fromX, fromY, toX, toY };
					possibleMoves.add(move);
				}
			}
		}
		return possibleMoves;
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
