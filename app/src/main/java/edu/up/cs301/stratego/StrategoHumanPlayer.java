package edu.up.cs301.stratego;


import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.GameOverInfo;
import edu.up.cs301.game.infoMsg.IllegalMoveInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;
import edu.up.cs301.stratego.actions.DownAction;
import edu.up.cs301.stratego.actions.EndScoutAction;
import edu.up.cs301.stratego.actions.LeftAction;
import edu.up.cs301.stratego.actions.RightAction;
import edu.up.cs301.stratego.actions.ScoutBonusAction;
import edu.up.cs301.stratego.actions.SelectPieceAction;
import edu.up.cs301.stratego.actions.UpAction;


/**
 * StrategoHumanPlayer
 *
 * @author Anne Marie Blank
 * @author Harry Vu
 * @author Vincent Truong
 * @author Kathryn Weidman
 * @version 4/13/2022
 */
public class StrategoHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener {

    private Button up;
    private Button down;
    private Button left;
    private Button right;
    private Button exit;
    private Button end;
    private Button surrender;
    private Button help;

    private BoardView myBoardView;
    private TextView selectedRank;
    private TextView yourTroops;
    private TextView enemyTroops;

    //graveyard dead troop counters
    private TextView yourGeneralCount;
    private TextView yourColonelCount;
    private TextView yourLieutenantsCount;
    private TextView yourMinerCount;
    private TextView yourSpyCount;
    private TextView yourMarshalsCount;
    private TextView yourSargeantsCount;
    private TextView yourScoutCount;
    private TextView yourCaptainsCount;
    private TextView yourMajorCount;

    private TextView enemyGeneralCount;
    private TextView enemyColonelCount;
    private TextView enemyLieutenantsCount;
    private TextView enemyMinerCount;
    private TextView enemySpyCount;
    private TextView enemyMarshalsCount;
    private TextView enemySargeantsCount;
    private TextView enemyScoutCount;
    private TextView enemyCaptainsCount;
    private TextView enemyMajorCount;

    private StrategoGameState copyState;

    //int for keeping track of the number of dead troops
    private int deadG;
    private int deadC;
    private int deadL;
    private int deadMin;
    private int deadSpy;
    private int deadMarsh;
    private int deadMaj;
    private int deadSar;
    private int deadSco;
    private int deadCap;


    /**
     * constructor
     *
     * @param name the name of the player
     */
    public StrategoHumanPlayer(String name) {
        super(name);
        int check = this.playerNum;
    }


    /**
     * getTopView
     *
     * @return    the topmost view
     */
    @Override
    public View getTopView() {
        return null;
    }


