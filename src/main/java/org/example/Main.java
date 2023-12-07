package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Chess3Board board = new Chess3Board();
        board.findSquare("e4").setPiece(new Bishop(board, new Player("Ahad", "RED")));
        board.print();
        ArrayList list = board.findSquare("e4").piece.allMoves();
        System.out.println(list);
        System.out.println(board.squareLeft(board.findSquare("e9")));
        System.out.println(board.squareRight(board.findSquare("e3")));
    }
}