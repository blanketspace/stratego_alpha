package edu.up.cs301.stratego;


import java.util.ArrayList;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * StrategoGameState
 *
 * class to store and describe the current state of the game
 *
 * @author Anne Marie Blank,
 * @author Harry Vu,
 * @author Vincent Truong,
 * @author Kathryn Weidman
 * @version 4/13/2022
 */
public class StrategoGameState extends GameState {

    protected StrategoGameState state;

    private int whoseTurn;

    private Unit[][] gameboard;
    private int roundNumber;     //will be initialized to zero, changed to indicate who's turn it is
    private double timeElapsed;  //for the timer

    private ArrayList<Unit> p1Troops;
    private ArrayList<Unit> p2Troops;

    private boolean flagCaptured;


    /**
     * ctor
     *
     * defines the state of the game on startup
     */
    public StrategoGameState() {
        gameboard = new Unit[10][10];
        roundNumber = 0;
        whoseTurn = 0;
        timeElapsed = 0.0;
        flagCaptured = false;
        p1Troops = new ArrayList<Unit>();
        p2Troops = new ArrayList<Unit>();

        this.fillRanks(0);
        this.fillRanks(1);

        //loop through each "hand", fill gameboard array

        //p1 aka the "top" half of board
        //i is the x, which is the row
        //j is the y, which is the column
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                gameboard[i][j] = p1Troops.get(10*i + j); //10*i + j necessary to keep p1Troops on track
                Unit test = gameboard[i][j];
                // set x and y
                //TODO: this is something that I'm concerned about
                test.setxLoc(j);  //changed from j*Unit_Width
                test.setyLoc(i);
            }
        }
        //p2 aka "bottom" half of board
        int k = 0; //for math purposes-- so we can keep formulas from prev loop
        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                gameboard[i][j] = p2Troops.get(10*k + j); //10*k + j keeps us moving through arraylist
                Unit test = gameboard[i][j];
                // set x and y
                test.setxLoc(j);
                test.setyLoc(i);
            }
            k++;
        }

    }//ctor


    /**
     * fillRanks
     *
     * helper method to fill the player's Troop Arrays
     *
     * @param pID   the id of the player whose array needs filling
     */
    public void fillRanks(int pID) {
        if (pID == 0) {
            p1Troops.add(new Unit(0, Unit.MARSHAL));
            p1Troops.add(new Unit(0, Unit.GENERAL));
            p1Troops.add(new Unit(0, Unit.FLAG));
            p1Troops.add(new Unit(0, Unit.SPY));

            p1Troops.add(new Unit(0, Unit.COLONEL));
            p1Troops.add(new Unit(0, Unit.COLONEL));

            p1Troops.add(new Unit(0, Unit.MAJOR));
            p1Troops.add(new Unit(0, Unit.MAJOR));
            p1Troops.add(new Unit(0, Unit.MAJOR));

            for (int i = 0; i < 4; i++) {
                p1Troops.add(new Unit(0, Unit.SERGEANT));
            }

            for (int i = 0; i < 4; i++) {
                p1Troops.add(new Unit(0, Unit.LIEUTENANT));
            }

            for (int i = 0; i < 4; i++) {
                p1Troops.add(new Unit(0, Unit.CAPTAIN));
            }

            for (int i = 0; i < 5; i++) {
                p1Troops.add(new Unit(0, Unit.MINER));
            }

            for (int i = 0; i < 8; i++) {
                p1Troops.add(new Unit(0, Unit.SCOUT));
            }

            for (int i = 0; i < 6; i++) {
                p1Troops.add(new Unit(0, Unit.BOMB));
            }

        }
        else if (pID == 1) {
            p2Troops.add(new Unit(1, Unit.MARSHAL));
            p2Troops.add(new Unit(1, Unit.GENERAL));
            p2Troops.add(new Unit(1, Unit.FLAG));
            p2Troops.add(new Unit(1, Unit.SPY));

            p2Troops.add(new Unit(1, Unit.COLONEL));
            p2Troops.add(new Unit(1, Unit.COLONEL));

            p2Troops.add(new Unit(1, Unit.MAJOR));
            p2Troops.add(new Unit(1, Unit.MAJOR));
            p2Troops.add(new Unit(1, Unit.MAJOR));

            p2Troops.add(new Unit(1, Unit.SERGEANT));
            p2Troops.add(new Unit(1, Unit.SERGEANT));
            p2Troops.add(new Unit(1, Unit.SERGEANT));
            p2Troops.add(new Unit(1, Unit.SERGEANT));

            p2Troops.add(new Unit(1, Unit.LIEUTENANT));
            p2Troops.add(new Unit(1, Unit.LIEUTENANT));
            p2Troops.add(new Unit(1, Unit.LIEUTENANT));
            p2Troops.add(new Unit(1, Unit.LIEUTENANT));

            p2Troops.add(new Unit(1, Unit.CAPTAIN));
            p2Troops.add(new Unit(1, Unit.CAPTAIN));
            p2Troops.add(new Unit(1, Unit.CAPTAIN));
            p2Troops.add(new Unit(1, Unit.CAPTAIN));

            for (int i = 0; i < 5; i++) {
                p2Troops.add(new Unit(1, Unit.MINER));
            }

            for (int i = 0; i < 8; i++) {
                p2Troops.add(new Unit(1, Unit.SCOUT));
            }

            for (int i = 0; i < 6; i++) {
                p2Troops.add(new Unit(1, Unit.BOMB));
            }
        }
    }//fillRanks


    /**
     * copy ctor
     *
     * @param orig  the original GameState to be copied
     * @Override
     */
    public StrategoGameState(StrategoGameState orig){
        this.gameboard = new Unit[10][10];

        //initialize new gameboard to be just like the old one
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                gameboard[i][j] = orig.gameboard[i][j];
            }
        }
        flagCaptured = orig.flagCaptured;
        whoseTurn = orig.whoseTurn;
        roundNumber = orig.roundNumber;
        p1Troops = new ArrayList<>();
        p2Troops = new ArrayList<>();

        //makes a deep copy of the the troops arraylist
        for (int i = 0; i < orig.p1Troops.size(); i++) {
            this.p1Troops.add(orig.p1Troops.get(i));
        }
        for (int i = 0; i < orig.p2Troops.size(); i++) {
            this.p2Troops.add(orig.p2Troops.get(i));
        }

    }//copy ctor


    /**
     * toString
     *
     * @return a String representation of the current StrategoGameState
     */
    @Override
    public String toString() {
        String foo = null;
        for (int i = 0; i < p1Troops.size(); i++) {
            foo = "" + p1Troops.get(i).nameRank() + " ";
        }
        String bar = null;
        for (int i = 0; i < p2Troops.size(); i++) {
            bar = "" + p2Troops.get(i).nameRank() + " ";
        }
        return "Turn:" + whoseTurn + "Player 1 Troops: " + foo
                + "Player 2 Troops: " + bar + "Time Elapsed: " + timeElapsed
                + "Flag Captured?: " + flagCaptured;
    }//toString


    public String boardToString() {
        StringBuilder result = new StringBuilder();
        result.append(" \n");
        for(int i = 0; i < gameboard.length; i++){
            for(int j = 0; j < gameboard[i].length; j++){
                if(gameboard[i][j] == null){
                    result.append("..");
                }
                else{
                    if(gameboard[i][j].getRank() < 10){
                        result.append(" ");
                    }
                    result.append(gameboard[i][j].getRank());
                }
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }//boardToString


    /**
     * findEquivUnit
     *
     * Given a Unit from another game state, find the one in this game state
     * that is the same (if it exists)
     *
     * @param other   the Unit we're attempting to find a match for
     * @return        the Unit that matches the one passed in
     */
    public Unit findEquivUnit(Unit other) {
        ArrayList<Unit> searchMe = this.p1Troops;

        if(other != null){
            if (other.getOwnerID() == 1) {
                searchMe = this.p2Troops;
            }
            for(Unit u : searchMe) {
                if ((u.getxLoc() == other.getxLoc()) && (u.getyLoc() == other.getyLoc())) {
                    return u;
                }
            }
        }


        return null;
    }//findEquivUnit

    //Nux note

    /**
     * selectPiece
     *
     *
     * @param playerID   the id of the player attempting to choose
     * @param chosenP    the Unit being selected
     */
    public boolean selectPiece(int playerID, Unit chosenP) {
        if (chosenP.getOwnerID() == playerID) {
            clearSelection(playerID);  //sets all Units to false
            chosenP.setSelected(true); //sets selection to true
            return true;
        }
        else {
            return false;
        }
    }//selectPiece


    /**
     * clearSelection
     *
     * sets the isSelected value of all Units in the player's "hand" to false
     *
     * @param playerId  the ID of the user attempting to make a selection
     */
    public void clearSelection(int playerId) {
        switch (playerId) {
            case 0:
                for (int i= 0; i < p1Troops.size(); i++) {
                    p1Troops.get(i).setSelected(false);
                }
                break;
            case 1:
                for (int i= 0; i < p2Troops.size(); i++) {
                    p2Troops.get(i).setSelected(false);
                }
                break;
        }
    }//clearSelection


    /**
     * getSelectedUnit
     *
     * finds the Unit in the gameboard array that is selected
     *
     * @return   returns the Unit on the gameboard that is currently selected
     */
    public Unit getSelectedUnit(){
        Unit selected = null;
        for(int i = 0; i < this.gameboard.length; i++){
            for(int j = 0; j < this.gameboard.length; j++){
                if(gameboard[i][j] != null && gameboard[i][j].getSelected()){
                    //the above condition is met when the Unit at that loc /is/ selected
                    selected = gameboard[j][i];
                }
            }
        }
        return selected;  //CAUTION: could return false; this is handled in SLocalGame
    }//getSelectedUnit


    /**
     * placePiece
     *
     * meant for the beginning stage of the game, when players
     * move their pieces from the starting location (graveyard) and onto the board
     *
     * @param playerID  the id of the player making the move
     * @param unit      the unit they're moving
     * @param x         x coord of new location
     * @param y         y coord of new location
     * @return          true if alive and movement is valid, false if not
     */
    public boolean placePiece(int playerID, Unit unit, int x, int y) {
        if (unit.getStatus()) {
            if (playerID == 0 && y < 4) {  //< 4 is for boundary purposes, ensures piece is on your side
                unit.setxLoc(x);
                unit.setxLoc(y);
                gameboard[y][x] = unit;
                return true;
            }
            else if (playerID == 1 && y > 5) {
                unit.setxLoc(x);
                unit.setxLoc(y);
                gameboard[y][x] = unit;
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }//placePiece


    /**
     * getUnit
     *
     * @param id    the id of the player whose "hand" you want to access
     * @param index the index you want to access
     * @return      the unit at the given index in the player's "hand"
     */
    public Unit getUnit(int id, int index) {
        if (id == 0) {
            return p1Troops.get(index);
        }
        else if (id == 1)
        {
            return p2Troops.get(index);
        }
        else {
            return p2Troops.get(index);
        }
    }//getUnit


    /**
     * isMinerAttack
     *
     * helper method to check for miner vs. bomb interactions
     *
     * @param chosenRank  the rank of the unit being checked
     * @return            true if the unit is a miner, false if else
     */
    public boolean isMinerAttack(int chosenRank) {
        if (chosenRank == Unit.MINER){
            return true;
        }
        else {
            return false;
        }
    }//isMinerAttack


    /** settters and getters */

    public void setWhoseTurn(int whoseTurn) {
        this.whoseTurn = whoseTurn;
    }

    public int getWhoseTurn() {
        return whoseTurn;
    }

    public Unit[][] getGameboard() {
        return gameboard;
    }

    public ArrayList<Unit> getP1Troops() {
        return p1Troops;
    }

    public ArrayList<Unit> getP2Troops() {
        return p2Troops;
    }

    public boolean isFlagCaptured() {
        return flagCaptured;
    }

    public void setFlagCaptured(boolean flagCaptured) {
        this.flagCaptured = flagCaptured;
    }



    public boolean movePiece(int playerID, Unit chosen, int dir) {
        int chosenY = chosen.getyLoc();
        int chosenX = chosen.getxLoc();

        //1 = up, 2 = down, 3 = left, 4 = right
        boolean legal = false;

        switch(dir) {
            case 1:  //aka "up"
                if (gameboard[chosenX][chosenY - 1] == null && chosenY - 1 >= 0) {
                    chosen.setyLoc(chosenY - 1);
                    gameboard[chosenX][chosenY] = chosen;
                    legal = true;
                }
                else if (gameboard[chosenX][chosenY - 1].getRank() == Unit.WATER) {
                    legal = false;
                }
                else if (gameboard[chosenX][chosenY - 1].getRank() == Unit.FLAG) {
                    legal = true;
                }
                else if (gameboard[chosenX][chosenY - 1].getRank() == Unit.BOMB) {
                    legal = isMinerAttack(chosen.getRank());
                }
                else if (gameboard[chosenX][chosenY].getOwnerID() != playerID) {
                    //attack
                    int opponentRank = gameboard[chosenX][chosenY].getRank();
                    if (opponentRank > chosen.getRank()) {
                        chosen.setDead(false);
                        gameboard[chosenX][chosenY] = null;
                        legal = true;
                    }
                    else {
                        gameboard[chosenX][chosenY].setDead(false);
                        gameboard[chosenX][chosenY] = null;
                        chosen.setxLoc(chosenY - 1);
                        gameboard[chosenX][chosenY] = chosen;
                        legal = true;
                    }
                }
                else {
                    legal = false;
                }
                break;
            //End case 1

            case 2:  //aka "down"
                if (gameboard[chosenX][chosenY + 1] == null && chosenY + 1 <= 9) {  //aka space is empty
                    chosen.setyLoc(chosenY + 1);  //move into space
                    gameboard[chosenX][chosenY] = chosen;
                    legal = true;
                }
                else if (gameboard[chosenX][chosenY + 1].getRank() == Unit.WATER) {
                    legal = false;  //can't walk on water
                }
                else if (gameboard[chosenX][chosenY + 1].getRank() == Unit.FLAG) {
                    legal = true;
                }
                else if (gameboard[chosenX][chosenY + 1].getRank() == Unit.BOMB) {
                    legal = isMinerAttack(chosen.getRank());
                }
                else if (gameboard[chosenX][chosenY + 1].getOwnerID() != playerID) {
                    //attack
                    int opponentRank = gameboard[chosenX][chosenY + 1].getRank();
                    if (opponentRank > chosen.getRank()) {
                        chosen.setDead(false);  //you died
                        gameboard[chosenX][chosenY] = null;  //empty space you were just in
                        legal = true;
                    }
                    else {
                        gameboard[chosenX][chosenY + 1].setDead(false);  //they died
                        gameboard[chosenX][chosenY] = null;  //empty the space you were just in
                        chosen.setyLoc(chosenY + 1);  //move into opponent's space
                        gameboard[chosenX][chosenY + 1] = chosen;  //report location to array
                        legal = true;
                    }
                }
                else {
                    legal = false;
                }
                break;
            //End case 2

            case 3:  //aka "left"
                if (gameboard[chosenX - 1][chosenY] == null && chosenX - 1 >= 0) {
                    chosen.setxLoc(chosenX - 1);
                    gameboard[chosenX - 1][chosenY] = chosen;
                    legal = true;
                }
                else if (gameboard[chosenX - 1][chosenY].getRank() == Unit.WATER) {
                    legal = false;
                }
                else if (gameboard[chosenX - 1][chosenY].getRank() == Unit.FLAG) {
                    legal = true;
                }
                else if (gameboard[chosenX - 1][chosenY].getRank() == Unit.BOMB) {
                    legal = isMinerAttack(chosen.getRank());
                }
                else if (gameboard[chosenX - 1][chosenY].getOwnerID() != playerID) {
                    //attack
                    int opponentRank = gameboard[chosenX - 1][chosenY].getRank();

                    if (opponentRank > chosen.getRank()) {
                        chosen.setDead(false);  //you died
                        gameboard[chosenX][chosenY] = null;
                        legal = true;
                    }
                    else {
                        gameboard[chosenX - 1][chosenY].setDead(false);  //they died
                        gameboard[chosenX][chosenY] = null;  //empty your spot
                        chosen.setxLoc(chosenX - 1);
                        gameboard[chosenX - 1][chosenY] = chosen;  //take their spot
                        legal = true;
                    }
                }
                else {
                    legal = false;
                }
                break;
            //End case 3

            case 4:  //aka "right"
                if (chosenX + 1 <= 9) {
                    if (gameboard[chosenX + 1][chosenY] == null && chosenX + 1 <= 9) {
                        chosen.setxLoc(chosenX + 1);
                        gameboard[chosenX + 1][chosenY] = chosen;
                        legal = true;
                    }
                    else if (gameboard[chosenX + 1][chosenY].getRank() == Unit.WATER) {
                        legal = false;
                    }
                    else if (gameboard[chosenX + 1][chosenY].getRank() == Unit.FLAG) {
                        legal = true;
                    }
                    else if (gameboard[chosenX + 1][chosenY].getRank() == Unit.BOMB) {
                        legal = isMinerAttack(chosen.getRank());
                    }
                    else {
                        if (gameboard[chosenX + 1][chosenY].getOwnerID() != playerID) {
                            //attack
                            int opponentRank = gameboard[chosenX + 1][chosenY].getRank();

                            if (opponentRank > chosen.getRank()) {
                                chosen.setDead(false);  //you died
                                gameboard[chosenX][chosenY] = null;
                                legal = true;
                            }
                            else if (opponentRank <= chosen.getRank()) {
                                gameboard[chosenX + 1][chosenY].setDead(false);  //they died
                                gameboard[chosenX][chosenY] = null;  //empty your space
                                chosen.setxLoc(chosenX + 1);
                                gameboard[chosenX + 1][chosenY] = chosen;  //take theirs
                                legal = true;
                            }
                            else {
                                legal = false;
                            }
                        }
                        else {
                            legal = false;
                        }

                    }
                }
                break;
            //End of case 4

            default:
                legal = false;
                break;
            //End of default case
        }//End switch-case*/

        //if (goldie.getWhoseTurn() == 0) {
        //goldie.setWhoseTurn(1);
        //}
        //else {
        //goldie.setWhoseTurn(0);
        //}

        //return legal;
        // }//movePiece
        return false;
    }//makeMove
}//StrategoGameState


