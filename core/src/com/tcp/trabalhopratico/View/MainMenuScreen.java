package com.tcp.trabalhopratico.View;

/**
 * Created by erick on 11/06/17.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tcp.trabalhopratico.Helper.Assets;

public class MainMenuScreen extends ScreenAdapter {
    com.tcp.trabalhopratico.View.Frogger game;
    OrthographicCamera guiCam;
    Rectangle playBounds;
    Rectangle highscoresBounds;
    Vector3 touchPoint;

    public MainMenuScreen (com.tcp.trabalhopratico.View.Frogger game) {
        this.game = game;

        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
        playBounds = new Rectangle(160 - 150, 200 + 18, 300, 36);
        highscoresBounds = new Rectangle(160 - 150, 200 - 18, 300, 36);
        touchPoint = new Vector3();
    }

    public void update () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                /*game.setScreen(new GameScreen(game));*/
                return;
            }
            if (highscoresBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new com.tcp.trabalhopratico.View.HighscoresScreen(game));
                return;
            }
        }
    }

    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);

        game.batcher.disableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.background, 0, 0, 320, 480);
        game.batcher.end();

        game.batcher.enableBlending();
        game.batcher.begin();
        game.batcher.draw(com.tcp.trabalhopratico.Helper.Assets.logo, 160 - 274 / 2, 480 - 10 - 142, 274, 142);
        game.batcher.draw(com.tcp.trabalhopratico.Helper.Assets.mainMenu, 10, 200 - 110 / 2, 300, 110);
        game.batcher.end();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

    @Override
    public void pause () {
        com.tcp.trabalhopratico.Helper.Settings.save();
    }
}
