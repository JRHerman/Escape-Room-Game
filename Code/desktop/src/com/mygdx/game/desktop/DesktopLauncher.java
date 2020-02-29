package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.EscapeRoomGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.foregroundFPS = 30;
		config.width = EscapeRoomGame.WIDTH;
		config.height = EscapeRoomGame.HEIGHT;
		config.resizable = false;

		new LwjglApplication(new EscapeRoomGame(), config);

	}
}
