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


    private Paint highlighter = new Paint();



    public static final int UNIT_WIDTH = 92;
    public static final int UNIT_HEIGHT = 98;


    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);


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


        //TODO: this is only for phase 0?
        //maybe a if(phase == 0)
        //top side of board's units
       for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] != null){
                    board[i][j].drawMe(canvas);
                }
                else{
                    //do nothing
                }
            }
        }

       /*//bottom side of board's units
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < board.length; j++){
                Unit temp = board[i][j];
                board[i][j].drawMe(canvas);
            }
        }*/


    }//onDraw

}//BoardView
