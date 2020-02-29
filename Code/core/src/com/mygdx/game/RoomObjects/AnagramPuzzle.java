package com.mygdx.game.RoomObjects;
import java.util.Random;
//anagram puzzle
public class AnagramPuzzle extends WordPuzzle {
    private String word;
    private String anagram;
    private String clue;
    Random randNum = new Random();

    public AnagramPuzzle(String wd, String c){
       word = wd;
       anagram = "";
       clue = c;
    }

    public boolean checkIfCorrect(String userGuess)
    {
        if (userGuess.equalsIgnoreCase(word) && (anagram.equals("") == false)) {
            return true;
        }
        else{
            return false;
        }
    }

    public void shufflePhrase()
    {
        char wordToChar[] = word.toCharArray();
        for(int i = 0; i < wordToChar.length; i++){
            int ran = randNum.nextInt(wordToChar.length);
            char tmp = wordToChar[i];
            wordToChar[i] = wordToChar[ran];
            wordToChar[ran] = tmp;
        }
        anagram = new String(wordToChar);
    }

    public String getAnagram(){
        return anagram;
    }

    public String getWord(){
        return word;
    }

    public String getClue(){return clue; }
}


