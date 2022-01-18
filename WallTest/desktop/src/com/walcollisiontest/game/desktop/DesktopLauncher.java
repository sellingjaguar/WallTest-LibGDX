package com.walcollisiontest.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.walcollisiontest.game.WallCollisionTest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Wall test";

		config.width = 1200;
		config.height = 700;

		new LwjglApplication(new WallCollisionTest(), config);
	}
}
