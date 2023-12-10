package org.jchess;

import java.util.ArrayList;

public class Chess3Board {
    Chess3Section[] sections;

    public Chess3Board(){
     sections = new Chess3Section[6];

        sections[0] = new Chess3Section(
                new String[]{"4", "3", "2", "1"},
                new String[]{"d", "c", "b", "a"},
                this
        );

        sections[1] = new Chess3Section(
                new String[]{"e", "f", "g", "h"},
                new String[]{"4", "3", "2", "1"},
                this
        );

        sections[2] = new Chess3Section(
                new String[]{"9", "10", "11", "12"},
                new String[]{"e", "f", "g", "h"},
                this
        );

        sections[3] = new Chess3Section(
                new String[]{"i", "j", "k", "l"},
                new String[]{"9", "10", "11", "12"},
                this
        );

        sections[4] = new Chess3Section(
                new String[]{"5", "6", "7", "8"},
                new String[]{"i", "j", "k", "l"},
                this
        );

        sections[5] = new Chess3Section(
                new String[]{"d", "c", "b", "a"},
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
        if(player.color == Player.colors.BLACK){
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
            System.out.println("\n");
        }
    }

    public Square squareAbove(Square square){
        int height = Integer.parseInt(square.label.substring(1));
        if(height == 1 || height == 8)
            return  null;
        Square squareAbove = null;
        String alpha = "";
        alpha += square.label.charAt(0);
        int newHeight = height;
        int change = (height>=5 && height<=8)?1:-1;
        if(alpha.charAt(0)>='i' && height==9)
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
        int height = Integer.parseInt(square.label.substring(1));
        if(height==12)
            return  null;
        Square squareBelow = null;
        String alpha = "";
        alpha += square.label.charAt(0);
        int newHeight = height;
        int change = (alpha.charAt(0)>='i' && height<9)?-1:1;
        if(alpha.charAt(0)<='d' && height>4)
            change = -1;

        while(newHeight>0 && newHeight<13){
            newHeight += change;
            if(newHeight == 4 && alpha.charAt(0)>='i'){
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
        if(square.label.charAt(0) == 'h')
            return null;
        if(square.label.charAt(0) == 'a' && square.label.charAt(1) > '4')
            return null;
        Square squareLeft = null;
        char alpha = square.label.charAt(0);
        String height = square.label.substring(1);
        int change = (Integer.parseInt(height)<5)?1:(alpha >= 'e')?1:-1;
        if(Integer.parseInt(height)>8 && alpha == 'i')
            return this.findSquare('e'+height);
        while(alpha>='a' && alpha<'m'){
            alpha = (char) (alpha+change);
            if(this.findSquare(alpha+height)!=null){
                squareLeft = this.findSquare(alpha+height);
                break;
            }
        }
        return squareLeft;
    }

    public Square squareRight(Square square){
        if(square.label.charAt(0) == 'l')
            return null;
        if(square.label.charAt(0) == 'a' && square.label.charAt(1) < '5')
            return null;
        Square squareRight = null;
        char alpha = square.label.charAt(0);
        String height = square.label.substring(1);
        int change = (Integer.parseInt(height)<5)?-1:1;
        if(Integer.parseInt(height)>8 && alpha == 'e')
            return this.findSquare('i'+height);
        while(alpha>='a' && alpha<'m'){
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
        if(square.label.equals("i9")){
            if(square.isEmpty()) {
                list.add(this.findSquare("e4"));
                list.addAll(this.topLeftDiagonal(this.findSquare("e4")));
                list.add(this.findSquare("d5"));
                list.addAll(this.topLeftDiagonal(this.findSquare("d5")));
            }
            return list;
        }
        Square above = this.squareAbove(square);
        if(above!=null) {
            Square topLeft = this.squareLeft(above);
            if(topLeft!= null){
                list.add(topLeft);
                if(topLeft.isEmpty()){
                    list.addAll(this.topLeftDiagonal(topLeft));
                }
            }
        }
        return list;
    }

    public ArrayList<Square> bottomLeftDiagonal(Square square){
        ArrayList<Square> list = new ArrayList<>();
        if(square.label.equals("d4")){
            if(square.isEmpty()) {
                list.add(this.findSquare("e9"));
                list.addAll(this.bottomLeftDiagonal(this.findSquare("e9")));
                list.add(this.findSquare("i5"));
                list.addAll(this.topRightDiagonal(this.findSquare("i5")));
            }
            return list;
        }
        if(square.label.equals("i5")){
            if(square.isEmpty()) {
                list.add(this.findSquare("e9"));
                list.addAll(this.bottomLeftDiagonal(this.findSquare("e9")));
                list.add(this.findSquare("d4"));
                list.addAll(this.topRightDiagonal(this.findSquare("d4")));
            }
            return list;
        }
        Square below = this.squareBelow(square);
        if(below!=null) {
            if(square.label.charAt(0)<='d' && (square.label.charAt(1)=='4' || square.label.charAt(1)=='5')){
                Square bottomLeft = this.squareRight(below);
                if(bottomLeft!=null){
                    list.add(bottomLeft);
                    if(bottomLeft.isEmpty())
                        list.addAll(this.topRightDiagonal(bottomLeft));
                }
                return list;
            }
            Square bottomLeft = this.squareLeft(below);
            if(bottomLeft!= null){
                list.add(bottomLeft);
                if(bottomLeft.isEmpty())
                    list.addAll(this.bottomLeftDiagonal(bottomLeft));
            }
            else{
                System.out.println("Square bottomLeft was null");
            }
        }
        return list;
    }

    public ArrayList<Square> topRightDiagonal(Square square){
        ArrayList<Square> list = new ArrayList<>();
        if(square.label.equals("e9")){
            if(square.isEmpty()) {
                list.add(this.findSquare("d4"));
                list.addAll(this.topRightDiagonal(this.findSquare("d4")));
                list.add(this.findSquare("i5"));
                list.addAll(this.topRightDiagonal(this.findSquare("i5")));
            }
            return list;
        }
        Square above = this.squareAbove(square);
        if(above!=null) {
            Square topRight = this.squareRight(above);
            if(topRight!= null){
                list.add(topRight);
                if(topRight.isEmpty())
                    list.addAll(this.topRightDiagonal(topRight));
            }
            else{
                System.out.println("Top right was null:" + above);
            }
        }
        return list;
    }

    public ArrayList<Square> bottomRightDiagonal(Square square){
        ArrayList<Square> list = new ArrayList<>();
        if(square.label.equals("e4")){
            list.add(this.findSquare("i9"));
            list.addAll(this.bottomRightDiagonal(this.findSquare("i9")));
            list.add(this.findSquare("d5"));
            list.addAll(this.topLeftDiagonal(this.findSquare("d5")));
            return list;
        }
        if(square.label.equals("d5")){
            list.add(this.findSquare("i9"));
            list.addAll(this.bottomRightDiagonal(this.findSquare("i9")));
            list.add(this.findSquare("e4"));
            list.addAll(this.topLeftDiagonal(this.findSquare("e4")));
            return list;
        }
        Square below = this.squareBelow(square);
        if(below!=null) {
            if(square.label.charAt(0)<='d' && (square.label.charAt(1)=='4' || square.label.charAt(1)=='5')){
                Square bottomRight = this.squareLeft(below);
                if(bottomRight!=null) {
                    list.add(bottomRight);
                    if(bottomRight.isEmpty())
                        list.addAll(this.topLeftDiagonal(bottomRight));
                }
                return list;
            }
            Square bottomRight = this.squareRight(below);
            if(bottomRight!= null){
                list.add(bottomRight);
                if(bottomRight.isEmpty())
                    list.addAll(this.bottomRightDiagonal(bottomRight));
            }
        }
        return list;
    }

    public void makeMove(Move move){
        this.findSquare(move.to.label).setPiece(this.findSquare(move.from.label).piece);
        this.findSquare(move.from.label).piece = null;

    }


}

