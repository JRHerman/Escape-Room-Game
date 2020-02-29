package com.mygdx.game.RoomObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.Random;

/**
 * A lever puzzle has starting positions and checks to see if the puzzle is solved
 * if the lever is on, and check to see if the lever is switched on
 * Levers can be inverted, switched from on to off. They can be initialized
 * with a random sequence to be solved, levers can be added, and we can check
 * if the user solved the puzzle
 */
public class LeverPuzzle {

    private class Lever {

        public boolean switchedOn;

        // if this boolean is true, the lever has to be on to solve the puzzle
        // if the boolean is false, then the lever has to be off to solve the puzzle
        public boolean solvedIfOn;

        public Texture texture;

        public int startingX;
        public int startingY;
        public int width;
        public int height;
        public int leverID;


        public Lever(int x, int y, int w, int h, int ID) {
            switchedOn = false;
            texture = new Texture("lever_off.png");
            startingX = x;
            startingY = y;
            width = w;
            height = h;
            leverID = ID;


        }

        public void invert() {
            switchedOn = !switchedOn;
            if (switchedOn)
                texture = new Texture("lever_on.png");
            else if (!switchedOn)
                texture = new Texture("lever_off.png");
        }

        public void setSolvedIfOn(boolean b) {
            solvedIfOn = b;
        }

        public Texture getTexture() {return texture;}


    }

    private ArrayList<Lever> levers;
    private LockKey key;

    public LeverPuzzle(LockKey lockKey) {
        key = lockKey;
        levers = new ArrayList<Lever>(0);
    }

    public void initialize() {
        // this function sets up the solution sequence of the levers
        Random random = new Random();
        for (Lever lever: levers) {
            if (lever.equals(levers.get(0))) {
                // first lever in the sequence always has to be turned on
                lever.setSolvedIfOn(true);
            }

            else {
                boolean randBool = random.nextBoolean();
                lever.setSolvedIfOn(randBool);
            }

        }
    }

    public void addLever(int x, int y, int w, int h, int ID) {
        Lever lever = new Lever(x, y, w, h, ID);
        levers.add(lever);
    }

    public boolean checkIfSolved() {
        boolean solved = true;

        for (Lever lever: levers) {
            solved = solved && (lever.solvedIfOn == lever.switchedOn);
        }

        return solved;
    }

    public void drawLevers(Batch gameBatch) {
        for (Lever lever: levers) {
            gameBatch.draw(lever.texture, lever.startingX, lever.startingY, lever.width, lever.height);
        }
    }

    public void invertLever(int ID) {
        levers.get(ID).invert();
    }
}
