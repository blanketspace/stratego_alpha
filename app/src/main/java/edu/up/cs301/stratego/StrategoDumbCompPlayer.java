package edu.up.cs301.stratego;


import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.stratego.actions.DownAction;
import edu.up.cs301.stratego.actions.LeftAction;
import edu.up.cs301.stratego.actions.MovePieceAction;
import edu.up.cs301.stratego.actions.RightAction;
import edu.up.cs301.stratego.actions.SelectPieceAction;
import edu.up.cs301.stratego.actions.UpAction;

/**
 * StrategoDumbCompPlayer
 *
 * @author Anne Marie Blank
 * @author Harry Vu,
 * @author Vincent Truong,
 * @author Kathryn Weidman
 * @version 4/13/2022
 */
public class StrategoDumbCompPlayer extends GameComputerPlayer {

    private StrategoGameState copyGS;

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public StrategoDumbCompPlayer(String name) {
        super(name);
        this.playerNum = 1;
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(info instanceof StrategoGameState){
            copyGS = (StrategoGameState) info;
            Log.i("DUMB_COMP","RECEIVED_NEW_INFO_alknrg;ladn");

            if(copyGS.getWhoseTurn() == this.playerNum){
                //if we've made it here, then it *is* this player's turn, so we can choose moves
                Random randGen = new Random();
                int randomDir = randGen.nextInt(4);

                Unit[][] board = copyGS.getGameboard();
                int randomX = randGen.nextInt(39);
                // int randomY = randGen.nextInt(9);
                Unit selected = copyGS.getUnit(0, randomX);

                //Unit selected = board[]
                //TODO: IDEA! use p2troops: only one random and eliminates the "computer picks the water" issue
                sleep(2000);

                if (selected != null){
                    SelectPieceAction spa = new SelectPieceAction(this, board[selected.getyLoc()][selected.getxLoc()]);
                    game.sendAction(spa);
                    Log.i("COMP_SELECTED_Piece", "asjgarjigoaet;hdjlksgjdfg");
                }
                //send move actions based on random number chosen
                if(randomDir == 0){ //moving up
                    UpAction upAction = new UpAction(this);
                    game.sendAction(upAction);
                    Log.i("COMP_MOVED_UP", "aj;lkdjgldjgisldfjgk");
                }
                else if(randomDir == 1){ //moving down
                    DownAction downAction = new DownAction(this);
                    game.sendAction(downAction);
                    Log.i("COMP_MOVED_DOWN", "ijoajglksjdlkfgjs");
                }
                else if(randomDir == 2){ //moving left
                    LeftAction leftAction = new LeftAction(this);
                    game.sendAction(leftAction);
                    Log.i("COMP_MOVED_LEFT", "jkgdlkjgalkjlgkjsd");
                }
                else if(randomDir == 3){ //moving right
                    RightAction rightAction = new RightAction(this);
                    game.sendAction(rightAction);
                    Log.i("COMP_MOVED_RIGHT", "ldjglajdfldkhshd'fj");
                }
            }

        }
        else {
            //something has gone wrong, it's likely not the player's turn
            //here, the human player has the screen flash, but a computer doesn't
            //need that visual cue, so we're leaving this empty
        }

    }//receiveInfo

    public void setUpDumbPieces(StrategoGameState gameState) { //Untested 4/7/22, Unsure of proper location
        ArrayList<Unit> p1Troops = gameState.getP1Troops();
        int i = 0, j = 6;
        for(Unit troop: p1Troops) { //Coordinates for placePiece are formula from StrategoGameState
            gameState.placePiece(playerNum, troop, i * troop.UNIT_WIDTH, j * troop.UNIT_HEIGHT);
            troop.setDead(true);
            switch(i) {
                case 9: //If we've reached the end of the row, start over a column down
                    i = 0;
                    j++;

                    break;
                default:
                    i++;
                    break;
            } //Possible bug: troop arraylist being too short/long causing errors in placement;
        }
    }
}//StrategoDumbCompPlayer

