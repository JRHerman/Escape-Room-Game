package com.mygdx.game.RoomObjects;
import java.util.Random;
import java.util.ArrayList;
public class NumberPuzzle {
    private ArrayList<SequencePuzzle> sequencePuzzles = new ArrayList<SequencePuzzle>();

    private Random rand = new Random();
    public NumberPuzzle(){}

    public void addSequence(SequencePuzzle ana)
    {
        sequencePuzzles.add(ana);
    }
    public SequencePuzzle chooseRandomSequence()
    {
        int randNum = rand.nextInt(sequencePuzzles.size());
        return sequencePuzzles.get(randNum);
    }
}
