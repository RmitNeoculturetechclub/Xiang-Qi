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

		int[][] facingDirections = { { 1, 0 }, { -1, 0 } };
		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		if (isFacing(x, y, board)) {
			for (int[] direction : facingDirections) {
				int row = x + direction[0];
				int col = y + direction[1];

				if (isValidMove(row, col, board, currentPlayer)) {
					possiblePositions.add(new int[] { row, col });
				}
			}
		} else {
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

	private boolean isFacing(int x, int y, Cell[][] board) {
		System.out.println("x, y: " + x + y);
		for (int i = 0; i < 10; i++) {
			if (i == x) {
				System.out.println("same i");
				continue;
			}
			System.out.println("y, i " + y + " " + i);
			if (board[i][y].getPiece() != null) { // TODO: getPiece -> allnull
				// && board[y][i].getPiece().getPieceName().equals("General")
				System.out.println(board[i][y].getPiece().getPieceName());
				System.out.println("isFacing true");

				return true;
			}
		}
		System.out.println("isFacing false");
		return false;
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

	public String getPieceName() {
		String className = this.getClass().getSimpleName();
		String[] array = className.split("_");
		return array[0];
	}

}
