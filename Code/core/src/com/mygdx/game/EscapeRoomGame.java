package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.mechanics.Level;
import com.mygdx.game.screens.*;

/**
 * An EscapeRoomGame has a game state, main menu screen, play screen, pause screen, room
 * complete screen, and a game over screen. They are created, rendered, and disposed.
 * We are able to reset a game, and set and get a game state. We can also get the screens
 * stated above
 */
public class EscapeRoomGame extends Game {


	public enum GameState {
		MENU, PLAYING, PAUSED
	}

	private GameState gameState;

	public SpriteBatch batch;

	// declaring the different Screens
	private MainMenuScreen mainMenuScreen;
	private PlayScreen playScreen;
	private PauseScreen pauseScreen;
	private GameOverScreen gameOverScreen;
	private RoomCompleteScreen roomCompleteScreen;
	private LevelSelectionScreen levelSelectionScreen;

	public static final int WIDTH  = 920;
	public static final int HEIGHT = 600;

	@Override
	public void create () {
		batch = new SpriteBatch();

		mainMenuScreen = new MainMenuScreen(this);
		playScreen = new PlayScreen(this);
		pauseScreen = new PauseScreen(this);
		gameOverScreen = new GameOverScreen(this);
		roomCompleteScreen = new RoomCompleteScreen(this);
		levelSelectionScreen = new LevelSelectionScreen(this);

		setGameState(GameState.MENU);

		this.setScreen(mainMenuScreen);
	}


	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void resetGame(Level level) {
		playScreen.setCurrentLevel(level);
		playScreen = new PlayScreen(this);
	}


	// return methods for screens
	public MainMenuScreen getMainMenuScreen() {return mainMenuScreen;}
	public PlayScreen getPlayScreen() {return playScreen;}
	public PauseScreen getPauseScreen() {return pauseScreen;}
	public GameOverScreen getGameOverScreen() {return gameOverScreen;}
	public RoomCompleteScreen getRoomCompleteScreen() {return roomCompleteScreen;}
	public LevelSelectionScreen getLevelSelectionScreen() {return levelSelectionScreen;}

	public void setGameState(GameState gameState) {this.gameState = gameState;}

	public GameState getGameState() {return gameState;}


}
