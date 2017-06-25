package com.tcp.trabalhopratico.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tcp.trabalhopratico.View.Frogger;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Title";
		config.height = 624;
		config.width = 416;
		new LwjglApplication(new Frogger(), config);
	}
}
