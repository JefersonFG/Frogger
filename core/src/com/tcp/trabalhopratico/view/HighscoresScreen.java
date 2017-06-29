package com.tcp.trabalhopratico.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tcp.trabalhopratico.helper.Assets;
import com.tcp.trabalhopratico.helper.Settings;

/**
 * Classe responsável por exibir a tela de pontuação do jogo, exibindo as cinco maiores pontuações.
 */
class HighscoresScreen extends ScreenAdapter {
    private Frogger game;
    private OrthographicCamera guiCam;
    private Rectangle backBounds;
    private Vector3 touchPoint;
    private String[] highScores;
    private float xOffset = 0;

    /**
     * Inicia as variáveis.
     * @param game Objeto principal do jogo.
     */
    HighscoresScreen (Frogger game) {
        this.game = game;

        GlyphLayout glyphLayout = new GlyphLayout();

        guiCam = new OrthographicCamera(Frogger.SCREEN_WIDTH, Frogger.SCREEN_HEIGHT);
        guiCam.position.set(Frogger.SCREEN_WIDTH / 2, Frogger.SCREEN_HEIGHT / 2, 0);
        backBounds = new Rectangle(0, 0, 64, 64);
        touchPoint = new Vector3();
        highScores = new String[5];
        for (int i = 0; i < 5; i++) {
            highScores[i] = i + 1 + ". " + Settings.highscores[i];
            glyphLayout.setText(Assets.font, highScores[i]);
            xOffset = Math.max(glyphLayout.width, xOffset);
        }
        xOffset = 160 - xOffset / 2 + Assets.font.getSpaceWidth() / 2;
    }

    /**
     * Trata cliques na tela para retornar à tela principal quando necessário.
     */
    private void update () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (backBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }

    /**
     * Atualiza as imagens na tela.
     */
    private void draw () {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();

        game.batcher.setProjectionMatrix(guiCam.combined);
        game.batcher.disableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.background, 0, 0, Frogger.SCREEN_WIDTH, Frogger.SCREEN_HEIGHT);
        game.batcher.end();

        game.batcher.enableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.highScoresRegion, 10, 380, 300, 33);

        float y = 150;
        for (int i = 4; i >= 0; i--) {
            Assets.font.draw(game.batcher, highScores[i], xOffset, y);
            y += Assets.font.getLineHeight();
        }

        game.batcher.draw(Assets.arrow, 0, 0, 64, 64);
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
}
