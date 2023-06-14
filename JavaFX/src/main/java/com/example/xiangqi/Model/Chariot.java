package com.example.xiangqi.Model;

import java.util.List;

public class Chariot extends Piece{
	public Chariot (String id, String player, String pieceName) {
		super(id, player, pieceName);
	}

	public Chariot () {
	}

	@Override
	public List <int[]> getAllPossibleMoves (Cell[][] GlobalBoard) {
		//TODO: Implement this method
		return super.getAllPossibleMoves(GlobalBoard);
	}
}
