package com.example.xiangqi.Model;

import java.util.List;

public class Soldier extends Piece {
	static int soldierCounter =10;
	public Soldier (String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public Soldier () {
	}

	@Override
	public List <int[]> getAllPossibleMoves (Cell[][] GlobalBoard) {
		//TODO: Implement this method
		return super.getAllPossibleMoves(GlobalBoard);
	}
}