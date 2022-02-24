package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState goldenPig;  //will be used to store the official game state

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        goldenPig = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == goldenPig.getPlayerTurn()){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        int currentPlayer = goldenPig.getPlayerTurn();

        if(action instanceof PigHoldAction){
            if(currentPlayer == 0) {
                goldenPig.setPlayer0Score(goldenPig.getPlayer0Score()
                        + goldenPig.getRunTotal());
            }
            else {
                goldenPig.setPlayer1Score(goldenPig.getPlayer1Score()
                        + goldenPig.getRunTotal());
            }

            goldenPig.setRunTotal(0);

            if(currentPlayer == 0){
                goldenPig.setPlayerTurn(1);
            }
            else{
                goldenPig.setPlayerTurn(0);
            }
            return true;
        }

        else if (action instanceof  PigRollAction){
           goldenPig.setCurrentDieVal((int) (Math.random()*6));
           if (goldenPig.getCurrentDieVal() != 1){
               //add die value to current running total
               int newTotal = goldenPig.getCurrentDieVal() + goldenPig.getRunTotal();
               goldenPig.setRunTotal(newTotal);
           }
           else{
               //current running total=0
               goldenPig.setRunTotal(0);

               //make it other player's turn
               if(currentPlayer == 0){
                   goldenPig.setPlayerTurn(1);
               }
               else{
                   goldenPig.setPlayerTurn(0);
               }
           }
           return true;
        }
        else {
            return false;
        }

    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //make a copy of current game state
        PigGameState copyPig = new PigGameState(goldenPig);

        //use GamePlayer sendInfo to send it to p
        p.sendInfo(copyPig);

    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if(goldenPig.getPlayer0Score() >= 50){
            //return name of player and score
            return "" + this.playerNames[0] + " wins! Score: " + goldenPig.getPlayer0Score();
        }
        else if(goldenPig.getPlayer1Score() >= 50){
            //return name of player and score
            return "" + this.playerNames[1] + " wins! Score: " + goldenPig.getPlayer1Score();
        }
        else {
            return null;
        }
    }

}// class PigLocalGame
