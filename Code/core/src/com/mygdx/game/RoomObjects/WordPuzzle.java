package com.mygdx.game.RoomObjects;
import java.util.ArrayList;
import java.util.Random;
public class WordPuzzle {
    private ArrayList<AnagramPuzzle> anagramPuzzles = new ArrayList<AnagramPuzzle>();
    private Random rand = new Random();
    public WordPuzzle(){

    }

    public void addAnagram(AnagramPuzzle ana)
    {
        anagramPuzzles.add(ana);
    }

    public AnagramPuzzle chooseRandomAnagram()
    {
        int randNum = rand.nextInt(anagramPuzzles.size());
        return anagramPuzzles.get(randNum);
    }

}
