package edu.up.cs301.stratego;

import static org.junit.Assert.*;
import android.graphics.*;

import org.junit.Test;

/**
 * StragegoGameStateTest
 *
 * Creates tests for non-getter, non-setter,
 * and non-constructor methods that tests
 * the functionality of the StrategoGameState class.
 */
public class StrategoGameStateTest {

    @Test
    public void findEquivUnit() {
        StrategoGameState gameState = new StrategoGameState();
        Unit troop = gameState.getUnit(0, 1);
        Unit other = gameState.getUnit(0, 1);
        Unit checkFinding = gameState.findEquivUnit(other);
        assertTrue(checkFinding == troop);
        Unit another = gameState.getUnit(0, 2);
        checkFinding = gameState.findEquivUnit(another);
        assertFalse(checkFinding == troop);
    }

    @Test
    public void selectPiece() {
        StrategoGameState gameState = new StrategoGameState();
        Unit troop = gameState.getUnit(0, 1);
        boolean troopStatus = troop.getSelected();
        assertFalse(troopStatus);
        troopStatus = gameState.selectPiece(0, troop);
        assertTrue(troopStatus);
    }

    @Test
    public void clearSelection() {
        StrategoGameState gameState = new StrategoGameState();
        Unit troop = gameState.getUnit(0, 1);
        boolean troopStatus = troop.getSelected();
        assertFalse(troopStatus);
        gameState.selectPiece(0, troop);
        gameState.clearSelection();
        troopStatus = troop.getStatus();
        assertFalse(troopStatus);
    }

    @Test
    public void placePiece() {
        StrategoGameState gameState = new StrategoGameState();
        Unit troop = gameState.getUnit(0, 1);
        boolean status = troop.getStatus();
        assertFalse(status);
        boolean check = gameState.placePiece(0,troop,1,1);
        assertFalse(check);
    }

}