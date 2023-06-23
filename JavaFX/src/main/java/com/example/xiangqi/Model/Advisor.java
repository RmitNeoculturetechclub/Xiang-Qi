package com.example.xiangqi.Model;

import java.util.List;

public class Advisor extends Piece{
	static int advisorCounter = 0;
	public Advisor (String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public Advisor () {
	}

	@Override
	public List <int[]> getAllPossibleMoves (Cell[][] GlobalBoard) {
		//TODO: Implement this method
		return super.getAllPossibleMoves(GlobalBoard);
	}

	public void setNumPieces (int numPieces) {
		Advisor.advisorCounter = numPieces;
	}

	public void setNumPiece(int numPiece) {
		Advisor.advisorCounter += numPiece;
	}
	public int getNumPiece() {
		return Advisor.advisorCounter;
	}
}
