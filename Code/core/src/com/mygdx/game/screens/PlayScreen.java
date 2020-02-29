package com.mygdx.game.screens;

import GameLevels.Level1;
import GameLevels.Level2;
import GameLevels.Level3;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.EscapeRoomGame;
import com.mygdx.game.mechanics.Level;

/**
 * A PlayScreen is a Screen that shows the current room. It is rendered
 * and initialized with the proper graphics.
 */
public class PlayScreen implements Screen {

    private EscapeRoomGame game;

    public static final int gameOverlayWidth = Gdx.graphics.getWidth() - 700;
    private Level currentLevel;

    BitmapFont font;

    public PlayScreen(EscapeRoomGame escapeRoomGame) {
        this.game = escapeRoomGame;

        font = new BitmapFont();
        font.setColor(Color.YELLOW);

        currentLevel = new Level3();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        currentLevel.drawElements(game, delta);
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

    public void setCurrentLevel(Level level) {
        currentLevel = level;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
