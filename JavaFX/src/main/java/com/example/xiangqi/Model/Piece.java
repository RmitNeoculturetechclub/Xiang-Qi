package com.example.xiangqi.Model;

import com.example.xiangqi.Enums.Model.PieceName;
import com.example.xiangqi.Enums.Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    private boolean isAlive;
    private String id;
    protected PieceName pieceName;
    private Player player;

    public Piece(String id, String player, String pieceName) {
        this.isAlive = true;
        this.id = id;
        this.player = Player.valueOf(player);
        this.pieceName = PieceName.valueOf(pieceName);
    }

    public Piece() {
        this.isAlive = true;
    }

    public void pieceMovement(){}

	public PieceName getPieceName () {
		return pieceName;
	}

    public String getPieceImageName() {
        return pieceName.name() + '_' + player.name();
    }

    public List<int[]> getAllPossibleMoves(Cell[][] GlobalBoard){

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

				Canon canon = (Canon) this;
				// Assign the possible moves to the list
				example = canon.getAllPossibleMoves(GlobalBoard);
				break;
			}
			default -> {
				break;
			}
		}
		return example;
	}

	public String getId () {
		return id;
	}

	public void setId (String id) {
		this.id = id;
	}

	public String getPlayerName() {
		return player.name();
	}
}
