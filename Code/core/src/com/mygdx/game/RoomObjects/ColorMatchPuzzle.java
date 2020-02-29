package com.mygdx.game.RoomObjects;

import java.util.Random;

public class ColorMatchPuzzle {

    private enum peiceColor {
        RED, GREEN, BLUE;
    }

    private peiceColor lightColor;
    private peiceColor buttonColor;


    private boolean solved;

    public ColorMatchPuzzle() {
        solved = false;
    }

    public void changeColor() {
        Random rand = new Random();
        int lColor= rand.nextInt(3);
        int bColor = rand.nextInt(3);

        switch (lColor) {
            case 0: {
                lightColor = peiceColor.RED;
                break;
            }
            case 1: {
                lightColor = peiceColor.GREEN;
                break;
            }
            default: {
                lightColor = peiceColor.GREEN;
                break;
            }
        }

        switch (bColor) {
            case 0: {
                buttonColor = peiceColor.RED;
                break;
            }
            case 1: {
                buttonColor = peiceColor.GREEN;
                break;
            }
            default: {
                buttonColor = peiceColor.GREEN;
                break;
            }
        }
    }

    public boolean checkSolved() {
        return (lightColor == buttonColor);
    }
}
