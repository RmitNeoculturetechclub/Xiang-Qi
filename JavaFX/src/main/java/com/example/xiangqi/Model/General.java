package com.example.xiangqi.Model;

import java.util.List;

public class General extends Piece {
	Boolean isCheck = false;
	Boolean isCheckMate = false;

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		// TODO: Implement this method
		return super.getAllPossibleMoves(currentPosition, board);
	}

	public String getPieceImageName() {
		return "General_" + getPlayerName();
	}
}
