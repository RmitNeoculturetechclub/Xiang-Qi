package com.example.xiangqi.Model;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.xiangqi.Enums.Constant.InitPieceSetup.XiangQiBoard;

public class Advisor extends Piece{
	static int advisorCounter = 4;
	public Advisor (String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public Advisor () {
	}

	public List <int[]> getAllPossibleMoves (int[] currentPosition) {
		List <int[]> possiblePositions = new ArrayList <>();

		getAdvisorMoves(currentPosition,1, 0);
		return possiblePositions;
	}

	public List<int[]> getAdvisorMoves(int[] currentPosition, int fromX, int fromY ) {
		List<int[]> possibleMoves = new ArrayList<>();
		// Loop through all possible positions within the palace
		for (int toX = 3; toX <= 5; toX++) {
			for (int toY = 0; toY <= 2; toY++) {
				// Check if the Advisor is moving diagonally within the palace
				if (Math.abs(toX - fromX) == 1 && Math.abs(toY - fromY) == 1) {
					// Add the move to the list of possible moves
					int[] move = {fromX, fromY, toX, toY};
					isValidAdvisorDirection(fromX, fromY, toX, toY);
					possibleMoves.add(move);
				}
			}
		}
		return possibleMoves;
	}

	public boolean isValidAdvisorDirection(int fromX, int fromY, int toX, int toY) {
		// Check if the Advisor is within the palace
		if (toX < 3 || toX > 5) {
			return false;
		}
		if (fromY < 0 || fromY > 2 || toY < 0 || toY > 2) {
			return false;
		}
		// Check if the Advisor is moving diagonally within the palace
		if (Math.abs(toX - fromX) != 1 || Math.abs(toY - fromY) != 1) {
			return false;
		}
		return true;

		int midX = (getX() + x) / 2;
		int midY = (getY() + y) / 2;
		if (board[midX][midY] != null) {
			// There is a piece blocking the advisor's path
			return false;
		}
		return true;
	}
}
