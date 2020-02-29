package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.EscapeRoomGame;
import com.mygdx.game.button.GameButton;

/**
 * A PauseScreen is a Screen that has a pausedLabel return button and main menu
 * button. It is rendered and checked if it is pressed, and initialized with
 * proper graphics.
 */
public class PauseScreen implements Screen {

    private EscapeRoomGame game;

    private Texture pausedLabel;
    private GameButton returnButton;
    private GameButton mainMenuButton;

    public PauseScreen(EscapeRoomGame escapeRoomGame) {
        this.game = escapeRoomGame;
        pausedLabel = new Texture("paused_label.png");
        returnButton = new GameButton("return_button.png", 200);
        mainMenuButton = new GameButton("mainmenu_button.png", 320);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(150/255f,150/255f,150/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(pausedLabel, Gdx.graphics.getWidth() / 2 - pausedLabel.getWidth() / 2, Gdx.graphics.getHeight() - 80);
        game.batch.draw(returnButton, returnButton.getStartingX(), returnButton.getStartingY());
        game.batch.draw(mainMenuButton, mainMenuButton.getStartingX(), mainMenuButton.getStartingY());

        if (checkMouseOnReturnButton() && Gdx.input.isTouched()) {
            game.setScreen(game.getPlayScreen());
        }

        if (checkMouseOnMainMenuButton() && Gdx.input.isTouched()) {
            // will go back to the main menu and reset the game
            game.setScreen(game.getMainMenuScreen());
            game.resetGame(null);

        }

        game.batch.end();



    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    private boolean checkMouseOnReturnButton() {
        int startingX = Gdx.graphics.getWidth() / 2 - returnButton.getWidth() / 2;
        int endingX = startingX + returnButton.getWidth();
        int startingY = 200;
        int endingY = startingY - returnButton.getHeight();

        int currentX = Gdx.input.getX();
        int currentY = Gdx.input.getY();

        return (currentX > startingX && currentX < endingX  && currentY < startingY && currentY > endingY);
    }

    private boolean checkMouseOnMainMenuButton() {
        int startingX = Gdx.graphics.getWidth() / 2 - mainMenuButton.getWidth() / 2;
        int endingX = startingX + mainMenuButton.getWidth();
        int startingY = 320;
        int endingY = startingY - mainMenuButton.getHeight();

        int currentX = Gdx.input.getX();
        int currentY = Gdx.input.getY();

        return (currentX > startingX && currentX < endingX  && currentY < startingY && currentY > endingY);
    }


}
