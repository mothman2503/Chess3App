package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Chess3Board n =  new Chess3Board();

        n.findSquare("A6").setPiece(new Bishop(n, new Player()));
        n.print();
        ArrayList list  = n.findSquare("A6").piece.allMoves();
        System.out.println(list);

    }
}