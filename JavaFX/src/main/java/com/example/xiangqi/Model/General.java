package com.example.xiangqi.Model;

import java.util.List;

public class General extends Piece {
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
