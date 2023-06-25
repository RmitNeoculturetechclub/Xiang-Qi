package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

public class Canon extends Piece {
	static int canonCounter = 0;

	public Canon(String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public Canon() {
	}

	public List<int[]> getAllPossibleMoves(Cell[][] GlobalBoard) {
		// get current position of the pieces (checking their names)
		int[] currentPosition = new int[2];
		for (int i = 0; i < GlobalBoard.length; i++) {
			for (int j = 0; j < GlobalBoard[i].length; j++) {
				if (GlobalBoard[i][j].getPiece().getPieceName() == this.getPieceName()) {
					currentPosition[0] = i;
					currentPosition[1] = j;
				}

			}
		}
		List<int[]> possiblePositions = new ArrayList<>();

		// Check for valid moves horizontally to the left
		checkValidMovesInDirection(currentPosition, -1, 0, possiblePositions, GlobalBoard);

		// Check for valid moves horizontally to the right
		checkValidMovesInDirection(currentPosition, 1, 0, possiblePositions, GlobalBoard);

		// Check for valid moves below vertically
		checkValidMovesInDirection(currentPosition, 0, -1, possiblePositions, GlobalBoard);

		// Check for valid moves above vertically
		checkValidMovesInDirection(currentPosition, 0, 1, possiblePositions, GlobalBoard);

		return possiblePositions;
	}

	private void checkValidMovesInDirection(int[] currentPosition, int dx, int dy, List<int[]> possiblePositions,
			Cell[][] GlobalBoard) {
		int isBlocked = 0;
		int x = currentPosition[0] + dx;
		int y = currentPosition[1] + dy;

		while (x >= 0 && x <= 8 && y >= 0 && y <= 9) {
			if (GlobalBoard[x][y].getPiece() != null) {
				isBlocked++;
			} else {
				if (isBlocked == 0) {
					possiblePositions.add(new int[] { x, y });
				}
			}

			if (isBlocked == 2) {
				if (GlobalBoard[x][y].getPiece().getPlayerName() != this.getPlayerName()) {
					possiblePositions.add(new int[] { x, y });
				}
				break;
			}

			x += dx;
			y += dy;
		}
	}

	public void setNumPieces(int numPiece) {
		Canon.canonCounter += numPiece;
	}

	public int getNumPiece() {
		return Canon.canonCounter;
	}
}
