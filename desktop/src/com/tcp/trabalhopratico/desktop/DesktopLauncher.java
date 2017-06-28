package com.tcp.trabalhopratico.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tcp.trabalhopratico.View.Frogger;

/**
 * Classe responsável por iniciar o jogo no desktop.
 */
public class DesktopLauncher {
	/**
	 * Inicia o jogo.
	 * @param arg Argumentos padrão.
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Frogger";
		config.height = 624;
		config.width = 416;
		new LwjglApplication(new Frogger(), config);
	}
}
