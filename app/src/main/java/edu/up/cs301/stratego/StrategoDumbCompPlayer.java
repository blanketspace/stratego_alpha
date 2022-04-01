package edu.up.cs301.stratego;


import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.stratego.actions.MovePieceAction;

/**
 * StrategoDumbCompPlayer
 *
 * @author blank
 * @author Harry Vu,
 * @author Vincent Truong,
 * @author Kathryn Weidman
 * @version 3/18/2022
 */
public class StrategoDumbCompPlayer extends GameComputerPlayer {

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public StrategoDumbCompPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        StrategoGameState gameState = new StrategoGameState((StrategoGameState) info);
        if (!(info instanceof StrategoGameState)) {
            return;
        }

        if (gameState.getWhoseTurn() != playerNum) {
            return;
        }

        Random randGen = new Random();
        int randomDir = randGen.nextInt(4);
        Unit[][] board = ((StrategoGameState) info).getGameboard();
        int randomX = randGen.nextInt(9);
        int randomY = randGen.nextInt(9);
        sleep(1);
        //if conditions for checking whether or not the movement is valid
        if(randomDir == 0){
            if (board[randomX][randomY].getOwnerID() == playerNum){
                //moving up
            }
        }
        if(randomDir == 1){
            if (board[randomX][randomY].getOwnerID() == playerNum){
                //moving down
            }
        }
        if(randomDir == 2){
            if (board[randomX][randomY].getOwnerID() == playerNum){
                //moving left
            }
        }
        if(randomDir == 3){
            if (board[randomX][randomY].getOwnerID() == playerNum){
                //moving right
            }
        }

        game.sendAction(new MovePieceAction(this));

    }
}//StrategoDumbCompPlayer

