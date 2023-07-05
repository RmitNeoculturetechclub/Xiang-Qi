package com.example.xiangqi.Model;

import java.util.ArrayList;
import java.util.List;

import com.example.xiangqi.Enums.Model.PieceName;
import com.example.xiangqi.Enums.Model.Player;

public class Piece {

	private boolean isAlive;
	private String id;
	private Player player;

	public Piece(String id, String player) {
		this.isAlive = true;
		this.id = id;
		this.player = Player.valueOf(player);
	}

	public Piece() {
		this.isAlive = true;
	}

	public void pieceMovement() {
	}

	public String getPieceImageName() {
		return "Soldier_" + getPlayerName();
	}

	public List<int[]> getAllPossibleMoves(int[] currentPosition, Cell[][] board) {
		/*
		 * Change the list name
		 * Change the new int to find all possible positions
		 * Check the condition to find suitable move, and eliminate the occupied piece
		 */
		List<int[]> example = new ArrayList<>();
		return example;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlayerName() {
		return player.name();
	}

}