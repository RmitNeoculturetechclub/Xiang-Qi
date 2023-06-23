package com.example.xiangqi.Model;

import java.util.List;

public class General extends Piece {
	static int generalCounter = 0;
	Boolean isCheck = false;
	Boolean isCheckMate = false;
	public General (String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public General () {
	}

	@Override
	public List <int[]> getAllPossibleMoves (Cell[][] GlobalBoard) {
		//TODO: Implement this method
		return super.getAllPossibleMoves(GlobalBoard);
	}

	public void setNumPieces (int numPieces) {
		General.generalCounter += numPieces;
	}

	public int getNumPiece() {
		return General.generalCounter;
	}
}