    /**
     * receiveInfo
     *
     * @param info
     */
    @Override
    public void receiveInfo(GameInfo info) {
        /**
         * External Citation
         * 4/13/2022
         *
         * lifted from tictactoe
         */
        if (myBoardView == null) return;

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            // if the move was out of turn or otherwise illegal, flash the screen
            Log.i("HUMAN_MADE_AN_ERROR", "akljglakjf;gdl;fk;s");
            //this.flash(Color.RED, 50);
        }
        else if (!(info instanceof StrategoGameState))
            // if we do not have a SGState, ignore
            return;
        else {
             StrategoGameState state = ((StrategoGameState) info);
            copyState = (StrategoGameState)info;
            myBoardView.setGameState((StrategoGameState)info);
            Log.i("Board", state.boardToString());
            myBoardView.invalidate();
        }

    }//receiveInfo


    /**
     * setAsGui
     *
     * sets up communication between the GUI in the xml and this player
     *
     * @param activity  this game's MainActivity
     */
    @Override
    public void setAsGui(GameMainActivity activity) {

        //set the GUI to be the game's layout xml
        activity.setContentView(R.layout.stratego_board);

        //set up each button so it aligns with GUI
        this.up = activity.findViewById(R.id.upButton);
        this.down = activity.findViewById(R.id.downButton);
        this.left = activity.findViewById(R.id.leftButton);
        this.right = activity.findViewById(R.id.rightButton);
        this.exit = activity.findViewById(R.id.ExitButton);
        this.surrender = activity.findViewById(R.id.SurrenderButton);
        this.help = activity.findViewById(R.id.helpButton);
        this.end = activity.findViewById(R.id.endButton);

        //set up boardView variable to reference boardView on Gui
        this.myBoardView = activity.findViewById(R.id.strat_boardView);

        //sets up TextView to be altered later on
        this.selectedRank = activity.findViewById(R.id.DisplayRank);

        this.yourTroops = activity.findViewById(R.id.Numberyourtroops);
        this.enemyTroops = activity.findViewById(R.id.NumberEnemyTroops);


        this.yourGeneralCount = activity.findViewById(R.id.yourGeneral);
        this.yourColonelCount = activity.findViewById(R.id.yourColonels);
        this.yourLieutenantsCount = activity.findViewById(R.id.yourLieutenants);
        this.yourMinerCount = activity.findViewById(R.id.yourMiners);
        this.yourSpyCount = activity.findViewById(R.id.yourSpy);
        this.yourMarshalsCount = activity.findViewById(R.id.yourMarshal);
        this.yourSargeantsCount = activity.findViewById(R.id.yourSargeants);
        this.yourScoutCount = activity.findViewById(R.id.yourScouts);
        this.yourCaptainsCount = activity.findViewById(R.id.yourCaptains);
        this.yourMajorCount = activity.findViewById(R.id.yourMajors);

        this.enemyGeneralCount = activity.findViewById(R.id.oppGeneral);
        this.enemyColonelCount = activity.findViewById(R.id.oppColonels);
        this.enemyLieutenantsCount = activity.findViewById(R.id.oppLieutenants);
        this.enemyMinerCount = activity.findViewById(R.id.oppMiners);
        this.enemySpyCount = activity.findViewById(R.id.oppSpy);
        this.enemyMarshalsCount = activity.findViewById(R.id.oppMarshal);
        this.enemySargeantsCount = activity.findViewById(R.id.oppSargeants);
        this.enemyScoutCount = activity.findViewById(R.id.oppScouts);
        this.enemyCaptainsCount = activity.findViewById(R.id.oppCaptains);
        this.enemyMajorCount = activity.findViewById(R.id.oppMajors);

        //set onTouch and onClick listeners
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        exit.setOnClickListener(this);
        surrender.setOnClickListener(this);
        help.setOnClickListener(this);
        end.setOnClickListener(this);

        myBoardView.setOnTouchListener(this);

    }//setAsGui


    /**
     * onClick
     *
     * handles click events
     *
     * @param view   the view object that was clicked
     */
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.upButton) {
            Log.i("BUTTON_CLICK", " UP_fakjhsgkajlfkga_UP_ldkfnba;ndfb;");
            UpAction ua = new UpAction(this);
            game.sendAction(ua);
            myBoardView.invalidate();
        }
        else if (view.getId() == R.id.downButton) {
            Log.i("DOWN_BUTTON_CLICK", "ashkgjaero;igja;lkdfbnslkdfb;ldkf");
            DownAction da = new DownAction(this);
            game.sendAction(da);
        }
        else if (view.getId() == R.id.leftButton) {
            Log.i("LEFT_BUTTON_CLICK", "asjglaurjfaskfjasjgla");
            LeftAction la = new LeftAction(this);
            game.sendAction(la);
        }
        else if (view.getId() == R.id.rightButton) {
            Log.i("RIGHT_BUTTON_CLICK", "glkjdfkjglaksjklgdfklsldfk");
            RightAction ra = new RightAction(this);
            game.sendAction(ra);
        }
        else if(view.getId() == R.id.SurrenderButton){
            Log.i("HUMAN_SURRENDER", "BOT_CROWNED_VICTOR_OVER_HUMANITY");
            this.sendInfo(new GameOverInfo("BOT CROWNED VICTOR OVER HUMANITY"));
        }
        else if (view.getId() == R.id.endButton){
            Log.i("END_BUTTON_CLICKED", "lkjealkgjitjdlkgsh");
            EndScoutAction esa = new EndScoutAction(this);
            game.sendAction(esa);

        }
        else if (view.getId() == R.id.ExitButton){
            System.exit(1);
        }

        resetDeadNum();
        int pTroopsAlive = 0;
        for(int i = 0; i < copyState.getP2Troops().size(); i++){
            if(copyState.getP2Troops().get(i).getStatus() == false){
                pTroopsAlive++;
            }
            else{
                graveyardMethod(copyState.getP2Troops().get(i));
            }
        }
        yourTroops.setText("" + pTroopsAlive);
        yourCaptainsCount.setText("" + deadCap + "/4");
        yourSargeantsCount.setText("" + deadSar + "/4");
        yourColonelCount.setText("" + deadC + "/2");
        yourMajorCount.setText("" + deadMaj + "/3");
        yourMinerCount.setText("" + deadMin  + "/5");
        yourScoutCount.setText("" + deadSco  + "/8");
        yourSpyCount.setText("" + deadSpy  + "/1");
        yourLieutenantsCount.setText("" + deadL  + "/4");
        yourGeneralCount.setText("" + deadG  + "/1");
        yourMarshalsCount.setText("" + deadMarsh  + "/1");
        yourCaptainsCount.setText("" + deadCap  + "/4");


        resetDeadNum();
        int eTroopsAlive = 0;
        for(int j = 0; j < copyState.getP1Troops().size(); j++){
            if(copyState.getP1Troops().get(j).getStatus() == false){
                eTroopsAlive++;
            }
            else{
                graveyardMethod(copyState.getP1Troops().get(j));
            }
        }
        enemyTroops.setText("" + eTroopsAlive);
        enemyCaptainsCount.setText("" + deadCap + "/4");
        enemySargeantsCount.setText("" + deadSar + "/4");
        enemyColonelCount.setText("" + deadC + "/2");
        enemyMajorCount.setText("" + deadMaj + "/3");
        enemyMinerCount.setText("" + deadMin  + "/5");
        enemyScoutCount.setText("" + deadSco  + "/8");
        enemySpyCount.setText("" + deadSpy  + "/1");
        enemyLieutenantsCount.setText("" + deadL  + "/4");
        enemyGeneralCount.setText("" + deadG  + "/1");
        enemyMarshalsCount.setText("" + deadMarsh  + "/1");
        enemyCaptainsCount.setText("" + deadCap  + "/4");

    }//onClick


    /**
     * graveyardMethod
     *
     * helper method to incriment the count of currently dead units
     *
     * @param unit  the Unit to add to the graveyard
     */
    public void graveyardMethod(Unit unit){
        if(unit.getRank() == Unit.GENERAL){
            deadG++;
        }
        else if(unit.getRank() == Unit.COLONEL){
            deadC++;
        }
        else if(unit.getRank() == Unit.LIEUTENANT){
            deadL++;
        }
        else if(unit.getRank() == Unit.MINER){
            deadMin++;
        }
        else if(unit.getRank() == Unit.SPY){
            deadSpy++;
        }
        else if(unit.getRank() == Unit.MARSHAL){
            deadMarsh++;
        }
        else if(unit.getRank() == Unit.MAJOR){
            deadMaj++;
        }
        else if(unit.getRank() == Unit.SERGEANT){
            deadSar++;
        }
        else if(unit.getRank() == Unit.SCOUT){
            deadSco++;
        }
        else if(unit.getRank() == Unit.CAPTAIN){
            deadCap++;
        }
        else{
            //do nothing
        }
    }//graveyardMethod

    /**
     * resetDeadNum
     *
     * resets the count of Units that have died
     */
    public void resetDeadNum(){
        deadCap = 0;
        deadC = 0;
        deadG = 0;
        deadSco = 0;
        deadSar = 0;
        deadMaj = 0;
        deadMarsh = 0;
        deadL = 0;
        deadMin = 0;
        deadSpy = 0;
    }//resetDeadNum


    /**
     * onTouch
     *
     * handles touch events
     *
     * @param view          the view that was touched
     * @param motionEvent   the MotionEvent object created by the event
     * @return              true, since the event is being handled
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //find where the touch event occurred
        int x = (int)motionEvent.getX();
        int y = (int)motionEvent.getY();


        copyState = myBoardView.getGameState();
        //match that x,y to a Unit
        Unit test = this.findUnit(x, y);
        if(test != null){

            if(test.getRank() != Unit.BOMB && test.getRank() != Unit.FLAG){

                //check to see if unit is a scout (special game rules)
                if(test.getRank() == Unit.SCOUT){
                    ScoutBonusAction sba = new ScoutBonusAction(this);
                    game.sendAction(sba);

                }
                //initial run testing message
                Log.i("ON_TOUCH", "hey this is a rlly long message to let u know it worked " + x + " " + y);
                Log.i("ON TOUCH", "LOCATION ON BOARD: [" + test.getyLoc()+ ", " + test.getxLoc()+"]");
                Log.i("ON TOUCH", "TROOP RANK:" + test.getRank() + ": " + test.nameRank());

                SelectPieceAction spa = new SelectPieceAction(this, test);
                game.sendAction(spa);
            }
            CharSequence rank = test.nameRank();
            this.selectedRank.setText(rank);

            this.sendInfo(copyState);
        }
        else {
            //do nothing, since there's no Unit there
        }
        return true;
    }//onTouch


    /**
     *findRect
     *
     * method to assist in determining whether or not a touch happened in a given element
     *
     * @return  the Unit containing those specific xy coords
     */
    public Unit findUnit(int x, int y){
        Unit temp = null;
        for(int i = 0; i < copyState.getP2Troops().size(); i++){
            if(copyState.getP2Troops().get(i).containsPoint(x, y)){
                temp = copyState.getP2Troops().get(i);
                break;
            }
        }
        return temp;
    }// findRect

}//StrategoHumanPlayer

