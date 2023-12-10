package org.jchess;

public class Main {
    public static void main(String[] args) {
        Chess3Board board = new Chess3Board();
        board.findSquare("e4").setPiece(new Bishop(board, new Player("Ahad", "RED")));
        board.print();
        Moves moves = new Moves(new Game());
        Move move = moves.moveFromString("d4-d5");


    }
}