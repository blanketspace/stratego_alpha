    package edu.up.cs301.stratego;

    import android.util.Log;

    import edu.up.cs301.game.GameComputerPlayer;
    import edu.up.cs301.game.GamePlayer;
    import edu.up.cs301.game.LocalGame;
    import edu.up.cs301.game.actionMsg.GameAction;
    import edu.up.cs301.stratego.actions.DownAction;
    import edu.up.cs301.stratego.actions.EndScoutAction;
    import edu.up.cs301.stratego.actions.LeftAction;
    import edu.up.cs301.stratego.actions.RightAction;
    import edu.up.cs301.stratego.actions.ScoutBonusAction;
    import edu.up.cs301.stratego.actions.SelectPieceAction;
    import edu.up.cs301.stratego.actions.SurrenderAction;
    import edu.up.cs301.stratego.actions.UpAction;

    /**
     * StrategoLocalGame
     *
     * class to define the local game for Stratego
     *
     * @author Anne Marie Blank
     * @author Harry Vu
     * @author Vincent Truong
     * @author Kathryn Weidman
     *
     * @version 4/22/2022
     */
    public class StrategoLocalGame extends LocalGame {

        private StrategoGameState goldie;
        private Unit chosen;

        /** ctor */
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
         *
         * @param playerIdx   the index of the player attempting to make the move
         */
        @Override
        protected boolean canMove(int playerIdx) {
            if (playerIdx == goldie.getWhoseTurn()) {
                return true;
            }
            else {
                return false;
            }
        }//canMove

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

        }//sendUpdatedStateTo


        /**
         * checkIfGameOver
         *
         * checks to see if the game has ended (flag is captured)
         *
         * @return  a string with the end game message
         */
        @Override
        protected String checkIfGameOver() {
            if (goldie.isFlagCaptured()) {
                Log.i("FLAG_CAPTURED", "jaslkgja;eorinb/ldkfn;aoirg");
                String winner = null;
                if (goldie.getWhoseTurn() == 0) {
                    winner = "Computer Player";
                }
                else {
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
         * method to handle all GameActions made by a player
         *
         * @param action
         * 			The move that the player has sent to the game
         * @return
         */
        @Override
        protected boolean makeMove(GameAction action) {
            Log.i("MAKE_MOVE_CALLED", "afgerbkdfblkajd;fgjadfnba;lf");
            if (action instanceof SelectPieceAction) {
                Log.i("SELECT_PIECE", "s;fljwogiej;rkn;aldrh;alrdk");
                SelectPieceAction spa = (SelectPieceAction) action;
                Unit equiv = goldie.findEquivUnit(spa.selected);

                if (equiv != null) {
                    goldie.clearSelection();
                    equiv.setSelected(true);

                    if (equiv.getRank() == Unit.SCOUT && goldie.getWhoseTurn() != 1) {
                        goldie.setEndScout(false);
                    }
                    else {
                        goldie.setEndScout(true);
                    }
                }

                return true;
            }
            else if (action instanceof EndScoutAction) {
                goldie.setEndScout(true);
                if (goldie.getWhoseTurn() == 0) {
                    goldie.setWhoseTurn(1);
                }
                else {
                    goldie.setWhoseTurn(0);
                }
                return true;
            }
            else if (action instanceof SurrenderAction) {
                goldie.setFlagCaptured(true);
                return true;
            }
            else {  //every other action is caught here
                //the following loops go through each list of troops
                //and finds the Unit that has been selected


                chosen = null;
                if (goldie.getWhoseTurn() == 1) {
                    for (Unit u: goldie.getP1Troops()) {
                        if (u.getSelected()) {
                            chosen = u;
                            break;
                        }
                    }
                }
                else {
                    for (Unit u: goldie.getP2Troops()) {
                        if (u.getSelected()) {
                            chosen = u;
                            break;
                        }
                    }
                }
              //final check to make sure the move is legal
                if (chosen != null && chosen.getOwnerID() != goldie.getWhoseTurn()) {
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
                        if (goldie.notScoutTurn()) {
                            //it's not the scout's turn, so switch players
                            if (goldie.getWhoseTurn() == 0) {
                                goldie.setWhoseTurn(1);
                            }
                            else {
                                goldie.setWhoseTurn(0);
                            }
                            goldie.clearSelection();
                        }
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
     * @param chosen   the unit we intend to move
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
         * */
        int newX = chosenX;
        int newY = chosenY;

        //the following ifs set chosenX/Y to the correct direction
        //(based on which side of the board the player is on)
        if (playerID == 1) {
            //assuming player 1 is at the bottom of the board
            switch(dir) {
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
        else if (playerID == 0) {
            switch(dir) {
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
        if (this.inBounds(newX, newY)) {
            if (gameboard[newY][newX] == null) {  //spot is already empty, go ahead and take it
                chosen.setyLoc(newY);
                chosen.setxLoc(newX);
                gameboard[newY][newX] = chosen;  //fill that empty spot
                gameboard[chosenY][chosenX] = null;
                Log.i("SPOT_TAKEN", "WAS_EMPTY_BEFORE_ARGAEJR;BKN;FJBNKDJHSLDJHLKSJTDHKSKLE");

            }
            else if (gameboard[newY][newX].getRank() == Unit.WATER) {
                //you can't walk on water, therefore do nothing
                goldie.setEndScout(true);
            }
            else if (gameboard[newY][newX].getOwnerID() == goldie.getWhoseTurn()) {
                //the unit in the new space is not owned by the player trying to move
                if (gameboard[newY][newX].getRank() == Unit.BOMB) {
                    if (chosen.getRank() == Unit.MINER) {
                        //you are a miner, disarm the bomb
                        gameboard[newY][newX].setDead(true);

                        gameboard[newY][newX] = null;

                        chosen.setxLoc(newX);
                        chosen.setyLoc(newY);
                        gameboard[newY][newX] = chosen;  //fill its old spot
                        gameboard[chosenY][chosenX] = null;  //empty your old spot

                        Log.i("BOMB_DISARMED", "alksjdgaljlkah");
                    }
                    else {
                        //you are not a miner, you explode
                        goldie.setEndScout(true);  //ensure the scout ends its turn
                        gameboard[chosenY][chosenX] = null;
                        chosen.setDead(true);
                        Log.i("UNIT_EXPLODED", "lakjfdklgajdlfkhj");
                    }
                }
                else if (gameboard[newY][newX].getRank() == Unit.FLAG) {
                    //game over!
                    goldie.setEndScout(true);  //ensure the scout ends its turn
                    goldie.setFlagCaptured(true);

                }
                else if (isSpyAttack(newX, newY, chosen) || gameboard[newY][newX].getRank() != Unit.MARSHAL) {
                    //not a bomb, free to attack
                    int opponentRank = gameboard[newY][newX].getRank();
                    if (opponentRank > chosen.getRank()) {   //they won
                        chosen.setDead(true);            //you die

                        gameboard[chosenY][chosenX] = null; //empty your spot
                        Log.i("OPP_WON_BATTLE", "akjglkjdlhskgkj");

                    }
                    else {  //you won
                        gameboard[newY][newX].setDead(true);   //they die
                        gameboard[chosenY][chosenX] = null;   //empty your spot
                        gameboard[newY][newX] = null;        //they empty their spot
                        chosen.setxLoc(newX);
                        chosen.setyLoc(newY);
                        gameboard[newY][newX] = chosen;       //fill their old spot
                        Log.i("ATTACKER_WON_BATTLE", "sjfgklsjldkhg");
                    }
                    goldie.setEndScout(true);  //ensure the scout ends its turn
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
     * helper method to check that a movement is in bounds of the board
     *
     * @return  true if proposed x,y is in the bounds of the array
     */
    public boolean inBounds(int x, int y) {
        boolean bounds = false;
        if (x >= 0 && x < 10) {
            if (y >= 0 && y < 10) {
                bounds = true;
            }
        }
        else {
            bounds = false;
        }
        return bounds;
    }//inBounds

    /**
     * isSpyAttack
     *
     * checks to see if the Unit is a Spy (for special game rules)
     *
     * @return true if it's a legal spy attack, false if not
     */
    public boolean isSpyAttack(int x, int y, Unit chosen) {
        if (goldie.getGameboard()[y][x].getRank() == Unit.MARSHAL && chosen.getRank() == Unit.SPY ) {
            return true;
        }
        else {
            return false;
        }
    }//isSpyAttack

    }//class StrategoLocalGame
