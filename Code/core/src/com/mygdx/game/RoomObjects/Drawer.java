package com.mygdx.game.RoomObjects;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

/**
 * A drawer has texture, contains a key within it, and checks if the key is taken
 * The lock is made with a RNG.
 */
public class Drawer {

    private Texture texture;
    private LockKey key;
    private boolean keyTaken;

    private CombinationLock comboLock;
    private boolean locked;

    public Drawer(LockKey hiddenKey) {

        texture = new Texture("drawer_texture.png");
        key = hiddenKey;
        keyTaken = false;

        Random random = new Random();
        int randomNumber = random.nextInt(9);
        // the solution will be a random number between 0-9
        comboLock = new CombinationLock(Integer.toString(randomNumber));

    }

    public CombinationLock getComboLock() {return comboLock;}
    public boolean isLocked() {return comboLock.isLocked(); }

    public LockKey getKey() {
        // if the key is taken, a copy is made and returned, the key object is deleted
        LockKey keyCopy = key;
        key = null;
        keyTaken = true;
        return keyCopy;
    }

    public boolean isKeyTaken() { return keyTaken;}

    public Texture getTexture() { return texture; }
}
