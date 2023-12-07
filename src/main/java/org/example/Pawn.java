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
 * Mateusz Sławomir Lach ( matlak, msl )
 * Damian Marciniak
 */
package org.example;

import java.awt.Image;
import java.util.ArrayList;


/**
 * Class to represent a pawn piece
 * Pawn can move only forvard and can beat only across
 * In first move pawn can move 2 sqares
 * pawn can be upgreade to rook, knight, bishop, Queen if it's in the
 * squares nearest the side where opponent is lockated
 * First move of pawn:
 *
|_|_|_|_|_|_|_|_|7
|_|_|_|_|_|_|_|_|6
|_|_|_|X|_|_|_|_|5
|_|_|_|X|_|_|_|_|4
|_|_|_|P|_|_|_|_|3
|_|_|_|_|_|_|_|_|2
|_|_|_|_|_|_|_|_|1
|_|_|_|_|_|_|_|_|0
0 1 2 3 4 5 6 7
 *
 * Move of a pawn:
 *
|_|_|_|_|_|_|_|_|7
|_|_|_|_|_|_|_|_|6
|_|_|_|_|_|_|_|_|5
|_|_|_|X|_|_|_|_|4
|_|_|_|P|_|_|_|_|3
|_|_|_|_|_|_|_|_|2
|_|_|_|_|_|_|_|_|1
|_|_|_|_|_|_|_|_|0
0 1 2 3 4 5 6 7
 * Beats with can take pawn:
 *
|_|_|_|_|_|_|_|_|7
|_|_|_|_|_|_|_|_|6
|_|_|_|_|_|_|_|_|5
|_|_|X|_|X|_|_|_|4
|_|_|_|P|_|_|_|_|3
|_|_|_|_|_|_|_|_|2
|_|_|_|_|_|_|_|_|1
|_|_|_|_|_|_|_|_|0
0 1 2 3 4 5 6 7
 */

public class Pawn extends Piece
{

    boolean crossedBorder;
    boolean down;
    public static short value = 1;

    Pawn(Chess3Board board, Player player)
    {

        super(board, player);
        this.down = false; // Check if pawn has made first move
        this.crossedBorder = false;
        this.board = board;
        this.player = player;//this.setImages("Pawn-W.png", "Pawn-B.png");
        this.symbol = "p";
    }

    /**
     * Annotation to superclass Piece changing pawns location
     * @return  ArrayList with new position of piece
     */

    @Override
    public ArrayList<Square> allMoves()
    {

        ArrayList<Square> list = new ArrayList<>();

        if(player.color == Player.colors.WHITE){
            Square squareAbove = board.squareAbove(this.square);
            if(squareAbove.isEmpty()){
                list.add(squareAbove);
                Square nextStep = board.squareAbove(squareAbove);
                if(nextStep.isEmpty()){
                    list.add(nextStep);
                }
            }
            Square leftFront = board.squareLeft(squareAbove);
            Square rightFront = board.squareRight(squareAbove);
            if(leftFront!=null && !leftFront.isEmpty()){
                list.add(leftFront);
            }
            if(rightFront!=null && !rightFront.isEmpty()){
                list.add(rightFront);
            }
        }
        else{
            if(player.color == Player.colors.RED && this.square.label.charAt(1)=='5')
                crossedBorder = true;
            if(player.color == Player.colors.BLACK && this.square.label.charAt(1)=='4')
                crossedBorder = true;

            if(crossedBorder){
                Square squareAbove = board.squareAbove(this.square);
                if(squareAbove.isEmpty()){
                    list.add(squareAbove);
                }
                Square leftFront = board.squareLeft(squareAbove);
                Square rightFront = board.squareRight(squareAbove);
                if(leftFront!=null && !leftFront.isEmpty()){
                    list.add(leftFront);
                }
                if(rightFront!=null && !rightFront.isEmpty()){
                    list.add(rightFront);
                }
            }
            else {
                Square squareBelow = board.squareBelow(this.square);
                if (squareBelow.isEmpty()) {
                    list.add(squareBelow);
                    Square nextStep = board.squareBelow(squareBelow);
                    if(nextStep.isEmpty()){
                        list.add(nextStep);
                    }
                }
                Square leftFront = board.squareLeft(squareBelow);
                Square rightFront = board.squareRight(squareBelow);
                if (leftFront != null && !leftFront.isEmpty()) {
                    list.add(leftFront);
                }
                if (rightFront != null && !rightFront.isEmpty()) {
                    list.add(rightFront);
                }
            }
        }

        return list;
    }

    void promote(Piece newPiece)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
