package com.mygdx.game.mechanics;
import java.util.ArrayList;
/**
 *
 * the purpose of this class is to help the player throughout the course of the game if they
 * ask for a hint. This will be shown if the player hits a toggle button to turn
 * hint on in the GUI
 * */
public class ClueSet
{
    //arrayList of hints
    public ArrayList<String> hints;

    //Clue constructor initialize arrayList
    public ClueSet()
    {
         hints = new ArrayList<String>(0);
    }

    //Add clue to the back of the ArrayList. Clues will be progression strings
    //from the storyboard
    public void addClue(String clue)
    {
        hints.add(clue);
    }

    /**
     * gets clue from the room the player is currently in takes room -1 since room is 1 based and the array is 0 based
     *  @return String - returns the String of the clue
     */
    public String getCurrentHint(int stepNumber)
    {
        return hints.get(stepNumber - 1);
    }
}
