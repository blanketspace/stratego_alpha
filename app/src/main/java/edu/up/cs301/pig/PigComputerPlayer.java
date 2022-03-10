package edu.up.cs301.pig;

import java.util.Random;

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
        this.sleep(2000);

        Random rand = new Random();
        int r = rand.nextInt(2);

        if(r == 0){
            PigHoldAction ph = new PigHoldAction(this);
            game.sendAction(ph);
        }
        else{
            PigRollAction pr = new PigRollAction(this);
            game.sendAction(pr);
        }

    }//receiveInfo

}
