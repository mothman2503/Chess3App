package org.jchess;

public class Move {
    Square from;
    Square to;
    Piece movedPiece;
    Piece takenPiece = null;
    Piece promotedTo = null;

    Move(){}

    Move(Square from, Square to, Piece movedPiece, Piece takenPiece)
    {
        this.from = from;
        this.to = to;

        this.movedPiece = movedPiece;
        this.takenPiece = takenPiece;
    }

    Move(Square from, Square to, Piece movedPiece, Piece takenPiece, Piece promotedTo)
    {
        this.from = from;
        this.to = to;

        this.movedPiece = movedPiece;
        this.takenPiece = takenPiece;
        this.promotedTo = promotedTo;
    }
    public String toString(){
        String ret = "";
        if(movedPiece!=null){
            ret+= movedPiece.getSymbol();
        }
        ret+= from.toString() + "-";

        if(promotedTo!=null){
            ret+= promotedTo.getSymbol();
        }
        ret+= to.toString();
        return ret;
    }

}
