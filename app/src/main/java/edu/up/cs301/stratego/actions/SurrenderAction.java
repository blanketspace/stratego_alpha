package edu.up.cs301.stratego.actions;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

public class SurrenderAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SurrenderAction(GamePlayer player) {
        super(player);
    }
}
