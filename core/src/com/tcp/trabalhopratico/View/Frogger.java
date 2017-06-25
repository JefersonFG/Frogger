package com.tcp.trabalhopratico.View;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.tcp.trabalhopratico.Helper.Assets;
import com.tcp.trabalhopratico.Helper.Settings;

/**
 * Classe que inicia o software. Responsável por carregar os recursos e iniciar
 * a tela principal.
 */
public class Frogger extends Game {
	SpriteBatch batcher;

	/**
	 * Carrega a pontuação e as imagens do jogo e inicia a tela principal.
	 */
	@Override
	public void create() {
		batcher = new SpriteBatch();
		Settings.load();
		Assets.load();
		setScreen(new MainMenuScreen(this));
	}
}
