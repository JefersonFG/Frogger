package com.tcp.trabalhopratico.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.tcp.trabalhopratico.view.Frogger;

/**
 * Classe responsável por iniciar o jogo no navegador no modo Html.
 */
public class HtmlLauncher extends GwtApplication {
        /**
         * Método que retorna a configuração do modo Html.
         * @return Configuração do modo Html.
         */
        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(416, 624);
        }

        /**
         * Método que inicia o jogo no modo Html.
         * @return Objeto de jogo.
         */
        @Override
        public ApplicationListener createApplicationListener () {
                return new Frogger();
        }
}