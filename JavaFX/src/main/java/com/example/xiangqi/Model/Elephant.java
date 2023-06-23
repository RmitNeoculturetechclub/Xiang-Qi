package com.example.xiangqi.Model;

import java.util.List;

public class Elephant extends Piece{
	static int elephantCounter = 0;
	public Elephant (String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public Elephant () {
	}

	@Override
	public List <int[]> getAllPossibleMoves (Cell[][] GlobalBoard) {
		//TODO: Implement this method
		return super.getAllPossibleMoves(GlobalBoard);
	}

	public void setNumPieces (int numPieces) {
		Elephant.elephantCounter += numPieces;
	}

	public int getNumPiece() {
		return Elephant.elephantCounter;
	}
}
