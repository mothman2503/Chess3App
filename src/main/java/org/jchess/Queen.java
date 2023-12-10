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
package org.jchess;

import java.util.ArrayList;

/**
 * Class to represent a queen piece
 * Queen can move almost in every way:
 * |_|_|_|X|_|_|_|X|7
    |X|_|_|X|_|_|X|_|6
    |_|X|_|X|_|X|_|_|5
    |_|_|X|X|x|_|_|_|4
    |X|X|X|Q|X|X|X|X|3
    |_|_|X|X|X|_|_|_|2
    |_|X|_|X|_|X|_|_|1
    |X|_|_|X|_|_|X|_|0
    0 1 2 3 4 5 6 7
 */
public class Queen extends Piece
{
    public static short value = 9;
    Queen(Chess3Board board, Player player)
    {
        super(board, player);
        this.board = board;
        this.player = player;//call initializer of super type: Piece
        //this.setImages("Queen-W.png", "Queen-B.png");
        this.symbol = "Q";
    }

    /**
     * Annotation to superclass Piece changing pawns location
     * @return  ArrayList with new possition of piece
     */
    @Override
    public ArrayList<Square> allMoves()
    {
        ArrayList<Square> list = new ArrayList<>();

        //-------------- ROOK BEHAVIOUR ----------------
        boolean borderWasCrossed = false;

        Square checkingSquare = board.squareAbove(this.square);
        while(checkingSquare!=null){
            if (checkingSquare.isEmpty()){
                list.add(checkingSquare);
                checkingSquare = board.squareAbove(checkingSquare);
            }
            else{
                break;
            }
        }
        checkingSquare = board.squareBelow(this.square);

        while(checkingSquare!=null){
            if (checkingSquare.isEmpty()){
                list.add(checkingSquare);
                if(borderWasCrossed){
                    checkingSquare = board.squareAbove(checkingSquare);
                }
                else {
                    if (checkingSquare.label.charAt(0) <= 'D' && (checkingSquare.label.charAt(1) == '4' || checkingSquare.label.charAt(1) == '5')) {
                        borderWasCrossed = true;
                        continue;
                    }
                    checkingSquare = board.squareBelow(checkingSquare);
                }
            }
            else{
                break;
            }
        }
        checkingSquare = board.squareLeft(this.square);

        while(checkingSquare!=null){
            if (checkingSquare.isEmpty()){
                list.add(checkingSquare);
                checkingSquare = board.squareLeft(checkingSquare);
            }
            else{
                break;
            }
        }
        checkingSquare = board.squareRight(this.square);

        while(checkingSquare!=null){
            if (checkingSquare.isEmpty()){
                list.add(checkingSquare);
                checkingSquare = board.squareRight(checkingSquare);
            }
            else{
                break;
            }
        }

        //------------- BISHOP BEHAVIOUR --------------
        list.addAll(this.board.topRightDiagonal(this.square));
        list.addAll(this.board.topLeftDiagonal(this.square));
        list.addAll(this.board.bottomRightDiagonal(this.square));
        list.addAll(this.board.bottomLeftDiagonal(this.square));

        return list;
    }
}
