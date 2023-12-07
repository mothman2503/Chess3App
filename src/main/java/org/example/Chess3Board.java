package org.example;

import java.util.ArrayList;

public class Chess3Board {
    Chess3Section[] sections;

    public Chess3Board(){
     sections = new Chess3Section[6];

        sections[0] = new Chess3Section(
                new String[]{"4", "3", "2", "1"},
                new String[]{"D", "C", "B", "A"},
                this
        );

        sections[1] = new Chess3Section(
                new String[]{"E", "F", "G", "H"},
                new String[]{"4", "3", "2", "1"},
                this
        );

        sections[2] = new Chess3Section(
                new String[]{"9", "10", "11", "12"},
                new String[]{"E", "F", "G", "H"},
                this
        );

        sections[3] = new Chess3Section(
                new String[]{"I", "J", "K", "L"},
                new String[]{"9", "10", "11", "12"},
                this
        );

        sections[4] = new Chess3Section(
                new String[]{"5", "6", "7", "8"},
                new String[]{"I", "J", "K", "L"},
                this
        );

        sections[5] = new Chess3Section(
                new String[]{"D", "C", "B", "A"},
                new String[]{"5", "6", "7", "8"},
                this
        );
    }

    public boolean isSquareEmpty(String label)
    {
        if(this.findSquare(label) == null)
            return false;
        return this.findSquare(label).isEmpty();
    }


    public Square findSquare(String label){
        for(int i = 0; i<6; i++){
            if(this.sections[i].findSquare(label)!=null){
                return this.sections[i].findSquare(label);
            }
        }
        return null;
    }

    public void setPieces(Player player){
        int section1/*Queen's Side*/, section2/*King's Side*/;
        if(player.color == Player.colors.RED){
            section1 = 0;
            section2 = 1;
        }
        else if(player.color == Player.colors.WHITE){
            section1 = 2;
            section2 = 3;
        }
        else{
            section1 = 4;
            section2 = 5;
        }

        //PAWNS
        for(int i = 0; i<4;i++){
            this.sections[section1].squares[i][2].setPiece(new Pawn(this, player));
            this.sections[section2].squares[2][i].setPiece(new Pawn(this, player));
        }

        //ROOKS
        this.sections[section1].squares[3][3].setPiece(new Rook(this, player));
        this.sections[section2].squares[3][3].setPiece(new Rook(this, player));

        //KNIGHTS
        this.sections[section1].squares[2][3].setPiece(new Knight(this, player));
        this.sections[section2].squares[3][2].setPiece(new Knight(this, player));

        //BISHOPS
        this.sections[section1].squares[1][3].setPiece(new Bishop(this, player));
        this.sections[section2].squares[3][1].setPiece(new Bishop(this, player));

        //QUEEN
        this.sections[section1].squares[0][3].setPiece(new Queen(this, player));

    }

    public void print(){
        for(int i = 0; i<6; i++){
            System.out.println(sections[i]);
            System.out.println("\n\n");
        }
    }

    public Square squareAbove(Square square){
        Square squareAbove = null;
        String alpha = "";
        alpha += square.label.charAt(0);
        int height = Integer.parseInt(square.label.substring(1));
        if(height == 8) {
            return null;
        }
        int newHeight = height;
        int change = (height>=5 && height<=8)?1:-1;
        if(alpha.charAt(0)>='I' && height==9)
            return this.findSquare(alpha +"5");

        while(newHeight>0 && newHeight<13){
            newHeight += change;
            if(this.findSquare(alpha+Integer.toString(newHeight))!=null){
                squareAbove = this.findSquare(alpha+Integer.toString(newHeight));
                break;
            }
        }
        return squareAbove;
    }

    public Square squareBelow(Square square){
        Square squareBelow = null;
        String alpha = "";
        alpha += square.label.charAt(0);
        int height = Integer.parseInt(square.label.substring(1));
        if(height == 12)
            return null;
        int newHeight = height;
        int change = (alpha.charAt(0)>='I' && height<9)?-1:1;
        if(alpha.charAt(0)<='D' && height>4)
            change = -1;

        while(newHeight>0 && newHeight<13){
            newHeight += change;
            if(newHeight == 4 && alpha.charAt(0)>='I'){
                newHeight = 9;
            }
            if(this.findSquare(alpha+Integer.toString(newHeight))!=null){
                squareBelow = this.findSquare(alpha+Integer.toString(newHeight));
                break;
            }
        }
        return squareBelow;
    }

