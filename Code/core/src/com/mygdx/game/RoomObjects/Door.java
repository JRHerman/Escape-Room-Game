package com.mygdx.game.RoomObjects;// CS 440 - Group 4 Coding Project
// Luqmaan Baiyat, Patrick Gundry, Joshua Herman, Hamza Shahid

/**
 * A door consists of a texture and a lock and it can be opened and closed.
 * It also can be locked and unlocked
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.mechanics.Player;

public class Door extends GameObject {

    private Texture texture;

    private KeyLock doorLock;

    private int xStart, xSize, yStart, ySize;
    public enum DoorType {
        HORIZONTAL, VERTICAL;
    }



    private DoorType doorType;
    private int doorOffset; // how far the wall will move when opened/closed


    public Door(DoorType type, int xStart, int xSize, int yStart, int ySize, int offset) {
        texture = new Texture("door_texture.png");
        doorLock = new KeyLock();

        doorType = type;
        this.xStart = xStart;
        this.xSize = xSize;
        this.yStart = yStart;
        this.ySize = ySize;

        this.doorOffset = offset;
    }

    public boolean isLocked() {
        return doorLock.isLocked();
    }

    public void unlockLock() {

        doorLock.unlock();

        //update door coordinates
        if (doorType == DoorType.HORIZONTAL) {
            xStart += doorOffset;

        }

        else if (doorType == DoorType.VERTICAL) {
            yStart += doorOffset;
        }
    }

    public void lockLock() {
        doorLock.lock();
    }

    public Texture getTexture() {return texture;}

    public KeyLock getLock() {return doorLock;}

    public void draw(Batch gameBatch) {

        gameBatch.draw(texture, xStart, yStart, xSize, ySize);
    }

    private boolean inBounds(int xCoord, int yCoord) {

        int size = 35;
        if (doorType == DoorType.HORIZONTAL) {
            return (xCoord >= xStart - size && xCoord <= (xStart + xSize));
        }

        else if (doorType == DoorType.VERTICAL) {
            return (yCoord >= yStart - size && yCoord <= (yStart + ySize));
        }

        return false;
    }

    // private helper functions for determining the players position
    private boolean aboveWall(int yCoord) {
        return (yCoord >= (yStart + ySize));
    }
    private boolean belowWall(int yCoord) {
        return (yCoord <= yStart);
    }
    private boolean rightOfWall(int xCoord) {
        return (xCoord >= (xStart + xSize));
    }
    private boolean leftOfWall(int xCoord) {
        return (xCoord <= (xStart));
    }


    public boolean topCollision(Player player) {
        if (!inBounds(player.getxPos(), player.getyPos()))
            return false;

        // if this wall is going horizonally
        if (doorType == DoorType.HORIZONTAL)  {
            if (belowWall(player.getyPos()))
                return ((player.getyPos() + player.getMovementConstant()) > (yStart - player.getHeight()));
        }

        return false;
    }

    // used for detecting a collision for a bottom wall
    public boolean bottomCollision(Player player) {
        if (!inBounds(player.getxPos(), player.getyPos()))
            return false;

        // if this wall is going horizonally
        if (doorType == DoorType.HORIZONTAL)  {
            if (aboveWall(player.getyPos()))
                return ((player.getyPos() - player.getMovementConstant()) < (yStart + ySize));
        }

        return false;
    }

    public boolean rightCollision(Player player) {
        if (!inBounds(player.getxPos(), player.getyPos()))
            return false;

        // if this wall is going vertically
        if (doorType == DoorType.VERTICAL)  {
            if (leftOfWall(player.getxPos()))
                return ((player.getxPos() + player.getMovementConstant()) > (xStart - player.getWidth()));

        }

        return false;
    }

    public boolean leftCollision(Player player) {
        if (!inBounds(player.getxPos(), player.getyPos()))
            return false;

        // if this wall is going vertically
        if (doorType == DoorType.VERTICAL)  {
            if (rightOfWall(player.getxPos()))
                return ((player.getxPos() - player.getMovementConstant()) < (xStart + xSize));

        }

        return false;
    }


}
