package com.example.xiangqi.Model;

import java.util.List;

public class Chariot extends Piece {

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		// TODO: Implement this method
		return super.getAllPossibleMoves(currentPosition, board);
	}

	public String getPieceImageName() {
		return "Chariot_" + getPlayerName();
	}
}
