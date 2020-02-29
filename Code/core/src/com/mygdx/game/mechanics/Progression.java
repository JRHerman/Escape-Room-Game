package com.mygdx.game.mechanics;// CS 440 - Group 4 Coding Project
// Luqmaan Baiyat, Patrick Gundry, Joshua Herman, Hamza Shahid

/**
 * The players progression through the room. Progression is defined by
 * the step through the story board where the step is a step through the level.
 * The player must go through all steps in order to pass the room.
 */
public class Progression {

    private int numberSteps;
    private int currentStepNumber;

    protected int currRoom;

    public Progression() {
        // current step will start at 1 for any room
        currentStepNumber = 1;
    }

    public int getNumberSteps;

    /**
     * Gets the current step number of the user
     * @return currentStepNumber:int - Returns the current step of the user in the storyboard
     */
    public int getCurrentStepNumber() {
        return currentStepNumber;
    }

    /**
     * Gets the room that the user is currently in
     * @return currRoom:int - Returns the current room that the user is currently in
     */
    public int getRoom()
    {
        return currRoom;
    }

    /**
     * Will be updated when the user gets passed a step in the room. This
     * will allow us to keep track of the step in the story board that the
     * user is currently in. It will also help us give the right hints
     * to the user.
     */
    public void updateStepNumber() {
        currentStepNumber++;
    }

    /**
     * Sets the step number to the entered parameter stepNumber
     * @param stepNumber:int is the current step number in the room.
     */
    public void setStepNumber(int stepNumber) {currentStepNumber = stepNumber;}

}
