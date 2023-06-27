package com.example.xiangqi.Model;

import java.util.List;

public class Advisor extends Piece{
	static int advisorCounter = 0;
	public Advisor (String id, String player) {
		super(id, player);
	}

	public Advisor () {
	}

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board){
		//TODO: Implement this method
		return super.getAllPossibleMoves(currentPosition, board);
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

	public String getPieceImageName() {
        return "Advisor_" + getPlayerName();
    }
}
