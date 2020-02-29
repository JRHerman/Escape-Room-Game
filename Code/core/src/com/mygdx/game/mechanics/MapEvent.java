package com.mygdx.game.mechanics;

public class MapEvent
{
    private int state;

    public MapEvent(int inishol)
    {
        state = inishol;
    }

    public int getState()
    {
        return state;
    }

    void setState(int nState)
    {
        state = nState;
    }
}

