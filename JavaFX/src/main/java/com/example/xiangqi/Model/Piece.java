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

    public Piece(String id, String player, String pieceName) {
        this.isAlive = true;
        this.id = id;
        // Todo: Change the string to player and piece name
        // Piece name is init in the InitializeManager
        this.player = Player.valueOf(player);
        this.pieceName = PieceName.valueOf(pieceName);
    }

    public Piece() {
        this.isAlive = true;
    }

    public void pieceMovement(){}

    public String getPieceImageName() {
        return pieceName.name() + '_' + player.name();
    }

    public List<int[]> getAllPossibleMoves(Cell[][] GlobalBoard){
        /*
        Change the list name
        Change the new int to find all possible positions
        Check the condition to find suitable move, and eliminate the occupied piece
         */
        List<int[]> example = new ArrayList<>();
        switch (this.pieceName){
            case Soldier -> {
                /*
                Soldier movement here
                 */
                break;
            }

            case Canon -> {
                /*
                Cannon here
                 */
                break;
            }
            default -> {
                break;
            }
        }
        example.add(new int[]{0, 0});
        return example;
    }
}
