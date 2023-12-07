package org.example;

public class Chess3Section {
    public Square[][] squares;//squares of chessboard
    //The top left corner(pos 0,0) is always the square at the center rosette

    protected Chess3Board board;
    protected String[] x_axis, y_axis;

    public Chess3Section(String[] x_axis, String[] y_axis, Chess3Board board) {
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.squares = new Square[4][4];
        this.board = board;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                this.squares[i][j] = new Square(x_axis[j] + y_axis[i], j, i, this);

    }

    public Square findSquare(String label){
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(squares[j][i].label.equals(label))
                    return squares[j][i];
        return null;
    }

    @Override
    public String toString() {
        String ret = "";
        for(int i = 0; i<4; i++) {
            ret += y_axis[i]+ " " + (y_axis[i].length()>1?"":" ");
            for(int j = 0; j<4;j++){
                if(this.squares[i][j].piece!=null) {
                    ret +=  this.squares[i][j].piece.symbol + "   ";
                }
                else{
                    ret += "    ";
                }
            }
            ret+="\n";
        }
        ret+="   ";
        for(int i = 0; i<4; i++)
            ret+= x_axis[i] + "  " + (x_axis[i].length()>1?"":" ");
/*
        for(int i = 0; i< 4; i++)
            for(int j = 0; j<4; j++)
                ret+= this.squares[j][i].label+", ";*/
        return ret;
    }
}
