package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.xiangqi.Enums.Constant.InitPieceSetup.XiangQiBoard;

public class Canon extends Piece {
	public static List <int[]> checkValidMoves (int[] currentPosition) {

		List <int[]> possiblePositions = new ArrayList <>();

		// Check for valid moves horizontally to the left
		checkValidMovesInDirection(currentPosition, - 1, 0, possiblePositions);

		// Check for valid moves horizontally to the right
		checkValidMovesInDirection(currentPosition, 1, 0, possiblePositions);

		// Check for valid moves below vertically
		checkValidMovesInDirection(currentPosition, 0, - 1, possiblePositions);

		// Check for valid moves above vertically
		checkValidMovesInDirection(currentPosition, 0, 1, possiblePositions);

		return possiblePositions;
	}

	private static void checkValidMovesInDirection (int[] currentPosition, int dx, int dy, List <int[]> possiblePositions) {
		int isBlocked = 0;
		int x = currentPosition[0] + dx;
		int y = currentPosition[1] + dy;

		while (x >= 0 && x <= 8 && y >= 0 && y <= 9) {
			if (! Objects.equals(XiangQiBoard[y][x], "")) {
				isBlocked++;
			} else {
				if (isBlocked == 0) {
					possiblePositions.add(new int[] {x, y});
				}
			}

			if (isBlocked == 2) {
				possiblePositions.add(new int[] {x, y});
				break;
			}

			x += dx;
			y += dy;
		}
	}
}