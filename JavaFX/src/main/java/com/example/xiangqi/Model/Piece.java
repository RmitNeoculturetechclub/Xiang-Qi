package com.example.xiangqi.Model;

import com.example.xiangqi.Enums.Model.PieceName;
import com.example.xiangqi.Enums.Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Piece {
	private boolean isAlive;
	private String id;
	private PieceName pieceName;
	private Player player;

	public boolean isAlive () {
		return isAlive;
	}

	public void setAlive (boolean alive) {
		isAlive = alive;
	}

	public String getId () {
		return id;
	}

	public void setId (String id) {
		this.id = id;
	}

	public PieceName getPieceName () {
		return pieceName;
	}

	public void setPieceName (PieceName pieceName) {
		this.pieceName = pieceName;
	}

	public Player getPlayer () {
		return player;
	}

	public void setPlayer (Player player) {
		this.player = player;
	}

	public Piece (String id, String player, String pieceName) {
		this.isAlive = true;
		this.id = id;
		// Todo: Change the string to player and piece name
		// Piece name is init in the InitializeManager
		this.player = Player.valueOf("Black");
		this.pieceName = PieceName.valueOf("Soldier");
	}

	public Piece () {
		this.isAlive = true;
	}

	public void pieceMovement () {
	}

	public String getPieceImageName () {
		return pieceName.name() + '_' + player.name();
	}

	public List <int[]> getAllPossibleMoves (Cell[][] GlobalBoard) {
        /*
        Change the list name
        Change the new int to find all possible positions
        Check the condition to find suitable move, and eliminate the occupied piece
         */
		List <int[]> example = new ArrayList <>();
		switch (this.pieceName) {
			case Soldier -> {
                /*
                Soldier movement here
                 */
				break;
			}

			case Canon -> {
				// Check if the piece is alive
				if (! this.isAlive) {
					break;
				}

				//get current position of the pieces (checking their names)
				int[] currentPos = new int[2];
				for (int i = 0; i < GlobalBoard.length; i++) {
					for (int j = 0; j < GlobalBoard[i].length; j++) {
						if (GlobalBoard[i][j].getPiece().getPieceName() == this.pieceName) {
							currentPos[0] = i;
							currentPos[1] = j;
						}

					}
				}

				// Assign the possible moves to the list
				example = Canon.checkValidMoves(currentPos);
				break;
			}
			default -> {
				break;
			}
		}
		return example;
	}
}
