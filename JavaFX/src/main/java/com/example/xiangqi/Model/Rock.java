package com.example.xiangqi.Model;

import java.util.List;

public class Rock extends Piece{
	public Rock (String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public Rock () {
	}

	@Override
	public List <int[]> getAllPossibleMoves (Cell[][] GlobalBoard) {
		//TODO: Implement this method
		return super.getAllPossibleMoves(GlobalBoard);
	}
}
