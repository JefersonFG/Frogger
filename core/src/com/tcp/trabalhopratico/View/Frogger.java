package com.tcp.trabalhopratico.View;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class Frogger extends Game {
	public SpriteBatch batcher;
	
	@Override
	public void create () {
		batcher = new SpriteBatch();
		//Settings.load();
		com.tcp.trabalhopratico.Helper.Assets.load();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
