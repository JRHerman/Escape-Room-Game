package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.EscapeRoomGame;
import com.mygdx.game.button.GameButton;

/**
 * A RoomCompleteScreen has a level completed label, a return to menu button, and
 * a restarted level button. It is rendered and initialized with the proper graphics.
 */
public class RoomCompleteScreen implements Screen {

    private EscapeRoomGame game;
    private Texture levelCompletedLabel;
    private GameButton returnToMenuButton;
    private GameButton restartLevelButton;

    private BitmapFont font;

    private static int pointsGained = -1;

    public RoomCompleteScreen(EscapeRoomGame escapeRoomGame) {
        this.game = escapeRoomGame;

        levelCompletedLabel = new Texture("level_completed.png");
        restartLevelButton = new GameButton("restart_button.png",  300 );
        returnToMenuButton = new GameButton("mainmenu_button.png",   200);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(150/255f,150/255f,150/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(levelCompletedLabel, Gdx.graphics.getWidth() / 2 - levelCompletedLabel.getWidth() / 2, Gdx.graphics.getHeight() - 100);
        game.batch.draw(restartLevelButton, restartLevelButton.getStartingX(), restartLevelButton.getStartingY());
        game.batch.draw(returnToMenuButton, returnToMenuButton.getStartingX(), returnToMenuButton.getStartingY());

        if (restartLevelButton.checkMouseOnButton() && Gdx.input.isTouched()) {
            game.setGameState(EscapeRoomGame.GameState.PAUSED);
            game.resetGame(game.getPlayScreen().getCurrentLevel());
            game.setScreen(game.getPlayScreen());
        }

        if (returnToMenuButton.checkMouseOnButton() && Gdx.input.isTouched()) {
            game.setGameState(EscapeRoomGame.GameState.PAUSED);
            game.resetGame(null);
            try {
                Thread.sleep(350);
            } catch (InterruptedException ie) {}
            game.setScreen(game.getMainMenuScreen());
        }

        if (pointsGained > - 1) {
            font.draw(game.batch, "Points gained: " + pointsGained, 450, 600);
        }



        game.batch.end();
    }

    public static void setPointsGained(int points) {
        pointsGained = points;
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
}
