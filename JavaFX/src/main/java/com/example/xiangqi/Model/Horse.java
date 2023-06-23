package com.example.xiangqi.Model;

import java.util.List;

public class Horse extends Piece{
	static int horseCounter = 0;
	public Horse (String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public Horse () {
	}

	@Override
	public List <int[]> getAllPossibleMoves (Cell[][] GlobalBoard) {
		//TODO: Implement this method
		return super.getAllPossibleMoves(GlobalBoard);
	}

	public void setNumPieces (int numPieces) {
		Horse.horseCounter += numPieces;
	}

	public int getNumPiece() {
		return Horse.horseCounter;
	}
}
