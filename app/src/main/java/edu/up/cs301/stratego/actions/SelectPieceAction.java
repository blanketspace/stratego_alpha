package edu.up.cs301.stratego.actions;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.stratego.Unit;

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
    public Unit selected = null;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SelectPieceAction(GamePlayer player, Unit initSelected) {
        super(player);
        this.selected = initSelected;
    }

}//SelectPieceAction
