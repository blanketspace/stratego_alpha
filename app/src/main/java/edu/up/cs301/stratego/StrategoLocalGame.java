package edu.up.cs301.stratego;

import android.util.Log;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.pig.PigGameState;
import edu.up.cs301.stratego.actions.DownAction;
import edu.up.cs301.stratego.actions.LeftAction;
import edu.up.cs301.stratego.actions.RightAction;
import edu.up.cs301.stratego.actions.SelectPieceAction;
import edu.up.cs301.stratego.actions.UpAction;

/**
 * StrategoLocalGame
 *
 * @author Anne Marie Blank
 * @author Harry Vu
 * @author Vincent Truong
 * @author Kathryn Weidman
 *
 * @version 4/7/2022
 */
public class StrategoLocalGame extends LocalGame {

    private StrategoGameState goldie;
    //private BoardView boardView;
    private Unit chosen;

    public StrategoLocalGame() {
        goldie = new StrategoGameState();
        //boardView = goldie.getMyBoardView();  //CAUTION: is null on launch
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
            return "Flag Captured. Player Wins";
        }
        else {
            return null;
        }

    }

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
                equiv.setSelected(true);
            }

            return true;
        }

        //Unit chosen = null;
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
        if(chosen != null){  //get selected unit through troop array lists

            //boardView = goldie.getMyBoardView();

            Log.i("SELECTED_NOT_NULL", "s;lidjgaorjg;drkh");
           /* int chosenX = chosen.getxLoc();
            int chosenY = chosen.getyLoc();*/

            if (action instanceof UpAction) {

                    //player 0's turn, therefore p1Troops
                    //this also (temporarily, assuming player 0 is on far side) means UP = y + 1

                    //calls helper method to get gameboard array to match
                    this.movePiece(1, chosen, goldie.getWhoseTurn());
                    Log.i("MAKE_MOVE_UP", "UPSAKJFLKJOIEJGOIJSL:KGJLDKJG:LKJ");
                    return true;

            }
            else if (action instanceof DownAction) {
                if (goldie.getWhoseTurn() == 0) {
                    //player 0's turn, therefore p1Troops
                }
                else {
                    //player 1's turn, therefore p2Troops
                }
            }
            else if (action instanceof LeftAction) {
                if (goldie.getWhoseTurn() == 0) {
                    //player 0's turn, therefore p1Troops
                }
                else {
                    //player 1's turn, therefore p2Troops
                }
            }
            else if (action instanceof RightAction) {
                if (goldie.getWhoseTurn() == 0) {
                    //player 0's turn, therefore p1Troops
                }
                else {
                    //player 1's turn, therefore p2Troops
                }
            }
            else {
                //something went wrong
                return false;
            }
            return true;
        }
        else {
            return false; //no Units were selected, therefore no moves can be made
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

    /** dir indicates the direction the Unit wishes to go. 1 = up, 2 = down, 3 = left, and 4 = right
     * to simplify this method and save roughly 80 lines of code, the switch case is being used to
     * make placement calculations player 0 is assumed to be on the far side of the board, and
     * player 1 on the near side
     *
     * */
    int newX = chosenX;
    int newY = chosenY;
    if(playerID == 1){
        //assuming player 0 is at the top of the board
        switch(dir){
            case 1:
                newY = chosenY + 1;
                break;

            case 2:
                newY = chosenY - 1;
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

            case 3:
                newX = chosenX + 1;
                break;

            case 4:
                newX = chosenX - 1;
                break;
        }//end switch
    }


    //TODO:  put this back!
    // if(this.inBounds(newX, newY)) {  //calls helper method to make sure we're in bounds of array
        if (gameboard[newX][newY] == null) {  //spot is already empty, go ahead and take it
            chosen.setyLoc(newY);
            chosen.setxLoc(newX);
            gameboard[newX][newY] = chosen;
            Log.i("SPOT_TAKEN", "WAS_EMPTY_BEFORE_ARGAEJR;BKN;FJBNKDJHSLDJHLKSJTDHKSKLE");
            return true;

        }
        else if (gameboard[newX][newY].getRank() == Unit.WATER) {
            //you can't walk on water, therefore do nothing
            //TODO: do we wanna flash the screen?
        }
        else if (gameboard[newX][newY].getRank() == Unit.FLAG) {
            //game over!
            goldie.setFlagCaptured(true);
            return true;
        }
        else if (gameboard[newX][newY].getRank() == Unit.BOMB) {
            if (goldie.isMinerAttack(chosen.getRank())) {
                //you are a miner, diffuse the bomb
                gameboard[newX][newY] = null;
                chosen.setxLoc(newX);
                chosen.setyLoc(newY);
                gameboard[newX][newY] = chosen;  //fill its old spot
            } else {
                //you are not a miner, you explode
                gameboard[chosenX][chosenY] = null;
                chosen.setStatus(false);
            }
            return true;
        }
        else if (gameboard[newX][newY].getOwnerID() != goldie.getWhoseTurn()) {
            //attack
            int opponentRank = gameboard[newX][newY].getRank();
            if (opponentRank > chosen.getRank()) {   //they won
                chosen.setStatus(false);            //you die
                gameboard[chosenX][chosenY] = null; //empty your spot

            } else {  //you won
                gameboard[newX][newY].setStatus(false); //they die
                gameboard[chosenX][chosenY] = null;    //empty your spot
                chosen.setxLoc(newX);
                chosen.setyLoc(newY);
                gameboard[newX][newY] = chosen;       //fill their old spot
            }
            return true;
        }
        return true;
    //}
//    else {
//        //something's gone wrong, likely out of bounds
//        return false;
//    }
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

public void holdUnit(Unit u){
    chosen = u;
}




}//class StrategoLocalGame
