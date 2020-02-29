package com.mygdx.game.RoomObjects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A floor tile puzzle has start coordinates, width, and height.
 * Tiles can be set active or inactive and the puzzle is made random via RNG
 * Tiles can be reset, we can find the next tile in sequence and we can
 * check if the puzzle is solved
 */
public class FloorTilePuzzle {

    private class FloorTile {

        private Texture texture;
        private int xStart;
        private int yStart;
        private int width;
        private int height;

        private int tileID;
        private boolean active;

        public FloorTile(int x, int y, int w, int h, int tileId) {
            texture = new Texture("floortile_inactive.png");
            xStart = x;
            yStart = y;
            width = w;
            height = h;
            this.tileID = tileId;
        }

        public void setTileActive() {
            texture = new Texture("floortile_active.png");
            active = true;
        }

        public void setTileInactive() {
            texture = new Texture("floortile_inactive.png");
            active = false;
        }

        public boolean tileSteppedOn(int x, int y) {
            return (x >= xStart && x <= xStart + width && y >= yStart && y <= yStart + height );
        }

        public boolean nextInSequence(FloorTile comparingTile) {
            // checks to see if it is the next tile in the sequence
            return (this.equals(comparingTile));
        }


    }

    private ArrayList<FloorTile> tiles;

    private boolean puzzleSolved;

    // the key that will be given to the player after they solve this puzzle
    private LockKey key;

    // corresponds to how many tiles have been activated
    private int currentStep;

    public FloorTilePuzzle(LockKey hiddenKey) {
        key = hiddenKey;
        puzzleSolved = false;
        tiles = new ArrayList<FloorTile>(0);
        currentStep = 0;

    }


    public void addTile(int x, int y, int width, int height, int tileID) {
        FloorTile tile = new FloorTile(x,y,width,height, tileID);
        tiles.add(tile);
    }

    // sets up the sequence for the floor tile puzzles
    public void initializeSequence() {
        // will shuffle the tiles around
        //Collections.shuffle(tiles);
    }

    public boolean checkIfPuzzleSolved() {
        boolean solved = false;

        for (FloorTile tile: tiles) {
            solved = tile.active;
        }

        puzzleSolved = solved;
        return solved;
    }

    public void tileCheck(int playerX, int playerY) {

        for (FloorTile tile: tiles) {
            if (tile.tileSteppedOn(playerX, playerY) && !tile.active) {
                // if the stepped on tile was next in the sequence
                if (tile.nextInSequence(tiles.get(currentStep))) {
                    tile.setTileActive();
                    currentStep++;
                }

                else {
                    resetTiles();
                }

            }
        }
    }

    private void resetTiles() {
        for (FloorTile tile: tiles) {
            tile.setTileInactive();
        }
        currentStep = 0;
    }

    // will draw the tiles according to their coordinates
    public void drawTiles(Batch gameBatch) {
        for (FloorTile floorTile: tiles) {
            gameBatch.draw(floorTile.texture, floorTile.xStart, floorTile.yStart, floorTile.width, floorTile.height);
        }
    }

    public LockKey getKey() {
        return key;
    }
}
