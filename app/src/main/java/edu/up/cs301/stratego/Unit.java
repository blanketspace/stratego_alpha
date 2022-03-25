package edu.up.cs301.stratego;

import android.graphics.Canvas;

/**
 * class Unit
 *
 * @author Anne Marie Blank,
 * @author Harry Vu,
 * @author Vincent Truong
 * @version 3/11/2022
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


    /*** Nothing else needs to be added in this Unit class
     * IMPORTANT ELABORATION: Every unit has their ownerID, meaning that
     * the owner's id that comes with the unit,*/


    private int ownerID;  //the id of the player who owns the piece
    private int rank;     //what kind of unit is this?
    private boolean isSelected;
    private boolean isDead;
    private int xLoc;
    private int yLoc;

    public Unit(int id, int initRank){
        ownerID = id;
        rank = initRank;
        isSelected = false;
        isDead = false;
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

    public int getOwnerID(){
        return this.ownerID;
    }

    public int getRank() {
        return rank;
    }

    public void setSelected(boolean selected) {
        if(!isDead) {
            isSelected = selected;
        }
    }

    public boolean getSelected(){
        return this.isSelected;
    }

    public void setStatus(boolean dead){
        this.isDead = dead;
    }

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


}//class Unit


