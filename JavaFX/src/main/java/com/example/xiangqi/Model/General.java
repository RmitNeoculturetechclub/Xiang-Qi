package com.example.xiangqi.Model;

import java.util.List;

public class General extends Piece {
	static int generalCounter = 2;
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
}