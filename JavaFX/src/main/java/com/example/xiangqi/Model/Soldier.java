package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

public class Soldier extends Piece {
	static int soldierCounter = 0;

	public Soldier(String id, String player) {
		super(id, player);
	}

	public Soldier() {
	}

	@Override
	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		List<int[]> possiblePositions = new ArrayList<>();

		// TODO: Check if the soldier has crossed the river

		// TODO: Add the one step forward move based on the player's side

		// TODO: Add the sideways moves if the soldier has crossed the river

		return possiblePositions;
	}

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
