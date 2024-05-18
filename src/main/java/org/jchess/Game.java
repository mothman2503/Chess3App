package org.jchess;

public class Game {
    public Settings settings;
    public Chess3Board board;
    private Player activePlayer;
    //public GameClock gameClock;
    //public Client client;
    public Moves moves;
    public String gameID;
    //public Chat chat;

    public Game(){
        this.board = new Chess3Board();
        this.settings = new Settings();
        this.gameID = "";
    }

    public void newGame(){
        this.board.setPieces(settings.playerWhite);
        this.board.setPieces(settings.playerBlack);
        this.board.setPieces(settings.playerRed);

        activePlayer = settings.playerWhite;

    }

    public void setPlayers(Player player1, Player player2, Player player3){}

    public void loadGame(Moves moves, Settings settings){
        this.settings = settings;
        this.moves = moves;
    }

    public void saveGame(){}

    public void nextTurn(){
        if (activePlayer.color == Player.colors.WHITE){
            activePlayer = settings.playerBlack;
        }
        else if (activePlayer.color == Player.colors.BLACK){
            activePlayer = settings.playerRed;
        }
        else{
            activePlayer = settings.playerWhite;
        }
    }

    public void makeMove(Move move){
        this.moves.add(move);
        this.nextTurn();
    }

}
