package edu.up.cs301.stratego;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.ImageView;

import edu.up.cs301.animation.AnimationSurface;
import edu.up.cs301.game.R;

public class BoardView extends AnimationSurface {


    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);
    }


    /**
     * drawMe
     *
     * method to get the Unit onto the GUI board
     *
     * @param canvas  the drawing space for the Unit
     */
    public void drawMe(Canvas canvas){
        //TODO: need a rect somewhere with constant/final dimensions
        //TODO: need a color (designated by owner id?)
        //TODO: maybe xy locations to be drawn in? no clue how we'll get those
        //TODO: death by ifs for each rank's visual aspect?

    }

}//BoardView
