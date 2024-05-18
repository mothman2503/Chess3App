package org.jchess;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Chess3Board board = new Chess3Board();
        board.findSquare("g10").setPiece(new Knight(board,new Player()));

        board.print();

        ArrayList list=board.findSquare("g10").piece.allMoves();
        System.out.println(list);



    }
}