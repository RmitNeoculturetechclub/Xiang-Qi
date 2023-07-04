package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

import com.example.xiangqi.Enums.Model.PieceName;

public class Horse extends Piece {
	static int horseCounter = 0;

	public Horse(String id, String player) {
		super(id, player);
	}

	public Horse() {
	}

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		List<int[]> possiblePositions = new ArrayList<>();
		int x = currentPosition[0];
		int y = currentPosition[1];

		int[][] firstDirections = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int[][] secondDirections = { { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };

		for (int[] firstDirection : firstDirections) {
			int firstRow = x + firstDirection[0];
			int firstColumn = y + firstDirection[1];

			if (isValidMove(firstRow, firstColumn, board)) {
				for (int[] secondDirection : secondDirections) {
					int secondRow = firstRow + secondDirection[0];
					int secondColumn = firstColumn + secondDirection[1];

					if (isValidMove(secondRow, secondColumn, board)) {
						possiblePositions.add(new int[] { secondRow, secondColumn });
					}
				}
			}

		}
		return possiblePositions;
	}

	private boolean isValidMove(int row, int column, Cell[][] board) {
		int numRows = board.length;
		int numColumns = board[0].length;

		return row >= 0 && row < numRows && column >= 0 && column < numColumns
				&& (board[row][column].getPiece() == null
						|| board[row][column].getPiece().getPlayerName() != this.getPlayerName());
	}

	public void setNumPieces(int numPieces) {
		Horse.horseCounter += numPieces;
	}

	public int getNumPiece() {
		return Horse.horseCounter;
	}

	public String getPieceImageName() {
		return "Horse_" + getPlayerName();
	}
}
