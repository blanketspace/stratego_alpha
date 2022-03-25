package edu.up.cs301.stratego;

import android.view.View;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;


/**
 * StrategoHumanPlayer
 *
 * @author Anne Marie Blank
 * @author Harry Vu
 * @author Vincent Truong
 * @author Kathryn Weidman
 * @version 3/18/2022
 */
public class StrategoHumanPlayer extends GameHumanPlayer {

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public StrategoHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

        // set the GUI to be the game's layout xml
        activity.setContentView(R.layout.stratego_board);
    }
}//StrategoHumanPlayer

