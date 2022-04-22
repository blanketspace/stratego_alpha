package edu.up.cs301.stratego;

import edu.up.cs301.game.infoMsg.GameInfo;

public class HelpInfo extends GameInfo {

    /**
     * External Citation
     * 4/21/22
     *
     * lifted from GameOverInfo (both methods)
     */
    private String message;

    /**
     * constructor
     *
     * @param msg
     * 		a message that tells the result of the game
     */
    public HelpInfo(String msg) {
        this.message = msg;
    }

    /**
     * getter method for the message
     *
     * @return
     * 		the message, telling the result of the game
     */
    public String getMessage() {
        return message;
    }
}//class HelpInfo

