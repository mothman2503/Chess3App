package org.jchess;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Stack;

public class Moves{
    private ArrayList<String> moves = new ArrayList<>();

    private Game game;
    //protected Stack<Move> moveBackStack = new Stack<>();
    //protected Stack<Move> moveForwardStack = new Stack<>();

    public Moves(Game game){
        this.game = game;
    }

    public void makeMove(Move move){
        move.to.setPiece(move.from.piece);
        move.from.piece = null;
    }

    public void add(Move move){
        moves.add(move.toString());
    }

    public Move moveFromString(String moveString){
        Move move = new Move();
        move.to = new Square(moveString.substring(moveString.indexOf('-')+1));
        move.from =  new Square(moveString.substring(moveString.indexOf('-')-2, moveString.indexOf('-')));
        if(Character.isUpperCase(moveString.charAt(0))){

        }
        else {
            move.movedPiece = new Pawn(this.game.board, new Player());
        }
        return move;
    }








}
