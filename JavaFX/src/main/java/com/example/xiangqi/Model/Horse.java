package com.example.xiangqi.Model;

import java.util.List;

public class Horse extends Piece{
	static int horseCounter = 0;
	public Horse (String id, String player) {
		super(id, player);
	}

	public Horse () {
	}

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board){
		//TODO: Implement this method
		return super.getAllPossibleMoves(currentPosition, board);
	}

	public void setNumPieces (int numPieces) {
		Horse.horseCounter += numPieces;
	}

	public int getNumPiece() {
		return Horse.horseCounter;
	}

	public String getPieceImageName() {
        return "Horse_" + getPlayerName();
    }
}
