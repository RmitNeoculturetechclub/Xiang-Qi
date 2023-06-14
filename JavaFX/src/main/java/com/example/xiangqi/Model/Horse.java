package com.example.xiangqi.Model;

import java.util.List;

public class Horse extends Piece{
	public Horse (String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public Horse () {
	}

	@Override
	public List <int[]> getAllPossibleMoves (Cell[][] GlobalBoard) {
		//TODO: Implement this method
		return super.getAllPossibleMoves(GlobalBoard);
	}
}
