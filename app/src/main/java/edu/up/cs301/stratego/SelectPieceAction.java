package edu.up.cs301.stratego;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * SelectPieceAction
 *
 * @author Anne Marie Blank
 * @author Harry Vu
 * @author Vincent Truong
 * @author Kathryn Weidman
 * @version 3/18/2022
 */
public class SelectPieceAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SelectPieceAction(GamePlayer player) {
        super(player);
    }

}//SelectPieceAction
