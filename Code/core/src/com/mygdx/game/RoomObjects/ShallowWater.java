package com.mygdx.game.RoomObjects;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.mechanics.MapEvent;

public class ShallowWater extends Water
{
    public ShallowWater(int X, int Y, MapEvent M)
    {
        super();
        x = X;
        y = Y;
        liquid = new Texture("floortile_active.png");
    }

    public int xMovement(int temp, int pX, int preX)
    {
        if(temp == 0)//ice
        {
            if(pX > x && preX <= x)
            {
                return pX + 10;
            }
            else if(pX < (x + xMargin) && preX >= (x + xMargin))
            {
                return pX - 10;
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
        return pY;
    }
}

