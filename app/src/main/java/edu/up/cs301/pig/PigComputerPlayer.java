package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        PigGameState oinkGame = new PigGameState((PigGameState)info);
        if(oinkGame.getPlayerTurn() != this.playerNum){
            return;
        }
        else{
            double rand = Math.random();
            if(rand % 2 == 0){
                PigRollAction pr = new PigRollAction(this);
                game.sendAction(pr);
            }
            else{
                PigHoldAction ph = new PigHoldAction(this);
                game.sendAction(ph);
            }
        }
    }//receiveInfo

}
