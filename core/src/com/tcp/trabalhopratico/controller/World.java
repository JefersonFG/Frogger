package com.tcp.trabalhopratico.controller;

import com.tcp.trabalhopratico.model.Automobile;
import com.tcp.trabalhopratico.model.Frog;
import com.tcp.trabalhopratico.model.Grass;
import com.tcp.trabalhopratico.model.Lake;
import com.tcp.trabalhopratico.model.Obstacle;
import com.tcp.trabalhopratico.model.Road;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gerencia o fluxo do jogo. É responsável por criar o ambiente do jogo, atualizar os
 * estados dos objetos e verificar colisões, finalizando o jogo se necessário. Guarda referências
 * para todos os objetos em jogo e controla a distância percorrida em direção ao objetivo.
 */
public class World {
    static final float WORLD_WIDTH = 60;
    static final float WORLD_HEIGHT = 10;
    private static final int HORIZONTAL_SECTION_SIZE = 12;
    private static final int VERTICAL_SECTION_SIZE = 48;
    public static final int WORLD_STATE_RUNNING = 0;
    public static final int WORLD_STATE_GAME_OVER = 1;

    private int state;
    private int distanceSoFar;

    final Frog frog;
    final List<Automobile> automobiles;
    final List<Obstacle> obstacles;
    final List<Road> roads;
    final List<Grass> grass;
    final Lake lake;

    /**
     * Construtor que inicializa todos os objetos de jogo.
     */
    public World() {
        this.frog = new Frog(HORIZONTAL_SECTION_SIZE * 3, 0);
        this.automobiles = new ArrayList<Automobile>();
        this.obstacles = new ArrayList<Obstacle>();
        this.roads = new ArrayList<Road>();
        this.grass = new ArrayList<Grass>();
        this.lake = new Lake(0, WORLD_HEIGHT - 2);

        this.state = WORLD_STATE_RUNNING;
        this.distanceSoFar = 0;

        generateLevel();
    }

    /**
     * Getter de Frog.lives.
     * @return Vidas restantes do sapo.
     */
    public int getFrogLives() {
        return frog.getLives();
    }

    /**
     * Getter de state.
     * @return Estado de World.
     */
    public int getState() {
        return state;
    }

    /**
     * Getter de distanceSoFar.
     * @return Distância percorrida em direção ao objetivo.
     */
    public int getDistanceSoFar() {
        return distanceSoFar;
    }

    /**
     * Método que inicializa o ambiente dispondo os objetos de jogo na tela.
     */
    private void generateLevel () {
        Grass grass1 = new Grass(0, 0);
        Grass grass2 = new Grass(0, 3);
        Grass grass3 = new Grass(0, 5);

        grass.add(grass1);
        grass.add(grass2);
        grass.add(grass3);

        Road road1 = new Road(0, 1);
        Road road2 = new Road(0, 2);
        Road road3 = new Road(0, 4);
        Road road4 = new Road(0, 6);
        Road road5 = new Road(0, 7);

        roads.add(road1);
        roads.add(road2);
        roads.add(road3);
        roads.add(road4);
        roads.add(road5);
    }

    /**
     * Atualiza o estado dos objetos.
     * @param deltaTime Tempo em segundos desde a última atualização.
     */
    public void update (float deltaTime) {
        updateFrog();
        updateAutomobiles(deltaTime);
        if (frog.getState() != Frog.FROG_STATE_HIT)
            checkCollisions();
        checkGameOver();
    }

    /**
     * Atualiza o estado do spo.
     */
    private void updateFrog() {
        /*
        if (bob.state != Bob.BOB_STATE_HIT && bob.position.y <= 0.5f) bob.hitPlatform();
        if (bob.state != Bob.BOB_STATE_HIT) bob.velocity.x = -accelX / 10 * Bob.BOB_MOVE_VELOCITY;
        bob.update(deltaTime);
        heightSoFar = Math.max(bob.position.y, heightSoFar);
        */
    }

    /**
     * Atualiza o estados dos automóveis, que se movem na tela com o passar do tempo.
     * @param deltaTime Tempo em segundos desde a última atualização.
     */
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

    /**
     * Verifica se não ocorreram colisões do sapo com obstáculos ou automóveis.
     */
    private void checkCollisions () {
        checkAutomobileCollisions();
        checkObstacleCollision();
    }

    /**
     * Verifica se houve colisão do sapo com um automóvel.
     */
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

    /**
     * Verifica se houve colisão do sapo com um obstáculo.
     */
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

    /**
     * Verifica se o jogo deve ser finalizado.
     */
    private void checkGameOver () {
        /*
        if (heightSoFar - 7.5f > bob.position.y) {
            state = WORLD_STATE_GAME_OVER;
        }
        */
    }
}
