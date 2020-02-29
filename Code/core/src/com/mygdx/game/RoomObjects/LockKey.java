package com.mygdx.game.RoomObjects;

/**
 * A lockKey is a game object and we can set the lock of the Lock Key
 */
public class LockKey extends GameObject{

    private KeyLock lock;   // the lock this this key will open

    public LockKey() {

    }

    // used for associating the corresponding lock with this key
    public void setLock(KeyLock keyLock) {
        lock = keyLock;
    }

}
