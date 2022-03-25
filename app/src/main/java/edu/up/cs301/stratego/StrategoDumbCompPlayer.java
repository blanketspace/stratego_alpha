package edu.up.cs301.stratego;


import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * StrategoDumbCompPlayer
 *
 * @author blank
 * @author Harry Vu,
 * @author Vincent Truong,
 * @author Kathryn Weidman
 * @version 3/18/2022
 */
public class StrategoDumbCompPlayer extends GameComputerPlayer {

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public StrategoDumbCompPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {

    }
}//StrategoDumbCompPlayer

