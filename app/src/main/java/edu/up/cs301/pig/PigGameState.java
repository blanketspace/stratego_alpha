package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {
    private int playerTurn;
    private int player0Score;
    private int player1Score;
    private int runTotal; //current running total to be added should the player hold
    private int currentDieVal;

    /**
     * PigGameState ctor
     *
     *  initializes all variables for new game (at the start)
     */
    public PigGameState(){
        player0Score = 0;
        player1Score = 0;
        runTotal = 0;
        currentDieVal = 0;
        playerTurn = 0;
    }

    /**
     * PigGameState copy ctor
     *
     * @param orig  the GameState to be copied
     */
    public PigGameState(PigGameState orig){
        //copy ctor
        playerTurn = orig.playerTurn;
        player1Score = orig.player1Score;
        player0Score = orig.player0Score;
        runTotal = orig.runTotal;
        currentDieVal = orig.currentDieVal;
    }

    /**getters for each instance*/

    public int getPlayerTurn(){
        return playerTurn;
    }

    public int getPlayer0Score(){
        return player0Score;
    }

    public int getPlayer1Score(){
        return player1Score;
    }

    public int getCurrentDieVal(){
        return currentDieVal;
    }

    public int getRunTotal() {
        return runTotal;
    }


    /**setters for each instance variable */

    public void setCurrentDieVal(int currentDieVal) {
        this.currentDieVal = currentDieVal;
    }

    public void setPlayer0Score(int player0Score) {
        this.player0Score = player0Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setRunTotal(int runTotal) {
        this.runTotal = runTotal;
    }
}

