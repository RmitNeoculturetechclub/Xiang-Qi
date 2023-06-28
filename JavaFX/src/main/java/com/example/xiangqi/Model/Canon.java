package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Canon extends Piece {
	static int canonCounter = 0;

	public Canon (String id, String player) {
		super(id, player);
	}

	public Canon () {
	}

	public List <int[]> getAllPossibleMoves (int[] currentPosition, Cell[][] board) {

		List <int[]> possiblePositions = new ArrayList <>();

		// Check for valid moves up vertically
		checkValidMovesInDirection(currentPosition, - 1, 0, possiblePositions, board);
		// Check for valid moves down vertically
		checkValidMovesInDirection(currentPosition, 1, 0, possiblePositions, board);

		// Check for valid moves horizontally to the right
		checkValidMovesInDirection(currentPosition, 0, - 1, possiblePositions, board);


		// Check for valid moves horizontally to the left
		checkValidMovesInDirection(currentPosition, 0, 1, possiblePositions, board);

		return possiblePositions;
	}

	private void checkValidMovesInDirection (int[] currentPosition, int dx, int dy, List <int[]> possiblePositions,
											 Cell[][] board) {
		int isBlocked = 0;
		int x = currentPosition[0] + dx;
		int y = currentPosition[1] + dy;

		while (x >= 0 && x <= 9 && y >= 0 && y <= 8) {
			if (board[x][y].getPiece() != null) {
				isBlocked++;
			} else {
				if (isBlocked == 0) {
					possiblePositions.add(new int[] {x, y});
				}
			}

			if (isBlocked == 2) {
				if (! Objects.equals(board[x][y].getPiece().getPlayerName(), this.getPlayerName())) {
					possiblePositions.add(new int[] {x, y});
				}
				break;
			}

			x += dx;
			y += dy;
		}
	}

	public void setNumPieces (int numPiece) {
		Canon.canonCounter += numPiece;
	}

	public int getNumPiece () {
		return Canon.canonCounter;
	}

	public String getPieceImageName () {
		return "Canon_" + getPlayerName();
	}
}