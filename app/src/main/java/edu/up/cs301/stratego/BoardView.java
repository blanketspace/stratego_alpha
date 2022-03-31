package edu.up.cs301.stratego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.ImageView;

import edu.up.cs301.animation.AnimationSurface;
import edu.up.cs301.game.R;

/**
 * BoardView
 *
 * @author Anne Marie Blank
 * @author Harry Vu
 * @author Vincent Truong
 * @author Kathryn Weidman
 *
 * @version 3/29/2022
 */
public class BoardView extends SurfaceView {

    private StrategoGameState gameState;
    private Unit[][] board;

    private Paint redUnits = new Paint();
    private Paint blueUnits = new Paint();
    private Paint highlighter = new Paint();



    public static final int UNIT_WIDTH = 92;
    public static final int UNIT_HEIGHT = 98;


    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        redUnits.setColor(0xfff24141);
        blueUnits.setColor(0xff4287f5);
        highlighter.setColor(0xfff2e641);

        gameState = new StrategoGameState();
        board = gameState.getGameboard();

        setWillNotDraw(false);
    }


    /**
     * onDraw
     *
     * method to get the Unit onto the GUI board
     *
     * @param canvas the drawing space for the Unit
     */
    @Override
    public void onDraw(Canvas canvas) {
        //TODO: need a rect somewhere with constant/final dimensions
        //TODO: need a color (designated by owner id?)
        //TODO: maybe xy locations to be drawn in? no clue how we'll get those
        //TODO: death by ifs for each rank's visual aspect?

        //dimensions are 60dp wide per piece

        canvas.drawRect(98,92,190,190, blueUnits);

        Paint paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setStyle(Paint.Style.FILL);

        //use gameState variable to access which units are there?
        //for loop
        //for(int i = 0; i < gameState.p1Troops)
        //gameState.getUnit(0, 0).drawMe(canvas, redUnits);
        gameState.getP1Troops().get(0).drawMe(canvas, redUnits);


        //will only work after phase 0 is over
       for(int i = 0; i < board.length; i++){
            for(int j = 0; j < 5; j++){ //final will be i<baord.length
                board[i][j].drawMe(canvas, blueUnits);
            }
        }

    }//onDraw

}//BoardView
