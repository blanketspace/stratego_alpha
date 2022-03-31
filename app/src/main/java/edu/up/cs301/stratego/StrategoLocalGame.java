package edu.up.cs301.stratego;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * StrategoLocalGame
 *
 * @author Anne Marie Blank
 * @author Harry Vu
 * @author Vincent Truong
 * @author Kathryn Weidman
 *
 * @version 3/29/2022
 */
public class StrategoLocalGame extends LocalGame {

    private StrategoGameState goldie;

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

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

    }

    @Override
    protected String checkIfGameOver() {
        if (goldie.isFlagCaptured()) {
            return "Flag Captured. Player Wins";
        }
        else {
            return null;
        }

    }

    @Override
    protected boolean makeMove(GameAction action) {
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

//TODO: up/down/l/r actions will be classes-- switch case will likely need to be modified to accomodate
       /* public boolean movePiece(int playerID, Unit chosen, int dir) {
            int chosenY = chosen.getyLoc();
            int chosenX = chosen.getxLoc();

            //1 = up, 2 = down, 3 = left, 4 = right
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
                            chosen.setStatus(false);
                            gameboard[chosenX][chosenY] = null;
                            legal = true;
                        }
                        else {
                            gameboard[chosenX][chosenY].setStatus(false);
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
                            chosen.setStatus(false);  //you died
                            gameboard[chosenX][chosenY] = null;  //empty space you were just in
                            legal = true;
                        }
                        else {
                            gameboard[chosenX][chosenY + 1].setStatus(false);  //they died
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
                            chosen.setStatus(false);  //you died
                            gameboard[chosenX][chosenY] = null;
                            legal = true;
                        }
                        else {
                            gameboard[chosenX - 1][chosenY].setStatus(false);  //they died
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
                                    chosen.setStatus(false);  //you died
                                    gameboard[chosenX][chosenY] = null;
                                    legal = true;
                                }
                                else if (opponentRank <= chosen.getRank()) {
                                    gameboard[chosenX + 1][chosenY].setStatus(false);  //they died
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

}//class StrategoLocalGame
