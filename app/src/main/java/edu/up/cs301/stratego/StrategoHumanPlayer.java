package edu.up.cs301.stratego;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.IllegalMoveInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;
import edu.up.cs301.stratego.actions.DownAction;
import edu.up.cs301.stratego.actions.ExitAction;
import edu.up.cs301.stratego.actions.HelpAction;
import edu.up.cs301.stratego.actions.LeftAction;
import edu.up.cs301.stratego.actions.RightAction;
import edu.up.cs301.stratego.actions.SelectPieceAction;
import edu.up.cs301.stratego.actions.SurrenderAction;
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
    private Button surrender;
    private Button exit;
    private Button help;
    private BoardView myBoardView;
    private TextView selectedRank;

    private StrategoGameState copyState;


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
            this.flash(Color.RED, 50);
        }
        else if (!(info instanceof StrategoGameState))
            // if we do not have a SGState, ignore
            return;
        else {
            myBoardView.setGameState((StrategoGameState)info);
            myBoardView.invalidate();
        }

    }//receiveInfo


    /**
     * setAsGui
     *
     * TODO: FINISH ME
     * @param activity
     */
    @Override
    public void setAsGui(GameMainActivity activity) {

        // set the GUI to be the game's layout xml
        activity.setContentView(R.layout.stratego_board);

        //set up each button so it aligns with GUI
        this.up = activity.findViewById(R.id.upButton);
        this.down = activity.findViewById(R.id.downButton);
        this.left = activity.findViewById(R.id.leftButton);
        this.right = activity.findViewById(R.id.rightButton);
        this.surrender = activity.findViewById(R.id.SurrenderButton);
        this.exit = activity.findViewById(R.id.ExitButton);
        this.help = activity.findViewById(R.id.helpButton);

        //set up boardView variable to reference boardView on Gui
        this.myBoardView = activity.findViewById(R.id.strat_boardView);

        /*//sets up TextView to be altered later on
        this.selectedRank = activity.findViewById(R.id.DisplayRank);*/  //TODO: doesn't work. don't know why

        //set onTouch and onClick listeners
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        surrender.setOnClickListener(this);
        exit.setOnClickListener(this);
        help.setOnClickListener(this);
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
        else if (view.getId() == R.id.SurrenderButton) {
            Log.i("SURRENDER_BUTTON_CLICK", "weeheeheehee");
            SurrenderAction sa = new SurrenderAction(this);
            game.sendAction(sa);
        }
        else if (view.getId() == R.id.ExitButton) {
            Log.i("EXIT_BUTTON_CLICK", "ohohohohohohohohohoho");
            ExitAction ea = new ExitAction(this);
            game.sendAction(ea);
        }
        else if (view.getId() == R.id.ExitButton) {
            Log.i("EXIT_BUTTON_CLICK", "ohohohohohohohohohoho");
            HelpAction ha = new HelpAction(this);
            game.sendAction(ha);
        }

    }//onClick


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
            copyState.selectPiece(copyState.getWhoseTurn(), test);
            copyState.clearSelection(1);  //clears selection from all Units
            test.setSelected(true);  //select specific Unit
            myBoardView.invalidate();
            //selectedRank.setText(test.getRank());

            //initial run testing message
            Log.i("ON_TOUCH", "hey this is a rlly long message to let u know it worked " + x + " " + y);

            SelectPieceAction spa = new SelectPieceAction(this, test);
            game.sendAction(spa);
            this.sendInfo(copyState);  //this probably breaks things
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


    public void setMyBoardView(BoardView myBoardView) {
        this.myBoardView = myBoardView;
    }
}//StrategoHumanPlayer

