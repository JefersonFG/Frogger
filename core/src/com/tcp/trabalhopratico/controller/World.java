package com.tcp.trabalhopratico.controller;

import com.badlogic.gdx.utils.Timer;
import com.tcp.trabalhopratico.model.Automobile;
import com.tcp.trabalhopratico.model.Frog;
import com.tcp.trabalhopratico.model.Grass;
import com.tcp.trabalhopratico.model.Lake;
import com.tcp.trabalhopratico.model.Obstacle;
import com.tcp.trabalhopratico.model.Road;

import java.util.ArrayList;
import java.util.List;

public class World {
    private static final float WORLD_WIDTH = 10;
    private static final float WORLD_HEIGHT = 48;
    private static final int WORLD_STATE_RUNNING = 0;
    private static final int WORLD_STATE_GAME_OVER = 1;

    int state;
    int score;
    int distanceSoFar;

    final Frog frog;
    final List<Automobile> automobiles;
    final List<Obstacle> obstacles;
    final List<Road> roads;
    final List<Grass> grass;
    final Lake lake;

    public World() {
        this.frog = new Frog(5, 0);
        this.automobiles = new ArrayList<Automobile>();
        this.obstacles = new ArrayList<Obstacle>();
        this.roads = new ArrayList<Road>();
        this.grass = new ArrayList<Grass>();
        this.lake = new Lake(0, WORLD_HEIGHT - 0.8f);

        this.state = WORLD_STATE_RUNNING;
        this.score = 0;
        this.distanceSoFar = 0;

        Timer.schedule(new Timer.Task() {
            @Override
            public void run () {
                updateTimer();
            }
        }, 1, 1);
    }

    private void generateLevel () {
        /*
        float y = Platform.PLATFORM_HEIGHT / 2;
        float maxJumpHeight = Bob.BOB_JUMP_VELOCITY * Bob.BOB_JUMP_VELOCITY / (2 * -gravity.y);
        while (y < WORLD_HEIGHT - WORLD_WIDTH / 2) {
            int type = rand.nextFloat() > 0.8f ? Platform.PLATFORM_TYPE_MOVING : Platform.PLATFORM_TYPE_STATIC;
            float x = rand.nextFloat() * (WORLD_WIDTH - Platform.PLATFORM_WIDTH) + Platform.PLATFORM_WIDTH / 2;

            Platform platform = new Platform(type, x, y);
            platforms.add(platform);

            if (rand.nextFloat() > 0.9f && type != Platform.PLATFORM_TYPE_MOVING) {
                Spring spring = new Spring(platform.position.x, platform.position.y + Platform.PLATFORM_HEIGHT / 2
                        + Spring.SPRING_HEIGHT / 2);
                springs.add(spring);
            }

            if (y > WORLD_HEIGHT / 3 && rand.nextFloat() > 0.8f) {
                Squirrel squirrel = new Squirrel(platform.position.x + rand.nextFloat(), platform.position.y
                        + Squirrel.SQUIRREL_HEIGHT + rand.nextFloat() * 2);
                squirrels.add(squirrel);
            }

            if (rand.nextFloat() > 0.6f) {
                Coin coin = new Coin(platform.position.x + rand.nextFloat(), platform.position.y + Coin.COIN_HEIGHT
                        + rand.nextFloat() * 3);
                coins.add(coin);
            }

            y += (maxJumpHeight - 0.5f);
            y -= rand.nextFloat() * (maxJumpHeight / 3);
        }

        castle = new Castle(WORLD_WIDTH / 2, y);
        */
    }

    public void updateTimer () {
        // TODO Implementar updateTimer
    }

    public void update (float deltaTime) {
        updateFrog();
        updateAutomobiles(deltaTime);
        if (frog.getState() != Frog.FROG_STATE_HIT)
            checkCollisions();
        checkGameOver();
    }

    private void updateFrog() {
        /*
        if (bob.state != Bob.BOB_STATE_HIT && bob.position.y <= 0.5f) bob.hitPlatform();
        if (bob.state != Bob.BOB_STATE_HIT) bob.velocity.x = -accelX / 10 * Bob.BOB_MOVE_VELOCITY;
        bob.update(deltaTime);
        heightSoFar = Math.max(bob.position.y, heightSoFar);
        */
    }

    private void updateAutomobiles(float deltaTime) {
        /*
        int len = platforms.size();
        for (int i = 0; i < len; i++) {
            Platform platform = platforms.get(i);
            platform.update(deltaTime);
            if (platform.state == Platform.PLATFORM_STATE_PULVERIZING && platform.stateTime > Platform.PLATFORM_PULVERIZE_TIME) {
                platforms.remove(platform);
                len = platforms.size();
            }
        }
        */
    }

    private void checkCollisions () {
        checkAutomobileCollisions();
        checkObstacleCollision();
    }

    private void checkAutomobileCollisions() {
        /*
        if (bob.velocity.y > 0) return;

        int len = platforms.size();
        for (int i = 0; i < len; i++) {
            Platform platform = platforms.get(i);
            if (bob.position.y > platform.position.y) {
                if (bob.bounds.overlaps(platform.bounds)) {
                    bob.hitPlatform();
                    listener.jump();
                    if (rand.nextFloat() > 0.5f) {
                        platform.pulverize();
                    }
                    break;
                }
            }
        }
        */
    }

    private void checkObstacleCollision() {
        /*
        int len = squirrels.size();
        for (int i = 0; i < len; i++) {
            Squirrel squirrel = squirrels.get(i);
            if (squirrel.bounds.overlaps(bob.bounds)) {
                bob.hitSquirrel();
                listener.hit();
            }
        }
        */
    }

    private void checkGameOver () {
        /*
        if (heightSoFar - 7.5f > bob.position.y) {
            state = WORLD_STATE_GAME_OVER;
        }
        */
    }
}
