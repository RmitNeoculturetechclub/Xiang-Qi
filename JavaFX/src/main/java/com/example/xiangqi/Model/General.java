package com.example.xiangqi.Model;

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

	// @Override
	// public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][]
	// board){
	// //TODO: Implement this method
	// return super.getAllPossibleMoves(currentPosition, board);
	// }

	public void setNumPieces(int numPieces) {
		General.generalCounter += numPieces;
	}

	public int getNumPiece() {
		return General.generalCounter;
	}

	public String getPieceImageName() {
		return "General_" + getPlayerName();
	}
}