    public Square squareLeft(Square square){
        Square squareLeft = null;
        char alpha = square.label.charAt(0);
        String height = square.label.substring(1);
        int change = (Integer.parseInt(height)<5)?1:-1;
        if(Integer.parseInt(height)>8 && alpha == 'I')
            return this.findSquare('E'+height);
        while(alpha>='A' && alpha<'M'){
            alpha = (char) (alpha+change);
            if(this.findSquare(alpha+height)!=null){
                squareLeft = this.findSquare(alpha+height);
                break;
            }
        }
        return squareLeft;
    }

    public Square squareRight(Square square){
        Square squareRight = null;
        char alpha = square.label.charAt(0);
        String height = square.label.substring(1);
        int change = (Integer.parseInt(height)<5)?-1:1;
        if(Integer.parseInt(height)>8 && alpha == 'E')
            return this.findSquare('I'+height);
        while(alpha>='A' && alpha<'M'){
            alpha = (char) (alpha+change);
            if(this.findSquare(alpha+height)!=null){
                squareRight = this.findSquare(alpha+height);
                break;
            }
        }
        return squareRight;
    }

    public ArrayList<Square> topLeftDiagonal(Square square){
        ArrayList<Square> list = new ArrayList<>();
        list.add(square);
        if(square.label.equals("I9")){
            list.addAll(this.topLeftDiagonal(this.findSquare("E4")));
            list.addAll(this.topLeftDiagonal(this.findSquare("D5")));
            return list;
        }
        Square above = this.squareAbove(square);
        if(above!=null) {
            Square topLeft = this.squareLeft(above);
            if(topLeft!= null){
                list.addAll(this.topLeftDiagonal(topLeft));
            }
        }
        return list;
    }

    public ArrayList<Square> bottomLeftDiagonal(Square square){
        ArrayList<Square> list = new ArrayList<>();
        list.add(square);
        if(square.label.equals("D4")){
            list.addAll(this.bottomLeftDiagonal(this.findSquare("E9")));
            list.addAll(this.topRightDiagonal(this.findSquare("I5")));
            return list;
        }
        if(square.label.equals("I5")){
            list.addAll(this.bottomLeftDiagonal(this.findSquare("E9")));
            list.addAll(this.topRightDiagonal(this.findSquare("D4")));
            return list;
        }
        Square below = this.squareBelow(square);
        if(below!=null) {
            if(square.label.charAt(0)<='D'){
                Square bottomLeft = this.squareRight(below);
                if(bottomLeft!=null)
                    list.addAll(this.topRightDiagonal(bottomLeft));
                return list;
            }
            Square bottomLeft = this.squareLeft(below);
            if(bottomLeft!= null){
                list.addAll(this.topLeftDiagonal(bottomLeft));
            }
        }
        return list;
    }

    public ArrayList<Square> topRightDiagonal(Square square){
        ArrayList<Square> list = new ArrayList<>();
        list.add(square);
        if(square.label.equals("E9")){
            list.addAll(this.topRightDiagonal(this.findSquare("D4")));
            list.addAll(this.topRightDiagonal(this.findSquare("I5")));
            return list;
        }
        Square above = this.squareAbove(square);
        if(above!=null) {
            Square topRight = this.squareRight(above);
            if(topRight!= null){
                list.addAll(this.topLeftDiagonal(topRight));
            }
        }
        return list;
    }

    public ArrayList<Square> bottomRightDiagonal(Square square){
        ArrayList<Square> list = new ArrayList<>();
        list.add(square);
        if(square.label.equals("E4")){
            list.addAll(this.bottomRightDiagonal(this.findSquare("I9")));
            list.addAll(this.topLeftDiagonal(this.findSquare("D5")));
            return list;
        }
        if(square.label.equals("D5")){
            list.addAll(this.bottomRightDiagonal(this.findSquare("I9")));
            list.addAll(this.topLeftDiagonal(this.findSquare("E4")));
            return list;
        }
        Square below = this.squareBelow(square);
        if(below!=null) {
            if(square.label.charAt(0)<='D'){
                Square bottomRight = this.squareLeft(below);
                if(bottomRight!=null)
                    list.addAll(this.topLeftDiagonal(bottomRight));
                return list;
            }
            Square bottomRight = this.squareRight(below);
            if(bottomRight!= null){
                list.addAll(this.topLeftDiagonal(bottomRight));
            }
        }
        return list;
    }


}

