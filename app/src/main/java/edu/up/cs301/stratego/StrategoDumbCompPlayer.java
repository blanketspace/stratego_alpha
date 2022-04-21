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
 * @version 4/20/2022
 */
public class StrategoDumbCompPlayer extends GameComputerPlayer {

    private StrategoGameState copyGS;
    private int dir;


    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public StrategoDumbCompPlayer(String name) {
        super(name);
        this.playerNum = 1;
    }

    /**
     * @return a list of legal directions that a given piece can move to. If no legal
     * directions are possible, then it returns null.
     */
    protected int[] legalDirs(Unit unit) {
        Unit[][] board = copyGS.getGameboard();
        ArrayList<Integer> temp = new ArrayList<>();
        int x = unit.getxLoc();
        int y = unit.getyLoc();

        //check UP
        if (y < 0) {
            Unit dest = board[y - 1][x];
            if (dest == null) {
                temp.add(1);
            }
        }

        //TODO: check down, left, right
        if (y < 9) {
            Unit dest = board[y + 1][x];
            if (dest == null) {
                temp.add(2);
            }
        }
        if (x < 0) { //left
            Unit dest = board[y][x - 1];
            if (dest == null) {
                temp.add(3);
            }
        }
        if (x < 9) { // right
            Unit dest = board[y][x + 1];
            if (dest == null) {
                temp.add(4);
            }
        }


        //conver the temp arraylist to an array of int
        if (temp.size() == 0) return null;

        int[] result = new int[temp.size()];
        for(int i = 0; i < temp.size(); ++i) {
            result[i] = temp.get(i);
        }

        return result;
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
                dir = -1;
                Unit selected = null;
                while(dir < 0) {

                    int randomX = randGen.nextInt(39);
                    // int randomY = randGen.nextInt(9);
                    selected = copyGS.getUnit(0, randomX);


                    while(selected.getStatus() == true || selected.getRank() == 11 || selected.getRank() == 12){
                        randomX = randGen.nextInt(39);
                        selected = copyGS.getUnit(0, randomX);
                    }


                    //added a legalDirs method that checks for legal moves that the dumb ai can make
                    int[] options = legalDirs(selected);
                    if (options != null) {
                        //whichOption is index of the options array (randomly selects an index from options)
                        int whichOption = randGen.nextInt(options.length);
                        //using the whichOptions index, we will use it to get a dir from the options array
                        dir = options[whichOption];
                    }
                }


                //Unit selected = board[]
                //TODO: put this back in!!
  //              sleep(2000);

                if (selected != null){
                    SelectPieceAction spa = new SelectPieceAction(this, board[selected.getyLoc()][selected.getxLoc()]);
                    game.sendAction(spa);
                    Log.i("COMP_SELECTED_Piece", "asjgarjigoaet;hdjlksgjdfg");
                }

                //TODO: THE PLAYER's PIECE MOVES UP TWICE BECAUSE of the PlayerID
                //only moved down once because we set randomDir = 1 (up), not down, so the if for down
                //isn't executed.

                //send move actions based on random number chosen
                if(dir == 1){ //moving up
                    UpAction upAction = new UpAction(this);
                    game.sendAction(upAction);
                    Log.i("COMP_MOVED_UP", "aj;lkdjgldjgisldfjgk");
                }
                else if(dir == 2){ //moving down
                    DownAction downAction = new DownAction(this);
                    game.sendAction(downAction);
                    Log.i("COMP_MOVED_DOWN", "ijoajglksjdlkfgjs");
                }
                else if(dir == 3){ //moving left
                    LeftAction leftAction = new LeftAction(this);
                    game.sendAction(leftAction);
                    Log.i("COMP_MOVED_LEFT", "jkgdlkjgalkjlgkjsd");
                }
                else if(dir == 4){ //moving right
                    RightAction rightAction = new RightAction(this);
                    game.sendAction(rightAction);
                    Log.i("COMP_MOVED_RIGHT", "ldjglajdfldkhshd'fj");
                }
                Log.i("Board",((StrategoGameState) info).boardToString());
            }

        }
        else {
            //something has gone wrong, it's likely not the player's turn
            //here, the human player has the screen flash, but a computer doesn't
            //need that visual cue, so we're leaving this empty
        }

    }//receiveInfo

    public void setUpDumbPieces(StrategoGameState gameState) { //Untested 4/7/22, Unsure of proper location
        /*ArrayList<Unit> p1Troops = gameState.getP1Troops();
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
        }*/
    }
}//StrategoDumbCompPlayer

