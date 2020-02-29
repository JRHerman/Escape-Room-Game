package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.EscapeRoomGame;
import com.mygdx.game.button.GameButton;

/**
 * A gameOverScreen has a restart button, and return to menu button in a game
 * The screen can render and is initialized with the proper graphics.
 */
public class GameOverScreen implements Screen {
    /* the purpose of this screen is to tell the player that they ran out of time for the room,
        this screen will ask the player if they want to start over or go back to the main menu. */


    private EscapeRoomGame game;

    private Texture gameOverLabel;
    private GameButton restartGameButton;
    private GameButton returnToMenuButton;


    public GameOverScreen(EscapeRoomGame escapeRoomGame) {
        this.game = escapeRoomGame;
        gameOverLabel = new Texture("gameover_label.png");
        restartGameButton = new GameButton("restart_button.png", 200);
        returnToMenuButton = new GameButton("mainmenu_button.png", 300);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(150/255f,150/255f,150/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(gameOverLabel, Gdx.graphics.getWidth() / 2 - gameOverLabel.getWidth() / 2, Gdx.graphics.getHeight() - 100);
        //game.batch.draw(restartGameButton, restartGameButton.getStartingX(), restartGameButton.getStartingY());
        game.batch.draw(returnToMenuButton, returnToMenuButton.getStartingX(), returnToMenuButton.getStartingY());

        // the player clicks the restart game button
//        if (restartGameButton.checkMouseOnButton() && Gdx.input.isTouched()) {
//            game.setGameState(EscapeRoomGame.GameState.PLAYING);
//            game.resetGame(game.getPlayScreen().getCurrentLevel());
//            game.setScreen(game.getPlayScreen());
//        }

        // the player clicks the main menu button
        if (returnToMenuButton.checkMouseOnButton() && Gdx.input.isTouched()) {
            game.setGameState(EscapeRoomGame.GameState.PAUSED);
            game.resetGame(null);
            game.setScreen(game.getMainMenuScreen());
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
}
