package edu.up.cs301.stratego;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.stratego.actions.DownAction;
import edu.up.cs301.stratego.actions.LeftAction;
import edu.up.cs301.stratego.actions.RightAction;
import edu.up.cs301.stratego.actions.SelectPieceAction;
import edu.up.cs301.stratego.actions.UpAction;


/**
 * StrategoHumanPlayer
 *
 * @author Anne Marie Blank
 * @author Harry Vu
 * @author Vincent Truong
 * @author Kathryn Weidman
 * @version 3/29/2022
 */
public class StrategoHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener {

    private Button up;
    private Button down;
    private Button left;
    private Button right;
    private BoardView boardView;

    private GameMainActivity gma;
    private StrategoGameState copyState;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public StrategoHumanPlayer(String name) {
        super(name);
    }


    /**
     * getTopView
     *
     * @return
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
        if (info instanceof StrategoGameState) {
            Log.i("HUM_PLAYER", "rECIEVEiNFO");
            copyState = (StrategoGameState) info;
        }
        else {
            //something has gone wrong, or it's not this player's turn
            this.flash(Color.RED, 2);
        }

    }//receiveInfo

    @Override
    public void setAsGui(GameMainActivity activity) {

        // set the GUI to be the game's layout xml
        activity.setContentView(R.layout.stratego_board);

        //set up each button so it aligns with GUI
        this.up = activity.findViewById(R.id.upButton);
        this.down = activity.findViewById(R.id.downButton);
        this.left = activity.findViewById(R.id.leftButton);
        this.right = activity.findViewById(R.id.rightButton);

        //set up boardView variable to reference boardView on Gui
        this.boardView = activity.findViewById(R.id.strat_boardView);

        //set onTouch and onClick listeners
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        boardView.setOnTouchListener(this);

    }//setAsGui

    /**
     * onClick
     *
     * @param view
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
        else if (view.getId() == R.id.rightButton); {
            Log.i("RIGHT_BUTTON_CLICK", "glkjdfkjglaksjklgdfklsldfk");
            RightAction ra = new RightAction(this);
            game.sendAction(ra);
        }


    }//onClick


    /**
     * onTouch
     *
     * @param view
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //find where the touch event occurred
        int x = (int)motionEvent.getX();
        int y = (int)motionEvent.getY();


        copyState = boardView.getGameState();
        //match that x,y to a Unit
        Unit test = this.findUnit(x, y);
        if(test != null){
            copyState.selectPiece(copyState.getWhoseTurn(), test);
            //copyState.clearSelection(1);  KEEP COMMENTED OUT, RESULTS IN CRASH
            test.setSelected(true);
            boardView.invalidate();
        }

        SelectPieceAction spa = new SelectPieceAction(this);
        game.sendAction(spa);

        //initial run testing message
        Log.i("ON_TOUCH", "hey this is a rlly long message to let u know it worked " + x + " " + y);
        return false;
    }//onTouch

    /**
     *findRect
     *
     * method to assist in determining whether or not a touch happened in a given element
     */
    public Unit findUnit(int x, int y){
        Unit temp = null;
        for(int i = 0; i < copyState.getP2Troops().size() - 1; i++){
            if(copyState.getP2Troops().get(i).containsPoint(x, y)){
                temp = copyState.getP2Troops().get(i);
                break;
            }
        }
        return temp;
    }// findRect


}//StrategoHumanPlayer

