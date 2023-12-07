/*
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Authors:
 * Othman and Janakan
 */

package org.example;


import java.util.ArrayList;
import java.util.Iterator;

/**
Class to represent a piece (any kind) - this class should be extended to represent pawn, bishop etc.
 */
public abstract class Piece
{
    Player player;
    Chess3Board board; // <-- this relations isn't in class diagram, but it's necessary :/
    public Square square;
    String name;
    protected String symbol;

    Piece(Chess3Board board, Player player)
    {
        this.board = board;
        this.player = player;
    }
    /* Method to draw piece on chessboard
     * @graph : where to draw
     */


    /** method check if Piece can move to given square
     * @param square square where piece want to move (Square object)
     * @param allmoves  all moves which can piece do
     * */
    boolean canMove(Square square, ArrayList allmoves)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
        ArrayList moves = allmoves;
        for (Iterator it = moves.iterator(); it.hasNext();)
        {
            Square sq = (Square) it.next();//get next from iterator
            if (sq == square)
            {//if adress is the same
                return true; //piece canMove
            }
        }
        return false;//if not, piece cannot move
    }

    abstract public ArrayList allMoves();

    /**
     * @param x x position on chessboard
     * @param y  y position on chessboard
     * @return true if can move, false otherwise
     * */
    protected boolean checkPiece(int section, int x, int y)
    {
        if (board.sections[section].squares[x][y].piece != null
                && board.sections[section].squares[x][y].piece.name.equals("King"))
        {
            return false;
        }
        Piece piece = board.sections[section].squares[x][y].piece;
        if (piece == null || //if this square is empty
                piece.player != this.player) //or piece is another player
        {
            return true;
        }
        return false;
    }

    /** Method check if piece has other owner than calling piece
     * @param x x position on chessboard
     * @param y y position on chessboard
     * @return true if owner(player) is different
     * */
    protected boolean otherOwner(int section, int x, int y)
    {
        Square sq = board.sections[section].squares[x][y];
        if (sq.piece == null)
        {
            return false;
        }
        if (this.player != sq.piece.player)
        {
            return true;
        }
        return false;
    }

    public String getSymbol()
    {
        return this.symbol;
    }
}
