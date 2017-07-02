package com.tcp.trabalhopratico.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.tcp.trabalhopratico.helper.Assets;
import com.tcp.trabalhopratico.helper.Persistence;

/**
 * Classe que inicia o software. Responsável por carregar os recursos e iniciar
 * a tela principal e contém o tamanho padrão da tela.
 */
public class Frogger extends Game {
	static final int SCREEN_WIDTH = 320;
	static final int SCREEN_HEIGHT = 480;

	SpriteBatch batcher;

	/**
	 * Carrega a pontuação e as imagens do jogo e inicia a tela principal.
	 */
	@Override
	public void create() {
		batcher = new SpriteBatch();
		Persistence.load();
		Assets.load();
		setScreen(new MainMenuScreen(this));
	}
}
