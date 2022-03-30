package edu.up.cs301.stratego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.ImageView;

import edu.up.cs301.animation.AnimationSurface;
import edu.up.cs301.game.R;

public class BoardView extends AnimationSurface {

    protected StrategoGameState state;

    public static final float unitXSide = 95.0f;
    public static final float unitYSide = 92.0f;
    public static final float topLeftX = 5.0f;
    public static final float topLeftY = 5.0f;

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

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
    public void onDraw(Canvas canvas){
        //TODO: need a rect somewhere with constant/final dimensions
        //TODO: need a color (designated by owner id?)
        //TODO: maybe xy locations to be drawn in? no clue how we'll get those
        //TODO: death by ifs for each rank's visual aspect?

        //dimensions are 60dp wide per piece

        //Paint paint = new Paint();
        //paint.setColor(Color.BLUE);
        //paint.setStyle(Paint.Style.FILL);
        //
        //Paint paint2 = new Paint();
        //paint2.setColor(Color.RED);
        //paint2.setStyle(Paint.Style.FILL);

        //920x980 units...?
        //canvas.drawRect(topLeftX+1,topLeftY,unitXSide,unitYSide, paint);
        //canvas.drawRect(unitXSide+topLeftX-1,topLeftY,2*(unitXSide)-1,unitYSide, paint2);
        //canvas.drawRect(2*unitXSide+topLeftX-2,topLeftY,3*(unitXSide)-3,unitYSide, paint);
        //canvas.drawRect(3*unitXSide+topLeftX-5,topLeftY,4*(unitXSide)-6,unitYSide, paint2);
        //Rectangles not in dp I think (in px?)




    }//onDraw

    protected void setState(StrategoGameState state) {
        this.state = state;
    }

}//BoardView
