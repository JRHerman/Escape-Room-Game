package com.mygdx.game.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * A GameButton has bounds and can check if it is pressed.
 */
public class GameButton extends Texture{

    // these variables will be used when checking if the player is hovering over the button
    private int lowerXBound;
    private int upperXBound;
    private int lowerYBound;
    private int upperYBound;


    public GameButton(String path, int yOffset) {
       super(path);

        lowerXBound = Gdx.graphics.getWidth() / 2 - this.getWidth() / 2;
        upperXBound =  lowerXBound + this.getWidth();
        lowerYBound = yOffset;
        upperYBound = yOffset - this.getHeight();
    }

    public GameButton(String path, int x, int y) {
        super(path);

        lowerXBound = x;
        upperXBound =  lowerXBound + this.getWidth();
        lowerYBound = Gdx.graphics.getHeight() - y;
        upperYBound = lowerYBound - this.getHeight();
    }

    public int getStartingX() {return lowerXBound;}
    public int getStartingY() {return Gdx.graphics.getHeight() - lowerYBound;}

    public boolean checkMouseOnButton() {

        int currentX = Gdx.input.getX();
        int currentY = Gdx.input.getY();

        return (currentX > lowerXBound && currentX < upperXBound  && currentY < lowerYBound && currentY > upperYBound);

    }

}
