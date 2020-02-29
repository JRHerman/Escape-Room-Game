package com.mygdx.game.RoomObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Water
{

    protected int x;
    protected int y;
    protected int xMargin;
    protected int yMargin;
    protected Texture frozen;
    protected Texture liquid;

    public Water()
    {
        frozen = new Texture("floortile_inactive.png");
    }

    abstract public int xMovement(int temp, int pX, int preX);

    abstract public int yMovement(int temp, int pY, int preY);



    public boolean overlap(int pX, int pY, int preX, int preY)
    {
        if((pY > y && preY <= y)&&(pX > x && pX < x + xMargin))
        {
            return true;
        }
        else if(pY < (y + yMargin) && preY >= (y + yMargin)&&(pX > x && pX < x + xMargin))
        {
            return true;
        }
        else if(pX > x && preX <= x&&(pY > y && pY < y + yMargin))
        {
            return true;
        }
        else if(pX < (x + xMargin) && preX >= (x + xMargin)&&(pY > y && pY < y + yMargin))
        {
            return true;
        }

        return false;
    }

    public void draw(Batch gameBatch, int temp)
    {
        if(temp == 0)
        {
            gameBatch.draw(frozen, x, y, 50, 50);
        }
        else
        {
            gameBatch.draw(liquid, x, y, 50, 50);
        }
    }
}
