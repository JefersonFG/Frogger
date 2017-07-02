package com.tcp.trabalhopratico.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tcp.trabalhopratico.helper.Assets;
import com.tcp.trabalhopratico.model.Grass;
import com.tcp.trabalhopratico.model.Lake;
import com.tcp.trabalhopratico.model.Road;

/**
 * Classe responsável por atualizar os gráficos dos elementos de jogo na tela.
 */
public class WorldRenderer {
    private World world;
    private SpriteBatch batch;

    /**
     * Construtor que recebe o objeto de renderização de elementos e uma referência ao
     * objeto World que tem referências aos elementos de jogo.
     * @param batch Objeto de renderização de elementos na tela.
     * @param world Objeto com referência aos demais objetos de jogo.
     */
    public WorldRenderer (SpriteBatch batch, World world) {
        this.world = world;
        this.batch = batch;
    }

    /**
     * Método que realiza a renderização dos objetos de plano de fundo e demais objetos de jogo,
     * nessa ordem.
     */
    public void render () {
        renderBackground();
        renderObjects();
    }

    /**
     * Renderiza objetos do primeiro plano, que são o sapo, os automóveis e os obstáculos.
     */
    private void renderObjects () {
        batch.enableBlending();
        batch.begin();
        renderFrog();
        renderAutomobiles();
        renderObstacles();
        batch.end();
    }

    /**
     * Renderiza objetos de plano de fundo, que são as estradas, os canteiros e o lago.
     */
    private void renderBackground () {
        batch.disableBlending();
        batch.begin();
        renderRoads();
        renderGrass();
        renderLake();
        batch.end();
    }

    /**
     * Renderiza o sapo na tela.
     */
    private void renderFrog() {
        /*
        TextureRegion keyFrame;
        switch (world.bob.state) {
            case Bob.BOB_STATE_FALL:
                keyFrame = Assets.bobFall.getKeyFrame(world.bob.stateTime, Animation.ANIMATION_LOOPING);
                break;
            case Bob.BOB_STATE_JUMP:
                keyFrame = Assets.bobJump.getKeyFrame(world.bob.stateTime, Animation.ANIMATION_LOOPING);
                break;
            case Bob.BOB_STATE_HIT:
            default:
                keyFrame = Assets.bobHit;
        }

        float side = world.bob.velocity.x < 0 ? -1 : 1;
        if (side < 0)
            batch.draw(keyFrame, world.bob.position.x + 0.5f, world.bob.position.y - 0.5f, side * 1, 1);
        else
            batch.draw(keyFrame, world.bob.position.x - 0.5f, world.bob.position.y - 0.5f, side * 1, 1);
        */
    }

    /**
     * Renderiza os automóveis na tela.
     */
    private void renderAutomobiles() {
        /*
        int len = world.platforms.size();
        for (int i = 0; i < len; i++) {
            Platform platform = world.platforms.get(i);
            TextureRegion keyFrame = Assets.platform;
            if (platform.state == Platform.PLATFORM_STATE_PULVERIZING) {
                keyFrame = Assets.brakingPlatform.getKeyFrame(platform.stateTime, Animation.ANIMATION_NONLOOPING);
            }

            batch.draw(keyFrame, platform.position.x - 1, platform.position.y - 0.25f, 2, 0.5f);
        }
        */
    }

    /**
     * Renderiza os obstáculos na tela.
     */
    private void renderObstacles() {
        /*
        int len = world.springs.size();
        for (int i = 0; i < len; i++) {
            Spring spring = world.springs.get(i);
            batch.draw(Assets.spring, spring.position.x - 0.5f, spring.position.y - 0.5f, 1, 1);
        }

        len = world.coins.size();
        for (int i = 0; i < len; i++) {
            Coin coin = world.coins.get(i);
            TextureRegion keyFrame = Assets.coinAnim.getKeyFrame(coin.stateTime, Animation.ANIMATION_LOOPING);
            batch.draw(keyFrame, coin.position.x - 0.5f, coin.position.y - 0.5f, 1, 1);
        }
        */
    }

    /**
     * Renderiza as estradas na tela.
     */
    private void renderRoads() {
        int len = world.roads.size();
        for (int i = 0; i < len; i++) {
            Road road = world.roads.get(i);
            Texture roadImage = Assets.road;
            batch.draw(roadImage, road.getPosition().x, road.getPosition().y);
        }

        /*
        int len = world.squirrels.size();
        for (int i = 0; i < len; i++) {
            Squirrel squirrel = world.squirrels.get(i);
            TextureRegion keyFrame = Assets.squirrelFly.getKeyFrame(squirrel.stateTime, Animation.ANIMATION_LOOPING);
            float side = squirrel.velocity.x < 0 ? -1 : 1;
            if (side < 0)
                batch.draw(keyFrame, squirrel.position.x + 0.5f, squirrel.position.y - 0.5f, side * 1, 1);
            else
                batch.draw(keyFrame, squirrel.position.x - 0.5f, squirrel.position.y - 0.5f, side * 1, 1);
        }
        */
    }

    /**
     * Renderiza os canteiros na tela.
     */
    private void renderGrass() {
        int len = world.grass.size();
        for (int i = 0; i < len; i++) {
            Grass grass = world.grass.get(i);
            Texture grassImage = Assets.grass;
            batch.draw(grassImage, grass.getPosition().x, grass.getPosition().y);
        }
    }

    /**
     * Renderiza o lago na tela.
     */
    private void renderLake() {
        Lake lake = world.lake;
        batch.draw(Assets.lake, lake.getPosition().x, lake.getPosition().y);
    }
}
