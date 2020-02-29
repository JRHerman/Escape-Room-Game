package com.mygdx.game.mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.RoomObjects.LockKey;

import java.util.ArrayList;

/**
 * A player has an arraylist of keys and they can add keys, remove them, count them,
 * and check which keys they have
 */
public class Player {

    public enum MoveDirection {
        UP, DOWN, RIGHT, LEFT;
    }

    private Texture texture = new Texture("player_symbol.png");

    private int xPos, yPos;

    private final int MOVEMENT_CONSTANT = 5;

    private ArrayList<LockKey> playerKeys;

    public Player(int startX, int startY) {

        xPos = startX;
        yPos = startY;
        playerKeys = new ArrayList<LockKey>(0);
    }


    // function that handles input from the user
    // TODO - add collision detection
    public void move(Level level, boolean movementEnabled) {

        if (!movementEnabled)
            return;

        // will be used for testing
        boolean ctrlPressed = Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT);

        if (Gdx.input.isKeyPressed(Input.Keys.W))  {
            if (!level.detectCollision(this, MoveDirection.UP) || ctrlPressed)
                 yPos += MOVEMENT_CONSTANT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (!level.detectCollision(this, MoveDirection.DOWN) || ctrlPressed)
                yPos -= MOVEMENT_CONSTANT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (!level.detectCollision(this, MoveDirection.LEFT) || ctrlPressed)
                xPos -= MOVEMENT_CONSTANT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (!level.detectCollision(this, MoveDirection.RIGHT) || ctrlPressed)
                xPos += MOVEMENT_CONSTANT;
        }
    }

    public void draw(Batch gameBatch) {
        gameBatch.draw(texture, xPos, yPos);
    }

    // getter functions for coordinates
    public int getxPos() { return xPos;}
    public int getyPos() { return yPos;}

    public void setxPos(int x) { xPos = x;}
    public void setyPos(int y) {yPos = y;}

    public int getMovementConstant() { return MOVEMENT_CONSTANT;}

    public int getWidth() { return texture.getWidth();}
    public int getHeight () { return texture.getHeight();}

    // functions relating to keys
    public void addKey(LockKey key) {
        playerKeys.add(key);
    }

    public void removeKey(LockKey key) {
        playerKeys.remove(key);
    }

    public int countKeys() {return playerKeys.size();}

    public boolean hasKey(LockKey key) {
        return playerKeys.contains(key);
    }



}
