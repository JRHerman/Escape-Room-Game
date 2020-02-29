package com.mygdx.game.RoomObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.mechanics.Player;

public class Wall {


    // default constructor
    public Wall() {}

    public enum WallType  {
        HORIZONTAL, VERTICAL;
    }


    private WallType wallType;
    private Texture texture = new Texture("wall.png");

    // x0 and y0 are starting x and y coordinates, respectively
    // x1 and y1 are ending x and y coordinates, respectively
    private int xStart, xSize, yStart, ySize;


    public Wall(WallType wType, int xStart, int xSize, int yStart, int ySize) {
        wallType = wType;

        this.xStart = xStart;
        this.xSize = xSize;
        this.yStart = yStart;
        this.ySize = ySize;
    }

    private boolean inBounds(int xCoord, int yCoord) {

        int size = 35;
        if (wallType == WallType.HORIZONTAL) {
            return (xCoord >= xStart - size && xCoord <= (xStart + xSize));
        }

        else if (wallType == WallType.VERTICAL) {
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
        if (wallType == WallType.HORIZONTAL)  {
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
        if (wallType == WallType.HORIZONTAL)  {
            if (aboveWall(player.getyPos()))
                return ((player.getyPos() - player.getMovementConstant()) < (yStart + ySize));
        }

        return false;
    }

    public boolean rightCollision(Player player) {
        if (!inBounds(player.getxPos(), player.getyPos()))
            return false;

        // if this wall is going vertically
        if (wallType == WallType.VERTICAL)  {
            if (leftOfWall(player.getxPos()))
                return ((player.getxPos() + player.getMovementConstant()) > (xStart - player.getWidth()));

        }

        return false;
    }

    public boolean leftCollision(Player player) {
        if (!inBounds(player.getxPos(), player.getyPos()))
            return false;

        // if this wall is going vertically
        if (wallType == WallType.VERTICAL)  {
            if (rightOfWall(player.getxPos()))
                return ((player.getxPos() - player.getMovementConstant()) < (xStart + xSize));

        }

        return false;
    }


    public void draw(Batch gameBatch) {
        gameBatch.draw(texture, xStart, yStart, xSize, ySize);
    }



}
