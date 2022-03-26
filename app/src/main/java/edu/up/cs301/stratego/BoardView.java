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

public class BoardView extends SurfaceView {

    private StrategoGameState gameState;
    private Bitmap board;

    public static final int UNIT_WIDTH = 92;
    public static final int UNIT_HEIGHT = 98;


    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);
    }


    /**
     * drawMe
     *
     * method to get the Unit onto the GUI board
     *
     * @param canvas the drawing space for the Unit
     */
    public void onDraw(Canvas canvas){
        //TODO: need a rect somewhere with constant/final dimensions
        //TODO: need a color (designated by owner id?)
        //TODO: maybe xy locations to be drawn in? no clue how we'll get those
        //TODO: death by ifs for each rank's visual aspect?

        //dimensions are 60dp wide per piece

        Paint paint = new Paint();
        paint.setColor(0x00ff44);
        paint.setStyle(Paint.Style.STROKE);

        Paint paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setStyle(Paint.Style.FILL);

        canvas.drawRect(98,92,190,190, paint2);
        //Rectangles not in dp I think (they're pixels --AM)



    }


}//BoardView
