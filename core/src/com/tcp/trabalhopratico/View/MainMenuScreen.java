package com.tcp.trabalhopratico.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tcp.trabalhopratico.Helper.Assets;
import com.tcp.trabalhopratico.Helper.Settings;

/**
 * Classe responsável por exibir a tela principal do jogo, de onde o usuário pode iniciar
 * uma nova partida ou ver o ranking de pontuações.
 */
class MainMenuScreen extends ScreenAdapter {
    private Frogger game;
    private OrthographicCamera guiCam;
    private Rectangle newGameBounds;
    private Rectangle highscoresBounds;
    private Vector3 touchPoint;

    /**
     * Inicia as variáveis.
     * @param game Objeto principal do jogo.
     */
    MainMenuScreen (Frogger game) {
        this.game = game;

        guiCam = new OrthographicCamera(Frogger.SCREEN_WIDTH, Frogger.SCREEN_HEIGHT);
        guiCam.position.set(Frogger.SCREEN_WIDTH / 2, Frogger.SCREEN_HEIGHT / 2, 0);
        newGameBounds = new Rectangle(160 - 150, 200 + 18, 300, 36);
        highscoresBounds = new Rectangle(160 - 150, 200 - 18, 300, 36);
        touchPoint = new Vector3();
    }

    /**
     * Trata cliques na tela para abrir as telas correspondentes quando necessário.
     */
    private void update () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (newGameBounds.contains(touchPoint.x, touchPoint.y)) {
                // game.setScreen(new GameScreen(game));
            }
            if (highscoresBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new com.tcp.trabalhopratico.View.HighscoresScreen(game));
            }
        }
    }

    /**
     * Atualiza as imagens na tela.
     */
    private void draw () {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);

        game.batcher.disableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.background, 0, 0, Frogger.SCREEN_WIDTH, Frogger.SCREEN_HEIGHT);
        game.batcher.end();

        game.batcher.enableBlending();
        game.batcher.begin();
        game.batcher.draw(com.tcp.trabalhopratico.Helper.Assets.logo, 160 - 274 / 2, Frogger.SCREEN_HEIGHT - 10 - 142, 274, 142);
        game.batcher.draw(com.tcp.trabalhopratico.Helper.Assets.mainMenu, 10, 200 - 110 / 2, 300, 110);
        game.batcher.end();
    }

    /**
     * Chama os métodos de atualização da tela e verificação de input.
     * @param delta Tempo em segundos desde a última renderização.
     */
    @Override
    public void render (float delta) {
        update();
        draw();
    }

    /**
     * Método chamado quando a aplicação é pausada, salva as configurações em disco.
     */
    @Override
    public void pause () {
        Settings.save();
    }
}
