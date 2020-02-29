package com.mygdx.game.RoomObjects;
import java.util.Random;
// cipher puzzle
public class CipherPuzzle extends Cipher{
    private String answer;
    private String cipher;
    private String clue;
    private Random rand = new Random();
    public CipherPuzzle(String ans, String c){
        answer = ans;
        cipher = "";
        clue = c;
    }

    public boolean checkIfCorrect(String userGuess)
    {
        if (userGuess.equalsIgnoreCase(answer) && (cipher.equals("") == false)) {
            return true;
        }
        else{
            return false;
        }
    }

    public void makeCipher(){
        String build = "";
        int shift = rand.nextInt(26);
        for (int i = 0; i < answer.length(); i++)
        {
            char chr= (char) answer.charAt(i);
            if (chr != ' '){
                char letter = (char)(answer.charAt(i) + shift);
                if (letter > 'z'){
                    build = build + (char)(answer.charAt(i)-(26-shift));
                }
                else{
                    build = build + (char)(answer.charAt(i) + shift);
                }
            }
            else
            {
                build = build + ' ';
            }
        }
        cipher = build;
    }

    public String getAnswer(){
        return answer;
    }

    public String getCipher(){
        return cipher;
    }
    public String getClue(){return clue;}

}
