package com.mygdx.game.RoomObjects;

/**
 * A lock has a boolean to check if it is locked and it can be locked or unlocked
 */
public class Lock extends GameObject {

    private boolean locked;

    public Lock() {
        locked = true;
    }

    public boolean isLocked() {return locked;}

    public void lock() {locked = true;}
    public void unlock() {locked = false;}

}
