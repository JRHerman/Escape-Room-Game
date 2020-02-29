package com.mygdx.game.RoomObjects;


/**
 * A keyLock is a lock and its lock key can be set and the keys can be compared
 * to see if the key is compatible with the lock
 */
public class KeyLock extends Lock {

    private LockKey key;    // the key that will be used to open this lock

    public KeyLock() {
        // calls super constructor
        super();
    }

    public void setKey(LockKey lockKey) {
        key = lockKey;
    }

    public boolean compareKeys(LockKey comparingKey) {
        // function checks to see if the given key is the one associated with this lock
        return key.equals(comparingKey);
    }

}
