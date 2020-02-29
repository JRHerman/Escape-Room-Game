package com.mygdx.game.screens;

import GameLevels.Level1;
import GameLevels.Level2;
import GameLevels.Level3;
import GameLevels.Level4;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.EscapeRoomGame;
import com.mygdx.game.button.GameButton;


/**
 * This class is unimplemented yet.
 */
public class LevelSelectionScreen implements Screen {

    private EscapeRoomGame game;
    private Texture selectionLabel;

    private GameButton level1Button;
    private GameButton level2Button;
    private GameButton level3Button;
    private GameButton level4Button;

    private static boolean isLevel2Locked = true;

    public LevelSelectionScreen(EscapeRoomGame escapeRoomGame) {

        this.game = escapeRoomGame;
        selectionLabel = new Texture("level_select_label.png");
        level1Button = new GameButton("level1_button.png", 250);
        level2Button = new GameButton("level2_button.png", 325);
        level3Button = new GameButton("level3_button.png", 400);
        level4Button = new GameButton("level4_button.png", 475);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(150/255f,150/255f,150/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(selectionLabel, Gdx.graphics.getWidth() / 2 - selectionLabel.getWidth() /2,
                Gdx.graphics.getHeight() - 100);

        game.batch.draw(level1Button, level1Button.getStartingX(), level1Button.getStartingY());
        game.batch.draw(level2Button, level2Button.getStartingX(), level2Button.getStartingY());
        game.batch.draw(level3Button, level3Button.getStartingX(), level3Button.getStartingY());
        game.batch.draw(level4Button, level3Button.getStartingX(), level4Button.getStartingY());


        // Button listeners

        if (level1Button.checkMouseOnButton() && Gdx.input.isTouched()) {
            game.getPlayScreen().setCurrentLevel(new Level1());
            game.setScreen(game.getPlayScreen());
        }

        if (level2Button.checkMouseOnButton() && Gdx.input.isTouched()) {
            if (!isLevel2Locked) {
                game.getPlayScreen().setCurrentLevel(new Level2());
                game.setScreen(game.getPlayScreen());
            }
        }

        if (level3Button.checkMouseOnButton() && Gdx.input.isTouched()) {
            game.getPlayScreen().setCurrentLevel(new Level3());
            game.setScreen(game.getPlayScreen());
        }

        if (level4Button.checkMouseOnButton() && Gdx.input.isTouched()) {
            game.getPlayScreen().setCurrentLevel(new Level4());
            game.setScreen(game.getPlayScreen());
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public static void unlockedLevel2() {
        isLevel2Locked = false;
    }
}
