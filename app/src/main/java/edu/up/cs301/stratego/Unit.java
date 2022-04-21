package edu.up.cs301.stratego;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * class Unit
 *
 * @author Anne Marie Blank,
 * @author Harry Vu,
 * @author Vincent Truong,
 * @author Kathryn Weidman
 * @version 4/7/2022
 */
public class Unit {
    /**
     *  External Citation
     *  Date 2/23/2022
     *  Issue: unsure what methods to implement
     *  "Link": Office Hours help from Nux
     *
     */
    public static final int SPY = 1;
    public static final int SCOUT = 2;
    public static final int MINER = 3;
    public static final int SERGEANT = 4;
    public static final int LIEUTENANT = 5;
    public static final int CAPTAIN = 6;
    public static final int MAJOR = 7;
    public static final int COLONEL = 8;
    public static final int GENERAL = 9;
    public static final int MARSHAL = 10;
    public static final int BOMB = 11;
    public static final int FLAG = 12;
    public static final int WATER = 13; //only completely non-movable piece


    //for drawing Units
    public static final int UNIT_WIDTH = 70;
    public static final int UNIT_HEIGHT = 70;
    private Paint redUnits = new Paint();
    private Paint blueUnits = new Paint();
    private Paint textPaint = new Paint();


    /*** Nothing else needs to be added in this Unit class
     * IMPORTANT ELABORATION: Every unit has their ownerID, meaning that
     * the owner's id comes with the unit*/


    private int ownerID;  //the id of the player who owns the piece
    private int rank;     //what kind of unit is this?
    private boolean isSelected;
    private boolean isDead;
    private int xLoc;
    private int yLoc;


    public Unit(int id, int initRank) {
        ownerID = id;
        rank = initRank;
        isSelected = false;
        isDead = false;
        redUnits.setColor(0xfff24141);
        blueUnits.setColor(0xff4287f5);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30.0f);

    }//ctor

    public int getOwnerID(){
        return this.ownerID;
    }

    public int getRank() {
        return rank;
    }

    public void setSelected(boolean selected) {
        if (!isDead) {
            isSelected = selected;
            //this.drawHighlight();
        }
    }

    public boolean getSelected(){
        return this.isSelected;
    }

    public void setDead(boolean dead){
        this.isDead = dead;
    }

    /**
     * getStatus
     *
     * this is confusing, so
     *
     * @return  true if dead, false if alive
     */
    public boolean getStatus(){
        return this.isDead;
    }

    public int getxLoc() {
        return this.xLoc;
    }

    public int getyLoc() {
        return this.yLoc;
    }

    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }

    public String nameRank() {
        String name;
        switch (this.rank)
        {
            case 1:
                return "Spy";
            case 2:
                return "Scout";
            case 3:
                return "Miner";
            case 4:
                return "Sergeant";
            case 5:
                return "Lieutenant";
            case 6:
                return "Captain";
            case 7:
                return "Major";
            case 8:
                return "Colonel";
            case 9:
                return "General";
            case 10:
                return "Marshal";
            case 11:
                return "Bomb";
            case 12:
                return "Flag";
        }
        return "bad";
    }//nameRank

    /**
     * drawMe
     *
     * @param c  the canvas to draw on
     */
    public void drawMe(Canvas c) {
        //the following lines are for drawing the outline of the unit
        Paint outline = new Paint();

        if(this.isSelected){
            outline.setColor(0xFFfcfc03);  //highlight color
            outline.setStyle(Paint.Style.STROKE);
        }
        else{
            outline.setColor(0xFF000000); //regular black
            outline.setStyle(Paint.Style.STROKE);
        }

        if(!this.isDead){
            if (ownerID == 0) {
                c.drawRect(xLoc*UNIT_WIDTH, yLoc*UNIT_HEIGHT, xLoc*UNIT_WIDTH + UNIT_WIDTH, yLoc*UNIT_HEIGHT + UNIT_HEIGHT, redUnits);
                c.drawRect(xLoc*UNIT_WIDTH, yLoc*UNIT_HEIGHT, xLoc*UNIT_WIDTH + UNIT_WIDTH, yLoc*UNIT_HEIGHT + UNIT_HEIGHT, outline);
            }
            else if (ownerID == 1) {
                c.drawRect(xLoc*UNIT_WIDTH, yLoc*UNIT_HEIGHT, xLoc*UNIT_WIDTH + UNIT_WIDTH, yLoc*UNIT_HEIGHT + UNIT_HEIGHT, blueUnits);
                c.drawRect(xLoc*UNIT_WIDTH, yLoc*UNIT_HEIGHT, xLoc*UNIT_WIDTH + UNIT_WIDTH, yLoc*UNIT_HEIGHT + UNIT_HEIGHT, outline);
            }

            /**
             * external citation
             * 4/19/2022
             * in-class help from nux
             */
            if(this.rank == 11) {
                c.drawText("Bomb", xLoc * UNIT_WIDTH + UNIT_WIDTH / 3, yLoc * UNIT_HEIGHT + UNIT_HEIGHT / 3, textPaint);
            }
            else if(this.rank == 12)
            {
                c.drawText("Flag", xLoc * UNIT_WIDTH + UNIT_WIDTH / 3, yLoc * UNIT_HEIGHT + UNIT_HEIGHT / 3, textPaint);
            }
            else
            {
                c.drawText(""+this.rank, xLoc * UNIT_WIDTH + UNIT_WIDTH / 3, yLoc * UNIT_HEIGHT + UNIT_HEIGHT / 3, textPaint);
            }
        }


    }//drawMe


    /**
     * External Citation
     * based off of a method of the same name in the CustomRect class
     * provided as a resource for the Custom Coloring homework
     *
     * @param x
     * @param y
     * @return
     */
    public boolean containsPoint(int x, int y) {
        Rect r = new Rect(xLoc*UNIT_WIDTH, yLoc*UNIT_HEIGHT, xLoc*UNIT_WIDTH + UNIT_WIDTH, yLoc*UNIT_HEIGHT + UNIT_HEIGHT);

        return r.contains(x, y);
    }//containsPoint

}//class Unit


