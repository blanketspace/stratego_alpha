package edu.up.cs301.stratego;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.stratego.actions.DownAction;
import edu.up.cs301.stratego.actions.LeftAction;
import edu.up.cs301.stratego.actions.RightAction;
import edu.up.cs301.stratego.actions.SelectPieceAction;
import edu.up.cs301.stratego.actions.UpAction;

/**
 * StrategoDumbCompPlayer
 *
 * @author Anne Marie Blank,
 * @author Harry Vu,
 * @author Vincent Truong,
 * @author Kathryn Weidman
 * @version 4/14/2022
 *
 * Reference for smart AI setup from a world champion Stratego player
 *      https://www.ultraboardgames.com/stratego/setups.php
 */

public class StrategoSmartCompPlayer extends GameComputerPlayer {


    private StrategoGameState copyGS;
    private int dir;


    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public StrategoSmartCompPlayer(String name) {
        super(name);
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
        if (y > 0) {  //changed from y < 0  4/21 4:21
            Unit dest = board[y - 1][x];
            if (dest == null ||
                    (dest.getOwnerID() != unit.getOwnerID() && dest.getRank() == Unit.FLAG) ||
                    (dest.getOwnerID() != unit.getOwnerID() && dest.getRank() <= unit.getRank())) {
                temp.add(1);
            }
        }

        if (y < 9) { //down
            Unit dest = board[y + 1][x];
            if (dest == null ||
                    (dest.getOwnerID() != unit.getOwnerID() && dest.getRank() == Unit.FLAG) ||
                    (dest.getOwnerID() != unit.getOwnerID() && dest.getRank() <= unit.getRank())) {
                temp.add(2);
            }
        }
        if (x > 0) { //left   changed from x < 0  4/21 4:22
            Unit dest = board[y][x - 1];
            if (dest == null ||
                    (dest.getOwnerID() != unit.getOwnerID() && dest.getRank() == Unit.FLAG) ||
                    (dest.getOwnerID() != unit.getOwnerID() && dest.getRank() <= unit.getRank())) {
                temp.add(3);
            }
        }
        if (x < 9) { // right
            Unit dest = board[y][x + 1];
            if (dest == null ||
                    (dest.getOwnerID() != unit.getOwnerID() && dest.getRank() == Unit.FLAG) ||
                    (dest.getOwnerID() != unit.getOwnerID() && dest.getRank() <= unit.getRank())) {
                temp.add(4);
            }
        }


        //convert the temp arraylist to an array of int
        if (temp.size() == 0) {return null;
        }
        else{
            int[] result = new int[temp.size()];
            for(int i = 0; i < temp.size(); ++i) {
                result[i] = temp.get(i);
            }

            return result;
        }
    }//legalDirs



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
                while(dir < 0) {  //TODO: what's this for?

                    int bound = copyGS.getP1Troops().size() - 1;
                    int randomX = randGen.nextInt(bound);
                    // int randomY = randGen.nextInt(9);
                    selected = copyGS.getUnit(0, randomX);


                    //loop to ensure a legal piece is chosen
                    while(selected.getStatus() == true || selected.getRank() == 11 || selected.getRank() == 12){
                        randomX = randGen.nextInt(39);
                        selected = copyGS.getUnit(0, randomX);
                    }


                    //added a legalDirs method that checks for legal moves that the dumb ai can make
                    int[] options = this.legalDirs(selected);
                    int x = selected.getxLoc();
                    int y = selected.getyLoc();
                    int priorDir = 0;
                    if (options != null) {
                        //whichOption is index of the options array (randomly selects an index from options)
                        //int whichOption = randGen.nextInt(options.length);
                        //using the whichOptions index, we will use it to get a dir from the options array
                        for(int i = 0; i < options.length; i++){
                            if(options[i] == 1){
                                Unit possible = board[y-1][x];
                                if((possible != null && possible.getRank() == Unit.FLAG) ||
                                        (possible != null && possible.getRank() <= selected.getRank())){
                                    priorDir = 1;
                                    break;
                                }
                            }
                            else if(options[i] == 2){
                                Unit possible = board[y+1][x];
                                if((possible != null && possible.getRank() == Unit.FLAG) ||
                                        (possible != null && possible.getRank() <= selected.getRank())){
                                    priorDir = 2;
                                    break;
                                }

                            }
                            else if(options[i] == 3){
                                Unit possible = board[y][x-1];
                                if((possible != null && possible.getRank() == Unit.FLAG) ||
                                        (possible != null && possible.getRank() <= selected.getRank())){
                                    priorDir = 3;
                                    break;
                                }
                            }
                            else if(options[i] == 4){
                                Unit possible = board[y][x+1];
                                if((possible != null && possible.getRank() == Unit.FLAG) ||
                                        (possible != null && possible.getRank() <= selected.getRank())){
                                    priorDir = 4;
                                    break;
                                }
                            }
                        }
                        if(priorDir != 0){
                            dir = priorDir;
                        }
                        else{
                            int whichOption = randGen.nextInt(options.length);
                            dir = options[whichOption];
                        }
                    }
                }

