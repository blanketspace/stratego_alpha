package edu.up.cs301.stratego;

import android.util.Log;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.stratego.actions.DownAction;
import edu.up.cs301.stratego.actions.LeftAction;
import edu.up.cs301.stratego.actions.RightAction;
import edu.up.cs301.stratego.actions.SelectPieceAction;
import edu.up.cs301.stratego.actions.SurrenderAction;
import edu.up.cs301.stratego.actions.UpAction;

/**
 * StrategoLocalGame
 *
 * @author Anne Marie Blank
 * @author Harry Vu
 * @author Vincent Truong
 * @author Kathryn Weidman
 *
 * @version 4/20/2022
 */
public class StrategoLocalGame extends LocalGame {

    private StrategoGameState goldie;
    private Unit chosen;


    public StrategoLocalGame() {
        goldie = new StrategoGameState();
    }//ctor

    /**
     * EXTERNAL CITATION
     *
     * lifted from PigLocalGame
     * https://github.com/cs301up/PigGameStarter
     *
     * canMove
     *
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if (playerIdx == goldie.getWhoseTurn()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * External Citation
     *
     * 4/6/2022
     *
     * Lifted from PigLocalGame, edited appropriately
     *
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //make a copy of current game state
        StrategoGameState gameCopy = new StrategoGameState(goldie);

        //use GamePlayer sendInfo to send it to the player
        p.sendInfo(gameCopy);

    }//sendUpdatedSate


    @Override
    protected String checkIfGameOver() {
        if (goldie.isFlagCaptured()) {
            Log.i("FLAG_CAPTURED", "jaslkgja;eorinb/ldkfn;aoirg");
            String winner = null;
            if(goldie.getWhoseTurn() == 0){
                winner = "Computer Player";  //TODO: problematic for network play
            }
            else{
                winner = "Human Player";
            }
            return "Flag Captured. " + winner +  " Wins";
        }
        else {
            return null;
        }

    }//checkIfGameOver


    /**
     * makeMove
     *
     *
     *
     * @param action
     * 			The move that the player has sent to the game
     * @return
     */
    @Override
    protected boolean makeMove(GameAction action) {
        Log.i("MAKE_MOVE_CALLED", "afgerbkdfblkajd;fgjadfnba;lf");
        if(action instanceof SelectPieceAction){
            Log.i("SELECT_PIECE", "s;fljwogiej;rkn;aldrh;alrdk");
            SelectPieceAction spa = (SelectPieceAction) action;
            Unit equiv = goldie.findEquivUnit(spa.selected);
            if (equiv != null) {
                goldie.clearSelection();
                equiv.setSelected(true);
            }

            return true;
        }
        else if(action instanceof SurrenderAction){
            goldie.setFlagCaptured(true);
            //TODO: maybe change something so the message doesn't read "flag captured"?
            return true;
        }
        else {  //every other action is caught here
            //the following loops go through each list of troops
            //and finds the Unit that has been selected
            chosen = null;
            if(goldie.getWhoseTurn() == 1){
                for(Unit u: goldie.getP1Troops()){
                    if(u.getSelected()){
                        chosen = u;
                        break;
                    }
                }
            }
            else{
                for(Unit u: goldie.getP2Troops()){
                    if(u.getSelected()){
                        chosen = u;
                        break;
                    }
                }
            }
            if(chosen != null && chosen.getOwnerID() != goldie.getWhoseTurn()){  //get selected unit through troop array lists

                Log.i("SELECTED_NOT_NULL", "s;lidjgaorjg;drkh");

                if (action instanceof UpAction) {

                    //calls helper method to get gameboard array to match
                    this.movePiece(1, chosen, goldie.getWhoseTurn());
                    Log.i("MAKE_MOVE_UP", "UPSAKJFLKJOIEJGOIJSL:KGJLDKJG:LKJ");



                }
                else if (action instanceof DownAction) {
                    this.movePiece(2, chosen, goldie.getWhoseTurn());

                }
                else if (action instanceof LeftAction) {
                    this.movePiece(3, chosen, goldie.getWhoseTurn());
                }
                else if (action instanceof RightAction) {
                    this.movePiece(4, chosen, goldie.getWhoseTurn());

                }
                else {
                    //something went wrong
                    return false;
                }

                if(goldie.getWhoseTurn() == 0){
                    goldie.setWhoseTurn(1);
                }
                else{
                    goldie.setWhoseTurn(0);
                }
                goldie.clearSelection();
                //chosen = null;
                return true;

            }
            else {
                return false; //no Units were selected, therefore no moves can be made
            }
        }

    }//makeMove




/**
 * movePiece
 *
 *
 * External Citation
 * Date 2/23/2022
 * Issue: unsure what methods to implement
 *
 * "Link": Office Hours help from Nux
 *
 *
 * @param playerID id of the player moving the piece
 * @param dir      direction of intended movement (up/down/l/r)
 *
 * @return  false if move is illegal, true otherwise
 */
public boolean movePiece(int dir, Unit chosen, int playerID) {
    int chosenY = chosen.getyLoc();
    int chosenX = chosen.getxLoc();
    Unit[][] gameboard = goldie.getGameboard();
    Log.i("Unigue", goldie.boardToString());

    /** dir indicates the direction the Unit wishes to go. 1 = up, 2 = down, 3 = left, and 4 = right
     * to simplify this method and save roughly 80 lines of code, the switch case is being used to
     * make placement calculations player 0 is assumed to be on the far side of the board, and
     * player 1 on the near side
     *
     * */
    int newX = chosenX;
    int newY = chosenY;
    if(playerID == 1){
        //assuming player 1 is at the top of the board
        switch(dir){
            case 1:
                newY = chosenY - 1;
                break;

            case 2:
                newY = chosenY + 1;
                break;

            case 3:
                newX = chosenX - 1;
                break;

            case 4:
                newX = chosenX + 1;
                break;
        }//end switch
    }
    else if(playerID == 0){
        switch(dir){
            case 1:
                newY = chosenY - 1;
                break;

            case 2:
                newY = chosenY + 1;
                break;

            case 3:  //left
                newX = chosenX - 1;
                break;

            case 4:
                newX = chosenX + 1;
                break;
        }//end switch
    }


    //calls helper method to make sure we're in bounds of array
    if(this.inBounds(newX, newY)) {
        if (gameboard[newY][newX] == null) {  //spot is already empty, go ahead and take it
            //TODO: This section has different results on each run
            //this is potentially due to an error in the comp player
            //check to see if the comp player is accidentally capturing its own troops
            chosen.setyLoc(newY);
            chosen.setxLoc(newX);
            gameboard[newY][newX] = chosen;
            gameboard[chosenY][chosenX] = null;
            Log.i("SPOT_TAKEN", "WAS_EMPTY_BEFORE_ARGAEJR;BKN;FJBNKDJHSLDJHLKSJTDHKSKLE");

        }
        else if (gameboard[newY][newX].getRank() == Unit.WATER) {
            //you can't walk on water, therefore do nothing
            //TODO: do we wanna flash the screen?
        }
        else if (gameboard[newY][newX].getOwnerID() == goldie.getWhoseTurn()) {  //turns and player nums are reversed
            if (gameboard[newY][newX].getRank() == Unit.BOMB) {
                if (chosen.getRank() == Unit.MINER) {
                    //you are a miner, disarm the bomb
                    gameboard[newY][newX].setDead(true);

                    gameboard[newY][newX] = null;


                    chosen.setxLoc(newX);
                    chosen.setyLoc(newY);
                    gameboard[newY][newX] = chosen;  //fill its old spot
                    Log.i("BOMB_DISARMED", "alksjdgaljlkah");
                } else {
                    //you are not a miner, you explode

                    gameboard[chosenY][chosenX] = null;
                    chosen.setDead(true);
                    Log.i("UNIT_EXPLODED", "lakjfdklgajdlfkhj");
                }
            }
            else if (gameboard[newY][newX].getRank() == Unit.FLAG) {
                //game over!
                goldie.setFlagCaptured(true);
            }
            else{
                //not a bomb, free to attack
                int opponentRank = gameboard[newY][newX].getRank();
                if (opponentRank > chosen.getRank()) {   //they won
                    chosen.setDead(true);            //you die

                    gameboard[chosenY][chosenX] = null; //empty your spot
                    Log.i("OPP_WON_BATTLE", "akjglkjdlhskgkj");

                } else {  //you won
                    gameboard[newY][newX].setDead(true); //they die
                    gameboard[chosenY][chosenX] = null;    //empty your spot
                    chosen.setxLoc(newX);
                    chosen.setyLoc(newY);
                    
                    gameboard[newY][newX] = chosen;       //fill their old spot
                    Log.i("ATTACKER_WON_BATTLE", "sjfgklsjldkhg");
                }
            }
            return true;
        }
        return true;
    }
    else {
        //something's gone wrong, likely out of bounds
        return false;
    }
}//makeMove


/**
 * inBounds
 *
 * @return  true if proposed x,y is in the bounds of the array
 */
public boolean inBounds(int x, int y){
    boolean bounds = false;
    if(x >= 0 && x < 10){
        if(y >= 0 && y < 10){
            bounds = true;
        }
    }
    else {
        bounds = false;
    }
    return bounds;
}//inBounds


    /**
     *
     * @param u
     */
public void holdUnit(Unit u){
    chosen = u;
}




}//class StrategoLocalGame
