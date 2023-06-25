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

		getValidAdvisorMoves();
		return possiblePositions;
	}

	public ArrayList<String> getValidAdvisorMoves(int fromX, int fromY, Cell[][] board) {
		ArrayList<String> validMoves = new ArrayList<String>();
		// Loop through all possible positions within the palace
		for (int toX = 3; toX <= 5; toX++) {
			for (int toY = 0; toY <= 2; toY++) {
				// Check if the Advisor is moving diagonally within the palace
				if (Math.abs(toX - fromX) == 1 && Math.abs(toY - fromY) == 1) {
					// Check if the target position is empty or occupied by an opponent's piece
					if (board[toX][toY] == null || isOpponentPiece(board, toX, toY)) {
						// Add the move to the list of valid moves
						validMoves.add(fromX + "," + fromY + "," + toX + "," + toY);
					}
				}
			}
		}
		return validMoves;
	}

	private boolean isOpponentPiece(String[][] board, int toX, int toY) {
		// Check if the target position is occupied by an opponent's piece
		String piece = board[toX][toY];
		if (piece != null && piece.charAt(0) != board[fromX][fromY].charAt(0)) {
			return true;
		}
		return false;
	}
}
