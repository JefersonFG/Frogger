package com.tcp.trabalhopratico.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.tcp.trabalhopratico.controller.World;
import com.tcp.trabalhopratico.controller.WorldRenderer;
import com.tcp.trabalhopratico.helper.Assets;
import com.tcp.trabalhopratico.helper.Persistence;

/**
 * Classe que representa a tela de jogo. Contém instâncias do Controller e exibe ao usuário
 * informações de tempo e vida restantes no jogo. É responsável por pausar o jogo e exibir a tela
 * de fim de jogo, calcular a pontuação final e salvar e receber o input do usuário.
 */
class GameScreen extends ScreenAdapter {
    private static final int GAME_READY = 0;
    private static final int GAME_RUNNING = 1;
    private static final int GAME_PAUSED = 2;
    private static final int GAME_OVER = 3;

    private static final int MAX_TIME = 60;

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
     * Construtor que inicia o jogo, instanciando o controller, iniciando o timer e os processadores
     * de input do usuário, um para gestos na tela e um para teclas do teclado.
     * @param game Referência ao objeto de jogo para renderização de elementos na tela.
     */
    GameScreen (Frogger game) {
        this.game = game;

        state = GAME_READY;
        guiCam = new OrthographicCamera(Frogger.SCREEN_WIDTH, Frogger.SCREEN_HEIGHT);
        guiCam.position.set(Frogger.SCREEN_WIDTH / 2, Frogger.SCREEN_HEIGHT / 2, 0);
        touchPoint = new Vector3();
        world = new World();
        renderer = new WorldRenderer(game.batcher, world);
        pauseBounds = new Rectangle(Frogger.SCREEN_WIDTH - 64, Frogger.SCREEN_HEIGHT - 64, 64, 64);
        resumeBounds = new Rectangle(Frogger.SCREEN_WIDTH / 2 - 96, Frogger.SCREEN_HEIGHT / 2, 192, 36);
        quitBounds = new Rectangle(Frogger.SCREEN_WIDTH / 2 - 96, Frogger.SCREEN_HEIGHT / 2 - 36, 192, 36);
        lastScore = 0;
        lastLives = world.getFrogLives();
        lastTime = MAX_TIME;
        scoreString = "SCORE: " + lastScore;
        livesString = "LIVES: " + lastLives;
        timeString = "TIME: " + lastTime;

        InputMultiplexer multiplexer = new InputMultiplexer();

        multiplexer.addProcessor((new SimpleDirectionGestureDetector
                (new SimpleDirectionGestureDetector.DirectionListener() {
            @Override
            public void onUp() {
                if (state != GAME_RUNNING)
                    return;
                world.moveFrogUp();
            }
            @Override
            public void onDown() {
                if (state != GAME_RUNNING)
                    return;
                world.moveFrogDown();
            }
            @Override
            public void onRight() {
                if (state != GAME_RUNNING)
                    return;
                world.moveFrogRight();
            }
            @Override
            public void onLeft() {
                if (state != GAME_RUNNING)
                    return;
                world.moveFrogLeft();
            }
        })));

        multiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (state != GAME_RUNNING)
                    return false;

                boolean result = false;

                switch(keyCode) {
                    case (Input.Keys.W):
                    case (Input.Keys.DPAD_UP):
                        result = true;
                        world.moveFrogUp();
                        break;
                    case (Input.Keys.S):
                    case (Input.Keys.DPAD_DOWN):
                        result = true;
                        world.moveFrogDown();
                        break;
                    case (Input.Keys.D):
                    case (Input.Keys.DPAD_RIGHT):
                        result = true;
                        world.moveFrogRight();
                        break;
                    case (Input.Keys.A):
                    case (Input.Keys.DPAD_LEFT):
                        result = true;
                        world.moveFrogLeft();
                        break;
                }

                return result;
            }
        });

        Gdx.input.setInputProcessor(multiplexer);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run () {
                updateTimer();
            }
        }, 1, 1);
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
     * Atualiza o tempo restante na tela e finaliza o jogo quando o tempo chega a zero.
     */
    private void updateTimer () {
        if (state != GAME_RUNNING)
            return;
        if (--lastTime > 0)
            timeString = "TIME: " + lastTime;
        else
            finishGame();
    }

    /**
     * Atualiza o jogo durante o estado READY. Quando o usuário clica na tela o
     * estado é alterado para RUNNING.
     */
    private void updateReady () {
        if (Gdx.input.justTouched())
            state = GAME_RUNNING;
    }

    /**
     * Atualiza o jogo no estado RUNNING. É realizada uma chamada para o método de atualização
     * do controller e verificado o estado resultante do jogo, além de atualizado número de vidas
     * restantes na tela.
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

        world.update(deltaTime);

        if (world.getFrogLives() != lastLives) {
            lastLives = world.getFrogLives();
            livesString = "LIVES: " + lastLives;
        }

        if (world.getState() == World.WORLD_STATE_GAME_OVER)
            finishGame();
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
        game.batcher.draw(Assets.headerBackground, 0, Frogger.SCREEN_HEIGHT - 48, Frogger.SCREEN_WIDTH, 48);
        game.batcher.draw(Assets.ready, Frogger.SCREEN_WIDTH / 2 - 192 / 2,
                Frogger.SCREEN_HEIGHT / 2 - 32 / 2, 192, 32);
    }

    /**
     * Atualiza o conteúdo na tela no estado RUNNING, que são os indicadores de vidas e tempo restantes.
     */
    private void presentRunning () {
        game.batcher.draw(Assets.headerBackground, 0, Frogger.SCREEN_HEIGHT - 48, Frogger.SCREEN_WIDTH, 48);
        game.batcher.draw(Assets.pause, Frogger.SCREEN_WIDTH - 45, Frogger.SCREEN_HEIGHT - 45, 45, 45);
        Assets.font.draw(game.batcher, livesString, 10, Frogger.SCREEN_HEIGHT - 5);
        Assets.font.draw(game.batcher, timeString, 10, Frogger.SCREEN_HEIGHT - 25);
    }

    /**
     * Atualiza o conteúdo na tela no estado PAUSED, que são as opções de voltar ao jogo, terminar o jogo
     * e os indicadores de vidas e tempo restantes.
     */
    private void presentPaused () {
        game.batcher.draw(Assets.headerBackground, 0, Frogger.SCREEN_HEIGHT - 48, Frogger.SCREEN_WIDTH, 48);
        game.batcher.draw(Assets.pauseMenu, Frogger.SCREEN_WIDTH / 2 - 192 / 2,
                Frogger.SCREEN_HEIGHT / 2 - 96 / 2, 192, 96);
        Assets.font.draw(game.batcher, livesString, 10, Frogger.SCREEN_HEIGHT - 5);
        Assets.font.draw(game.batcher, timeString, 10, Frogger.SCREEN_HEIGHT - 25);
    }

    /**
     * Atualiza o conteúdo na tela no estado GAMEOVER, que é o texto GAMEOVER e a pontuação final.
     */
    private void presentGameOver () {
        game.batcher.draw(Assets.headerBackground, 0, Frogger.SCREEN_HEIGHT - 48, Frogger.SCREEN_WIDTH, 48);
        game.batcher.draw(Assets.gameOver, Frogger.SCREEN_WIDTH / 4,
                Frogger.SCREEN_HEIGHT / 2 - 96 / 2, 160, 96);
        glyphLayout.setText(Assets.font, scoreString);
        Assets.font.draw(game.batcher, scoreString,
                Frogger.SCREEN_WIDTH / 2 - glyphLayout.width / 2, Frogger.SCREEN_HEIGHT - 20);
    }

    /**
     * Finaliza o jogo calculando e salvando a pontuação e mudando o estado para GAME_OVER.
     */
    private void finishGame() {
        state = GAME_OVER;
        determineFinalScore();
        saveScore();
    }

    /**
     * Determina a pontuação final baseado na distância percorrida, nas vidas e no tempo restantes.
     */
    private void determineFinalScore() {
        lastScore = lastLives * 10 + lastTime + world.getDistanceSoFar() * 2;
    }

    /**
     * Verifica se a pontuação do usuário é maior do que a dos recordes e se for salva em disco.
     * Também prepara a string para exibição ao usuário.
     */
    private void saveScore() {
        if (lastScore >= Persistence.highscores[4]) {
            scoreString = "NEW HIGHSCORE: " + lastScore;
            Persistence.addScore(lastScore);
            Persistence.save();
        } else {
            scoreString = "FINAL SCORE: " + lastScore;
        }
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
