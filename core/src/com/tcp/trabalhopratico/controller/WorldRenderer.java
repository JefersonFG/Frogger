package com.tcp.trabalhopratico.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
    private static final float FRUSTUM_WIDTH = 10;
    private static final float FRUSTUM_HEIGHT = 15;
    private World world;
    private SpriteBatch batch;

    public WorldRenderer (SpriteBatch batch, World world) {
        this.world = world;
        this.batch = batch;
    }

    public void render () {
        renderBackground();
        renderObjects();
    }

    private void renderObjects () {
        batch.enableBlending();
        batch.begin();
        renderFrog();
        renderAutomobiles();
        renderObstacles();
        batch.end();
    }

    private void renderBackground () {
        batch.disableBlending();
        batch.begin();
        //batch.draw(Assets.backgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH,
        //        FRUSTUM_HEIGHT);
        renderRoads();
        renderGrass();
        renderLake();
        batch.end();
    }

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

    private void renderRoads() {
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

    private void renderGrass() {
        //
    }

    private void renderLake() {
        /*
        Castle castle = world.castle;
        batch.draw(Assets.castle, castle.position.x - 1, castle.position.y - 1, 2, 2);
        */
    }
}
