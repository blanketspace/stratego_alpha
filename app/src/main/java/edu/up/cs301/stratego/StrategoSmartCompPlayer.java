package edu.up.cs301.stratego;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * StrategoDumbCompPlayer
 *
 * @author Anne Marie Blank,
 * @author Harry Vu,
 * @author Vincent Truong,
 * @author Kathryn Weidman
 * @version 4/5/2022
 */
public class StrategoSmartCompPlayer extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public StrategoSmartCompPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        StrategoGameState gameState = new StrategoGameState((StrategoGameState) info);
        if (!(info instanceof StrategoGameState)) {
            return;
        }

        if (gameState.getWhoseTurn() != playerNum) {
            return;
        }

        Unit[][] board = ((StrategoGameState) info).getGameboard();
    }

    public void setUpStrategy(int chosenStrat) {}

    public void choosePiece() {} //returns a Unit, not void

    public int checkFlanks(Unit chosen) {return 0;}

    public int chooseMoveDir(Unit chosen) {return 0;}


}