                //TODO: put this back in!!
                //              sleep(2000);

                if (selected != null){
                    SelectPieceAction spa = new SelectPieceAction(this, board[selected.getyLoc()][selected.getxLoc()]);
                    game.sendAction(spa);
                    Log.i("COMP_SELECTED_Piece", "asjgarjigoaet;hdjlksgjdfg");
                }

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

    }

    public void setUpStrategy(int chosenStrat) {
        switch (chosenStrat) { //We have possible setups from a world champion stratego player
            case 0:
               //invoke smartStrat1
                break;
            case 1:
                //invoke smartStrat2
                break;
            case 2:
                //Random setup?
                break;
        }
    }

    private void smartStrat1(StrategoGameState gameState) { //UNTESTED
        ArrayList<Unit> p1Troops = gameState.getP1Troops();
        int i = 0, j = 0; //i is left/right, j is up/down
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0; //how many times each piece has placed

        //this loop assigns each Unit a location on the board based on the Unit's rank
        for (Unit troop: p1Troops) {
            troop.setDead(false);
            switch (troop.getRank()) {
                case 1:
                    i = 7;
                    j = 1;
                    break;
                case 2:
                    j = 0;
                    switch (a) {
                        case 0:
                            i = 1;
                            break;
                        case 1:
                            i = 5;
                            break;
                        case 2:
                            i = 7;
                            break;
                        case 3:
                            i = 8;
                            break;
                        case 4:
                            i = 0;
                            j = 1;
                            break;
                        case 5:
                            i = 4;
                            j = 1;
                            break;
                        case 6:
                            i = 6;
                            j = 3;
                            break;
                        case 7:
                            i = 9;
                            j = 3;
                            break;
                    }
                    a++;
                    break; //end case 2
                case 3:
                   j = 3;
                   switch (b) {
                       case 1:
                           i = 0;
                           break;
                       case 2:
                           i = 1;
                           break;
                       case 3:
                           i = 5;
                           break;
                       case 4:
                           i = 9;
                           break;
                       case 0:
                           i = 3;
                           j = 0;
                           break;
                    }
                    b++;
                    break; //end case 3
                case 4:
                    j = 2;
                    switch (c) {
                        case 1:
                            i = 0;
                            break;
                        case 2:
                            i = 7;
                            break;
                        case 3:
                            i = 9;
                            break;
                        case 0:
                            i = 8;
                            j = 1;
                            break;
                    }
                    c++;
                    break; //end case 4
                case 5:
                    switch (d) {
                        case 0:
                            i = 6;
                            j = 0;
                            break;
                        case 1:
                            i = 9;
                            j = 1;
                            break;
                        case 2:
                            i = 2;
                            j = 2;
                            break;
                        case 3:
                            i = 4;
                            j = 2;
                            break;
                    }
                    d++;
                    break; //end case 5
                case 6:
                    j = 0;
                    switch (e) {
                        case 1:
                            i = 0;
                            break;
                        case 2:
                            i = 4;
                            break;
                        case 3:
                            i = 9;
                            break;
                        case 0:
                            i = 1;
                            j = 2;
                            break;
                    }
                    e++;
                    break; //end case 6
                case 7:
                    j = 1;
                    switch (f) {
                        case 0:
                            i = 2;
                            break;
                        case 1:
                            i = 3;
                            break;
                        case 2:
                            i = 6;
                            j = 2;
                            break;
                    }
                    f++;
                    break; //end case 7
                case 8:
                    switch (g) {
                        case 0:
                            i = 1;
                            j = 1;
                            break;
                        case 1:
                            i = 5;
                            j = 2;
                            break;
                    }
                    g++;
                    break; //end case 8
                case 9:
                    i = 5;
                    j = 1;
                    break; //end case 9
                case 10:
                    i = 2;
                    j = 0;
                    break; //end case 10
                case 11:
                    j = 3;
                    switch (h) {
                        case 0:
                            i = 2;
                            break;
                        case 1:
                            i = 4;
                            break;
                        case 2:
                            i = 7;
                            break;
                        case 3:
                            i = 8;
                            j = 2;
                            break;
                        case 4:
                            i = 3;
                            j = 2;
                            break;
                        case 5:
                            i = 7;
                            j = 1;
                            break;
                    }
                    h++;
                    break; //end case 11
                case 12:
                    i = 3;
                    j = 3;
                    break; //end case 12
            } //X and Y for placePiece from dumbComp setup method
            gameState.placePiece(playerNum, troop, i * troop.UNIT_WIDTH, (j + 6) * troop.UNIT_HEIGHT);
        }
    } //end smartStrat1

    private void smartStrat2(StrategoGameState gameState) { //UNTESTED
        ArrayList<Unit> p1Troops = gameState.getP1Troops();
        int i = 0, j = 0; //i is left/right, j is up/down
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0; //how many times each piece has placed

        //this loop assigns each Unit a location on the board based on the Unit's rank
        for (Unit troop: p1Troops) {
            troop.setDead(false);
            switch (troop.getRank()) {
                case 1:
                    i = 6;
                    j = 2;
                    break;
                case 2:
                    j = 0;
                    switch (a) {
                        case 0:
                            i = 1;
                            break;
                        case 1:
                            i = 2;
                            break;
                        case 2:
                            i = 3;
                            break;
                        case 3:
                            i = 5;
                            break;
                        case 4:
                            i = 4;
                            j = 1;
                            break;
                        case 5:
                            i = 8;
                            j = 1;
                            break;
                        case 6:
                            i = 0;
                            j = 2;
                            break;
                        case 7:
                            i = 8;
                            j = 2;
                            break;
                    }
                    a++;
                    break; //end case 2
                case 3:
                    j = 3;
                    switch (b) {
                        case 0:
                            i = 0;
                            break;
                        case 1:
                            i = 6;
                            break;
                        case 2:
                            i = 7;
                            break;
                        case 3:
                            i = 8;
                            break;
                        case 4:
                            i = 6;
                            j = 0;
                            break;
                    }
                    b++;
                    break; //end case 3
                case 4:
                    switch (c) {
                        case 0:
                            i = 3;
                            j = 1;
                            break;
                        case 1:
                            i = 9;
                            j = 1;
                            break;
                        case 2:
                            i = 2;
                            j = 2;
                            break;
                        case 3:
                            i = 5;
                            j = 3;
                            break;
                    }
                    c++;
                    break; //end case 4
                case 5:
                    switch (d) {
                        case 0:
                            i = 7;
                            j = 0;
                            break;
                        case 1:
                            i = 0;
                            j = 1;
                            break;
                        case 2:
                            i = 1;
                            j = 2;
                            break;
                        case 3:
                            i = 4;
                            j = 2;
                            break;
                    }
                    d++;
                    break; //end case 5
                case 6:
                    j = 0;
                    switch (e) {
                        case 0:
                            i = 0;
                            break;
                        case 1:
                            i = 4;
                            break;
                        case 2:
                            i = 8;
                            break;
                        case 3:
                            i = 5;
                            j = 2;
                            break;
                    }
                    e++;
                    break; //end case 6
                case 7:
                    switch (f) {
                        case 0:
                            i = 7;
                            j = 2;
                            break;
                        case 1:
                            i = 9;
                            j = 2;
                            break;
                        case 2:
                            i = 7;
                            j = 3;
                            break;
                    }
                    f++;
                    break; //end case 7
                case 8:
                    switch (g) {
                        case 0:
                            i = 6;
                            j = 1;
                            break;
                        case 1:
                            i = 7;
                            j = 1;
                            break;
                    }
                    g++;
                    break; //end case 8
                case 9:
                    i = 5;
                    j = 1;
                    break; //end case 9
                case 10:
                    i = 9;
                    j = 0;
                    break; //end case 10
                case 11:
                    j = 3;
                    switch (h) {
                        case 0:
                            i = 1;
                            break;
                        case 1:
                            i = 2;
                            break;
                        case 2:
                            i = 4;
                            break;
                        case 3:
                            i = 3;
                            j = 2;
                            break;
                        case 4:
                            i = 1;
                            j = 1;
                            break;
                        case 5:
                            i = 2;
                            j = 1;
                            break;
                    }
                    h++;
                    break; //end case 11
                case 12:
                    i = 3;
                    j = 3;
                    break; //end case 12
            } //X and Y for placePiece from dumbComp setup method
            gameState.placePiece(playerNum, troop, i * troop.UNIT_WIDTH, (j + 6) * troop.UNIT_HEIGHT);
        }
    } //end smartStrat2

} //end StrategoSmartCompPlayer class
