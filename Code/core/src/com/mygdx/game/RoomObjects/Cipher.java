package com.mygdx.game.RoomObjects;
import java.util.ArrayList;
import java.util.Random;
public class Cipher {
    private ArrayList<CipherPuzzle> cipherPuzzles = new ArrayList<CipherPuzzle>();
    private Random rand = new Random();
    public Cipher(){

    }

    public void addCipher(CipherPuzzle cip)
    {
        cipherPuzzles.add(cip);
    }
    public CipherPuzzle chooseRandomCipher()
    {
        int randNum = rand.nextInt(cipherPuzzles.size());
        return cipherPuzzles.get(randNum);
    }
}
