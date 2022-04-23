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

import java.util.ArrayList;

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
 * @version 4/22/2022
 */
public class BoardView extends SurfaceView {

    private StrategoGameState gameState;
    private Unit[][] board;


    /**
     * ctor
     */
    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        gameState = new StrategoGameState();
        board = gameState.getGameboard();

        setWillNotDraw(false);
    }//ctor


    /**
     * onDraw
     *
     * method to get the Units onto the GUI board
     *
     * @param canvas the drawing space for the Unit
     */
    @Override
    public void onDraw(Canvas canvas) {

        board = gameState.getGameboard();

        //loop through array, calls draw on Units
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].getOwnerID() != 0) {  //TODO: this might fuck things up during network play
                        board[i][j].setObscured(false);
                    }
                    board[i][j].drawMe(canvas);
                }
                else{
                    //do nothing
                }
            }
        }
    }//onDraw

    public StrategoGameState getGameState() {
        return gameState;
    }

    public void setGameState(StrategoGameState gameState) {
        this.gameState = gameState;
    }

}//class BoardView
