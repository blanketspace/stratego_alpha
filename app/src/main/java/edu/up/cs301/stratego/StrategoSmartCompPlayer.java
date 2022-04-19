package edu.up.cs301.stratego;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

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
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public StrategoSmartCompPlayer(String name) {
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

        Unit[][] board = ((StrategoGameState) info).getGameboard();
    }

    public void setUpStrategy(int chosenStrat) {
        switch (chosenStrat) { //We have possible setups from a world champion stratego player
            case 0:
                //smth to fill the ranks properly
                break;
            case 1:
                //smth
                break;
            case 2:
                //smth
                break;
        }
    } //Do we *need* multiple setups? theyre kinda long...

    private void smartStrat(StrategoGameState gameState) { //UNTESTED
        ArrayList<Unit> p1Troops = gameState.getP1Troops();
        int i = 0, j = 0; //i is left/right, j is up/down
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0; //how many times each piece has placed

        for(Unit troop: p1Troops) {
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
    } //end smartStrat

    public void choosePiece() {} //returns a Unit, not void

    public int checkFlanks(Unit chosen) {return 0;}

    public int chooseMoveDir(Unit chosen) {return 0;}


}
