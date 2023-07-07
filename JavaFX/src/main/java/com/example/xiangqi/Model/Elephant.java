package com.example.xiangqi.Model;

import java.util.List;

public class Elephant extends Piece {

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		// TODO: Implement this method
		return super.getAllPossibleMoves(currentPosition, board);
	}

	// public String getPieceImageName() {
	// return "Elephant_" + getPlayerName();
	// }

	public String getPieceName() {
		String className = this.getClass().getSimpleName();
		String[] array = className.split("_");
		return array[0];
	}
}
