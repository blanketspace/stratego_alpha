package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * PigSmartCompPlayer
 *
 * AI that makes slightly better choices than the other
 *
 * @author blank
 * @version 3/12/22
 */
public class PigSmartCompPlayer extends GameComputerPlayer {


    /**
     * ctor
     *
     * does nothing different than parent's
     *
     * @param name the player's name (e.g., "John")
     */
    public PigSmartCompPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        PigGameState gameState = new PigGameState((PigGameState) info);
        //check to make sure it's this player's turn
        if(gameState.getPlayerTurn() != this.playerNum){
            return;
        }
        int score;
        int oppScore;
        if(this.playerNum == 0) {
            score = ((PigGameState) info).getPlayer0Score();
            oppScore = ((PigGameState) info).getPlayer1Score();
        }
        else{
            score = ((PigGameState) info).getPlayer1Score();
            oppScore = ((PigGameState) info).getPlayer0Score();
        }
        this.sleep(2000);

        //if game total is less than seven, or you're losing
        if(((PigGameState) info).getRunTotal() < 7 || score + ((PigGameState) info).getRunTotal() < oppScore){
            //roll
            PigRollAction pra = new PigRollAction(this);
            game.sendAction(pra);
        }
        else{
            PigHoldAction pha = new PigHoldAction(this);
            game.sendAction(pha);
        }
    }//receiveInfo

}//PigSmartComputer