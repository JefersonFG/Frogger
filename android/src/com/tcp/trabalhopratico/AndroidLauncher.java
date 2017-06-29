package com.tcp.trabalhopratico;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tcp.trabalhopratico.view.Frogger;

/**
 * Classe responsável por iniciar o jogo no sistema Android.
 */
public class AndroidLauncher extends AndroidApplication {
	/**
	 * Método responsável por criar e iniciar o jogo.
	 * @param savedInstanceState Argumentos padrão.
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Frogger(), config);
	}
}
