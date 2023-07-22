package com.example.xiangqi.Model;

import com.example.xiangqi.Enums.Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Piece {

	private boolean isAlive;
	private String id;
	private Player player;

	public Piece() {
		this.isAlive = true;
	}

	public void setPlayer(String player) {
		this.player = Player.valueOf(player);
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

	public String getPlayerName() {
		return player.name();
	}

	public String getPieceName() {
		String className = this.getClass().getSimpleName();
		String[] array = className.split("_");
		// System.out.println(array[0]);
		return array[0];
	}

}