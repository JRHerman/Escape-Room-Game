package com.mygdx.game.RoomObjects;

import java.util.*;

public class RiddlePuzzle
{
    int I = 0;
    String hint;
    String RIDDLE_KEY;
    String RIDDLE_VALUE;
    String riddle, riddle_answer;
    ArrayList<String> hints = new ArrayList<String>();
    HashMap<String,String> riddles = new HashMap<String, String>();

    public RiddlePuzzle()
    {}

    /**
     * add the riddles to the HashMap
     */
    public void addToHashMap(String riddle, String riddle_answer)
    {
        this.riddle = riddle;
        this.riddle_answer = riddle_answer;
        this.riddles.put(riddle,riddle_answer);
    }


    /**
     * generate a random number "i" ranging from the first element of the HashMap to the
     * last one on the HashMap. Depending on "i", display riddle at that position
     */
    public String randomlyDisplayRiddle()
    {
        Random random = new Random();
        int i = random.nextInt(riddles.size());
        I = i;

        String key = (String)riddles.keySet().toArray()[i];
        String value = (new ArrayList<String>(riddles.values())).get(i);
        RIDDLE_KEY = key;
        RIDDLE_VALUE = value;
        return key;
    }

    public void addHints(String hint)
    {
        this.hint = hint;
        this.hints.add(this.hint);
    }

    public String showHint()
    {
        return this.hint/;
    }


    public String answerForPhrase(String phrase) {
        return riddles.get(phrase);
    }
    /**
     * check to see if the player guessed the riddle correct
     */
    public boolean isCorrect(String playerGuess)
    {
        if (playerGuess.equalsIgnoreCase(RIDDLE_VALUE))
            return true;
        else
            return false;
    }

    public int getSize()
    {
        return riddles.size();
    }
}
