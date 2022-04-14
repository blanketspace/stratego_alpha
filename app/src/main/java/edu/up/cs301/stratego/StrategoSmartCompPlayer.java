package edu.up.cs301.stratego;

import java.util.ArrayList;
import java.util.Random;

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
 *
 * Reference for smart AI setups from a world champion Stratego player
 *      https://www.ultraboardgames.com/stratego/setups.php
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

    public void setUpStrategy(int chosenStrat) {
        switch (chosenStrat) { //We have possible setups from a world champion stratego player
            case 0:
                //smth to fill the ranks properly
                break;
            case 1:
                //smth
                break;
            case 2:
                //smth
                break;
        }
    }

    private void stratOne(StrategoGameState gameState) {
        ArrayList<Unit> p1Troops = gameState.getP1Troops();
        for(Unit troop: p1Troops) {
            troop.setStatus(false);
            switch (troop.getRank()) {
                case 1:
            }

        }
    }
    private void stratTwo() {}
    private void stratThree() {}

    public void choosePiece() {} //returns a Unit, not void

    public int checkFlanks(Unit chosen) {return 0;}

    public int chooseMoveDir(Unit chosen) {return 0;}


}
