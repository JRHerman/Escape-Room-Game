package com.mygdx.game.RoomObjects;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.mechanics.MapEvent;

public class DeepWater extends Water
{
    public DeepWater(int X, int Y, int XM, int YM, MapEvent M)
    {
        super();
        x = X;
        y = Y;
        xMargin = XM;
        yMargin = YM;
        liquid = new Texture("floortile_active.png");
    }

    public int xMovement(int temp, int pX, int preX)
    {
        if(temp == 0)//ice
        {//does not work
            if(pX > x && preX <= x)
            {
                return pX + 10;
            }
            else if(pX < (x + xMargin) && preX >= (x + xMargin))
            {
                return pX - 10;
            }
        }
        else
        {
            if(pX > x && preX <= x)
            {
                return x;
            }
            else if(pX < (x + xMargin) && preX >= (x + xMargin))
            {
                return x + xMargin;
            }
        }
        return pX;
    }

    public int yMovement(int temp, int pY, int preY)
    {
        if(temp == 0)//ice
        {
            if(pY > y && preY <= y)
            {
                return y + 10;
            }
            else if(pY < (y + yMargin) && preY >= (y + yMargin))
            {
                return y - 10;
            }
        }
        else
        {
            if(pY > y && preY <= y)
            {
                return y;
            }
            else if(pY < (y + yMargin) && preY >= (y + yMargin))
            {
                return y + yMargin;
            }
        }
        return pY;
    }
}

