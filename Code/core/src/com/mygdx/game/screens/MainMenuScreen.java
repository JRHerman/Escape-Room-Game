package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.EscapeRoomGame;
import com.mygdx.game.button.GameButton;

/**
 * A MainMenuScreen has a welcomeLabel, a start button, an options button,
 * and an exit button. It can be rendered and is initialized with the proper graphics
 */
public class MainMenuScreen implements Screen {

    EscapeRoomGame game;

    private Texture welcomeLabel;
    private GameButton startButton;
    private GameButton optionsButton;
    private GameButton exitButton;


    // TODO - remove this after checking coordinates
    private BitmapFont font;

    public MainMenuScreen(EscapeRoomGame escapeRoomGame) {
        this.game = escapeRoomGame;
        welcomeLabel = new Texture("welcome_label.png");

        startButton = new GameButton("start_button.png", 200);
        optionsButton = new GameButton("options_button.png",320);
        exitButton = new GameButton("exit_button.png", 440);

        font = new BitmapFont();
        font.setColor(Color.YELLOW);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(150/255f,150/255f,150/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        // TODO - remove constant values from here and replace with final variables
        // draw the welcome label
        game.batch.draw(welcomeLabel, Gdx.graphics.getWidth() / 2 - welcomeLabel.getWidth() /2,
                Gdx.graphics.getHeight() - 100);

        // usefull for current mouse coordinates
        //font.draw(game.batch, "x: " + currentX, 700, 580);
        //font.draw(game.batch, "y: " + currentY, 700, 560);

        // draw the buttons for the main menu
        game.batch.draw(startButton, startButton.getStartingX(), startButton.getStartingY());
        game.batch.draw(optionsButton, optionsButton.getStartingX(), optionsButton.getStartingY());
        game.batch.draw(exitButton, exitButton.getStartingX(), exitButton.getStartingY());


        if (startButton.checkMouseOnButton() && Gdx.input.isTouched()) {
            game.setGameState(EscapeRoomGame.GameState.PLAYING);
            game.setScreen(game.getLevelSelectionScreen());
        }

        if (optionsButton.checkMouseOnButton() && Gdx.input.isTouched()) {
            // TODO - update the screen to the options menu when this button is pressed
        }

        if (exitButton.checkMouseOnButton() && Gdx.input.isTouched()) {
           Gdx.app.exit();
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

}
