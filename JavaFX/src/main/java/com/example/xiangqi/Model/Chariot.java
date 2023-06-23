package com.example.xiangqi.Model;

import java.util.List;

public class Chariot extends Piece{
	static int chariotCounter = 0;
	public Chariot (String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public Chariot () {
	}

	@Override
	public List <int[]> getAllPossibleMoves (Cell[][] GlobalBoard) {
		//TODO: Implement this method
		return super.getAllPossibleMoves(GlobalBoard);
	}

	public void setNumPieces (int numPieces) {
		Chariot.chariotCounter += numPieces;
	}

	public int getNumPiece() {
		return Chariot.chariotCounter;
	}
}
