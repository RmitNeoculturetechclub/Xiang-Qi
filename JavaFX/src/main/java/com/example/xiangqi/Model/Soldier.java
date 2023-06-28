package com.example.xiangqi.Model;

import java.util.List;

public class Soldier extends Piece {
	static int soldierCounter = 0;

	public Soldier(String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public Soldier() {
	}

	// @Override
	// public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board)
	// {
	// // TODO: Implement this method
	// return super.getAllPossibleMoves(currentPosition, board);
	// }

	public void setNumPieces(int numPieces) {
		Soldier.soldierCounter += numPieces;
	}

	public int getNumPiece() {
		return Soldier.soldierCounter;
	}

	public String getPieceImageName() {
		return "Soldier_" + getPlayerName();
	}
}
