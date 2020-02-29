package com.mygdx.game.RoomObjects;

public class SequencePuzzle extends NumberPuzzle {
    private String sequence;
    private int answer;
    private String clue;
    public SequencePuzzle(String seq, int ans, String c)
    {
        sequence = seq;
        answer = ans;
        clue = c;
    }

    public boolean checkAnswer(int ans)
    {
        if (answer == ans)
            return true;
        else
            return false;
    }

    public String getSequence()
    {
        return sequence;
    }

    public int getAnswer()
    {
        return answer;
    }

    public String getClue(){return clue; }
}
