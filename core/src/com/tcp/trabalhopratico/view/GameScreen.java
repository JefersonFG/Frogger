package com.tcp.trabalhopratico.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tcp.trabalhopratico.controller.World;
import com.tcp.trabalhopratico.controller.WorldRenderer;
import com.tcp.trabalhopratico.helper.Assets;

/**
 * Classe que representa a tela de jogo. Contém instâncias do Controller e exibe ao usuário
 * informações de tempo e vida restantes no jogo. É responsável por pausar o jogo e exibir a tela
 * de fim de jogo.
 */
class GameScreen extends ScreenAdapter {
    private static final int GAME_READY = 0;
    private static final int GAME_RUNNING = 1;
    private static final int GAME_PAUSED = 2;
    private static final int GAME_OVER = 3;

    private Frogger game;

    private int state;
    private OrthographicCamera guiCam;
    private Vector3 touchPoint;
    private World world;
    private WorldRenderer renderer;
    private Rectangle pauseBounds;
    private Rectangle resumeBounds;
    private Rectangle quitBounds;
    private int lastScore;
    private int lastLives;
    private int lastTime;
    private String scoreString;
    private String livesString;
    private String timeString;

    private GlyphLayout glyphLayout = new GlyphLayout();

    /**
     * Construtor que inicia o jogo, instanciando o controller
     * @param game Referência ao objeto de jogo para renderização de elementos na tela.
     */
    GameScreen (Frogger game) {
        this.game = game;

        state = GAME_READY;
        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
        touchPoint = new Vector3();
        world = new World();
        renderer = new WorldRenderer(game.batcher, world);
        pauseBounds = new Rectangle(320 - 64, 480 - 64, 64, 64);
        resumeBounds = new Rectangle(160 - 96, 240, 192, 36);
        quitBounds = new Rectangle(160 - 96, 240 - 36, 192, 36);
        scoreString = "SCORE: 0";
        livesString = "LIVES: 0";
        timeString = "TIME: 0";
    }

    /**
     * Atualiza o conteúdo na tela com o passar do tempo.
     * @param deltaTime Tempo em segundos desde a última atualização.
     */
    private void update (float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;

        switch (state) {
            case GAME_READY:
                updateReady();
                break;
            case GAME_RUNNING:
                updateRunning(deltaTime);
                break;
            case GAME_PAUSED:
                updatePaused();
                break;
            case GAME_OVER:
                updateGameOver();
                break;
        }
    }

    /**
     * Atualiza o jogo durante o estado READY. Quando o usuário clica na tela o
     * estado é alterado para RUNNING.
     */
    private void updateReady () {
        if (Gdx.input.justTouched()) {
            state = GAME_RUNNING;
        }
    }

    /**
     * Atualiza o jogo no estado RUNNING. É realizada uma chamada para o método de atualização
     * do controller e verificado o estado resultante do jogo.
     * @param deltaTime Tempo em segundos desde a última atualização.
     */
    private void updateRunning (float deltaTime) {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (pauseBounds.contains(touchPoint.x, touchPoint.y)) {
                state = GAME_PAUSED;
                return;
            }
        }

        /*

        Application.ApplicationType appType = Gdx.app.getType();

        // should work also with Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)
        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            world.update(deltaTime, Gdx.input.getAccelerometerX());
        } else {
            float accel = 0;
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) accel = 5f;
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) accel = -5f;
            world.update(deltaTime, accel);
        }
        if (world.score != lastScore) {
            lastScore = world.score;
            scoreString = "SCORE: " + lastScore;
        }
        if (world.state == World.WORLD_STATE_NEXT_LEVEL) {
            game.setScreen(new WinScreen(game));
        }
        if (world.state == World.WORLD_STATE_GAME_OVER) {
            state = GAME_OVER;
            if (lastScore >= Settings.highscores[4])
                scoreString = "NEW HIGHSCORE: " + lastScore;
            else
                scoreString = "SCORE: " + lastScore;
            Settings.addScore(lastScore);
            Settings.save();
        }

        */
    }

    /**
     * Atualiza o jogo no estado PAUSED. Verifica se o usuário clicou no botão de resumir o jogo
     * ou de finalizar a partida e retornar ao menu principal.
     */
    private void updatePaused () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (resumeBounds.contains(touchPoint.x, touchPoint.y)) {
                state = GAME_RUNNING;
            } else if (quitBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }

    /**
     * Atualiza o jogo no estado GAMEOVER. Quando o usuário clica na tela ele é levado de volta
     * ao menu principal.
     */
    private void updateGameOver () {
        if (Gdx.input.justTouched()) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    /**
     * Atualiza as imagens na tela, chamando o método do controller e depois o método de atualização
     * apropriado para o estado atual da tela.
     */
    private void draw () {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);
        game.batcher.enableBlending();
        game.batcher.begin();
        switch (state) {
            case GAME_READY:
                presentReady();
                break;
            case GAME_RUNNING:
                presentRunning();
                break;
            case GAME_PAUSED:
                presentPaused();
                break;
            case GAME_OVER:
                presentGameOver();
                break;
        }
        game.batcher.end();
    }

    /**
     * Atualiza o conteúdo na tela no estado READY, que é a palavra READY sobre o fundo do jogo.
     */
    private void presentReady () {
        game.batcher.draw(Assets.ready, 160 - 192 / 2, 240 - 32 / 2, 192, 32);
    }

    /**
     * Atualiza o conteúdo na tela no estado RUNNING, que são os indicadores de vidas e tempo restantes.
     */
    private void presentRunning () {
        game.batcher.draw(Assets.pause, 320 - 55, 480 - 55, 55, 55);
        Assets.font.draw(game.batcher, livesString, 10, 480 - 10);
        Assets.font.draw(game.batcher, timeString, 10, 480 - 30);
    }

    /**
     * Atualiza o conteúdo na tela no estado PAUSED, que são as opções de voltar ao jogo, terminar o jogo
     * e os indicadores de vidas e tempo restantes.
     */
    private void presentPaused () {
        game.batcher.draw(Assets.pauseMenu, 160 - 192 / 2, 240 - 96 / 2, 192, 96);
        Assets.font.draw(game.batcher, livesString, 10, 480 - 10);
        Assets.font.draw(game.batcher, timeString, 10, 480 - 30);
    }

    /**
     * Atualiza o conteúdo na tela no estado GAMEOVER, que é o texto GAMEOVER e a pontuação final.
     */
    private void presentGameOver () {
        game.batcher.draw(Assets.gameOver, 160 - 160 / 2, 240 - 96 / 2, 160, 96);
        glyphLayout.setText(Assets.font, scoreString);
        Assets.font.draw(game.batcher, scoreString, 160 - glyphLayout.width / 2, 480 - 20);
    }

    /**
     * Atualiza o conteúdo na tela.
     * @param delta Tempo em segundos desde a última atualização.
     */
    @Override
    public void render (float delta) {
        update(delta);
        draw();
    }

    /**
     * Pausa o jogo quando o software recebe um sinal de pause global.
     */
    @Override
    public void pause () {
        if (state == GAME_RUNNING)
            state = GAME_PAUSED;
    }
}
