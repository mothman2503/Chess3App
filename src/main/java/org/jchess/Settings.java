package org.jchess;

public class Settings {
    public Player playerWhite;
    public Player playerBlack;
    public Player playerRed;
    public gameModes gameMode;
    //public GameClock gameClock;
    //public Client client;

    public enum gameModes{
        loadGame, newGame
    }

    Settings(){
        this.playerWhite = new Player("", "WHITE");
        this.playerBlack = new Player("", "BLACK");
        this.playerRed = new Player("", "RED");

        this.gameMode = gameModes.newGame;
    }

}
