package com.example.xiangqi.Model;

import java.util.List;

public class Chariot extends Piece{
	static int chariotCounter = 0;
	public Chariot (String id, String player) {
		super(id, player);
	}

	public Chariot () {
	}

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board){
		//TODO: Implement this method
		return super.getAllPossibleMoves(currentPosition, board);
	}

	public void setNumPieces (int numPieces) {
		Chariot.chariotCounter += numPieces;
	}

	public int getNumPiece() {
		return Chariot.chariotCounter;
	}

	public String getPieceImageName() {
        return "Chariot_" + getPlayerName();
    }
}
