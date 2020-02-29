package com.mygdx.game.RoomObjects;// CS 440 - Group 4 Coding Project
// Luqmaan Baiyat, Patrick Gundry, Joshua Herman, Hamza Shahid

import java.util.ArrayList;

/**
 * A game object has a name and can return its name
 */
public class GameObject {

    // TODO - move lock functionality to the lock class
    protected String name;


    // default constructor
    public GameObject() {}

    public GameObject (String objectName) {
        name = objectName;

    }

    public String getName() {return name;}


}
