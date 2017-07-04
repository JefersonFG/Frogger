package com.tcp.trabalhopratico.controller;

import com.tcp.trabalhopratico.model.Automobile;
import com.tcp.trabalhopratico.model.Car;
import com.tcp.trabalhopratico.model.Frog;
import com.tcp.trabalhopratico.model.Grass;
import com.tcp.trabalhopratico.model.Lake;
import com.tcp.trabalhopratico.model.Motorcycle;
import com.tcp.trabalhopratico.model.Obstacle;
import com.tcp.trabalhopratico.model.Road;
import com.tcp.trabalhopratico.model.Tree;
import com.tcp.trabalhopratico.model.Truck;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gerencia o fluxo do jogo. É responsável por criar o ambiente do jogo, atualizar os
 * estados dos objetos e verificar colisões, finalizando o jogo se necessário. Guarda referências
 * para todos os objetos em jogo e controla a distância percorrida em direção ao objetivo.
 */
public class World {
    private static final int WORLD_WIDTH = 320;
    private static final int WORLD_HEIGHT = 480;
    private static final int HORIZONTAL_SECTION_SIZE = WORLD_WIDTH / 5;
    private static final int VERTICAL_SECTION_SIZE = WORLD_HEIGHT / 10;
    private static final int MAX_DISTANCE = 8;
    private static final int WORLD_STATE_RUNNING = 0;
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
        this.frog = new Frog(HORIZONTAL_SECTION_SIZE * 2, 0);
        this.automobiles = new ArrayList<Automobile>();
        this.obstacles = new ArrayList<Obstacle>();
        this.roads = new ArrayList<Road>();
        this.grass = new ArrayList<Grass>();
        this.lake = new Lake(0, 8 * VERTICAL_SECTION_SIZE);

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
        Grass grass2 = new Grass(0, 3 * VERTICAL_SECTION_SIZE);
        Grass grass3 = new Grass(0, 5 * VERTICAL_SECTION_SIZE);

        grass.add(grass1);
        grass.add(grass2);
        grass.add(grass3);

        Road road1 = new Road(0, VERTICAL_SECTION_SIZE);
        Road road2 = new Road(0, 2 * VERTICAL_SECTION_SIZE);
        Road road3 = new Road(0, 4 * VERTICAL_SECTION_SIZE);
        Road road4 = new Road(0, 6 * VERTICAL_SECTION_SIZE);
        Road road5 = new Road(0, 7 * VERTICAL_SECTION_SIZE);

        roads.add(road1);
        roads.add(road2);
        roads.add(road3);
        roads.add(road4);
        roads.add(road5);

        Tree tree1 = new Tree(HORIZONTAL_SECTION_SIZE * 2, 3 * VERTICAL_SECTION_SIZE);
        Tree tree2 = new Tree(HORIZONTAL_SECTION_SIZE * 4, 5 * VERTICAL_SECTION_SIZE);

        obstacles.add(tree1);
        obstacles.add(tree2);

        Car car1 = new Car(0 - Car.CAR_WIDTH, VERTICAL_SECTION_SIZE, 1);
        Car car2 = new Car(0 - Car.CAR_WIDTH, 6 * VERTICAL_SECTION_SIZE, 6);
        Truck truck1 = new Truck(WORLD_WIDTH, 2 * VERTICAL_SECTION_SIZE, 2);
        Truck truck2 = new Truck(WORLD_WIDTH, 4 * VERTICAL_SECTION_SIZE, 4);
        Motorcycle motorcycle = new Motorcycle(WORLD_WIDTH, 7 * VERTICAL_SECTION_SIZE, 7);

        automobiles.add(car1);
        automobiles.add(car2);
        automobiles.add(truck1);
        automobiles.add(truck2);
        automobiles.add(motorcycle);
    }

    /**
     * Atualiza o estado dos objetos.
     * @param deltaTime Tempo em segundos desde a última atualização.
     */
    public void update (float deltaTime) {
        updateAutomobiles(deltaTime);
        if (frog.getState() != Frog.FROG_STATE_HIT)
            checkAutomobileCollisions();
        checkGameWon();
    }

    /**
     * Atualiza o estados dos automóveis, que se movem na tela com o passar do tempo.
     * @param deltaTime Tempo em segundos desde a última atualização.
     */
    private void updateAutomobiles(float deltaTime) {
        int len = automobiles.size();
        for (int i = 0; i < len; i++) {
            Automobile automobile = automobiles.get(i);
            automobile.update(deltaTime);
            resetAutomobileOutOfBounds(automobile);
        }
    }

    private void resetAutomobileOutOfBounds(Automobile automobile) {
        if (automobile.getMovementDirection() == Automobile.DIRECTION_RIGHT) {
            if (automobile.getPosition().x > (WORLD_WIDTH + automobile.getBounds().width)) {
                automobile.getPosition().x = 0 - automobile.getBounds().width;
            }
        } else {
            if (automobile.getPosition().x < (0 - automobile.getBounds().width)) {
                automobile.getPosition().x = WORLD_WIDTH + automobile.getBounds().width;
            }
        }
    }

    /**
     * Método que move o sapo para cima e verifica colisões, se houver colisão com um automóvel
     * atualiza o status do sapo e finaliza o jogo, se houver com um obstáculo desfaz o movimento.
     */
    public void moveFrogUp() {
        frog.moveUp();
        checkCollisions();
    }

    /**
     * Método que move o sapo para baixo e verifica colisões, se houver colisão com um automóvel
     * atualiza o status do sapo e finaliza o jogo, se houver com um obstáculo desfaz o movimento.
     */
    public void moveFrogDown() {
        frog.moveDown();
        checkCollisions();
    }

    /**
     * Método que move o sapo para a direita e verifica colisões, se houver colisão com um automóvel
     * atualiza o status do sapo e finaliza o jogo, se houver com um obstáculo desfaz o movimento.
     */
    public void moveFrogRight() {
        frog.moveRight();
        checkCollisions();
    }

    /**
     * Método que move o sapo para a esquerda e verifica colisões, se houver colisão com um automóvel
     * atualiza o status do sapo e finaliza o jogo, se houver com um obstáculo desfaz o movimento.
     */
    public void moveFrogLeft() {
        frog.moveLeft();
        checkCollisions();
    }

    /**
     * Verifica se não ocorreram colisões do sapo com obstáculos ou automóveis.
     */
    private void checkCollisions() {
        checkBorderCollision();
        checkAutomobileCollisions();
        checkObstacleCollision();
    }

    /**
     * Verifica se o movimento do sapo não o faz sair dos limites da tela, se fizer desfaz o movimento.
     */
    private void checkBorderCollision() {
        if (frog.getPosition().x < 0 || frog.getPosition().x >= WORLD_WIDTH ||
                frog.getPosition().y < 0 || frog.getPosition().y >= (WORLD_HEIGHT - frog.getBounds().height))
            frog.undoMove();
    }

    /**
     * Verifica se houve colisão do sapo com um automóvel. Se houver, diminui uma vida do sapo.
     * Se o sapo ainda tiver vidas sobrando, reinicia sua posição. Caso não tenha mais vidas
     * sobrando finaliza o jogo.
     */
    private void checkAutomobileCollisions() {
        int len = automobiles.size();

        for (int i = 0; i < len; i++) {
            Automobile automobile = automobiles.get(i);
            if (isFrogAndAutomobileOnSameSpot(automobile)) {
                frog.setLives(frog.getLives() - 1);

                if (frog.getLives() > 0) {
                    frog.resetPosition(HORIZONTAL_SECTION_SIZE * 2, 0);
                    distanceSoFar = 0;
                } else {
                    frog.hitAutomobile();
                    state = WORLD_STATE_GAME_OVER;
                }
            }
        }
    }

    /**
     * Verifica se o sapo está no mesmo ponto que o automóvel.
     * @param automobile Automóvel a verificar se está no mesmo ponto que o sapo.
     * @return True caso estejam na mesma posição, false do contrário.
     */
    private boolean isFrogAndAutomobileOnSameSpot(Automobile automobile) {
        return ((frog.getPosition().x >= automobile.getPosition().x &&
                frog.getPosition().x <= (automobile.getPosition().x + automobile.getBounds().width)) ||
                ((frog.getPosition().x + frog.getBounds().width) >= automobile.getPosition().x &&
                (frog.getPosition().x + frog.getBounds().width) <=
                        (automobile.getPosition().x + automobile.getBounds().width)))
                && (frog.getPosition().y == automobile.getPosition().y);
    }

    /**
     * Verifica se houve colisão do sapo com um obstáculo.
     */
    private void checkObstacleCollision() {
        int len = obstacles.size();
        boolean hitObstacle = false;

        for (int i = 0; i < len; i++) {
            Obstacle obstacle = obstacles.get(i);
            if (isFrogAndObstacleOnSameSpot(obstacle)) {
                frog.undoMove();
                hitObstacle = true;
            }
        }

        // TODO Refatorar confirmação do movimento, deve ocorrer após todas as verificações
        if (!hitObstacle) {
            updateDistanceSoFar();
            frog.confirmMove();
        }
    }

    /**
     * Verifica se o sapo está no mesmo ponto que o obstáculo.
     * @param obstacle Obstáculo a verificar se está no mesmo ponto que o sapo.
     * @return True caso estejam na mesma posição, false do contrário.
     */
    private boolean isFrogAndObstacleOnSameSpot(Obstacle obstacle) {
        return (frog.getPosition().x == obstacle.getPosition().x)
                && (frog.getPosition().y == obstacle.getPosition().y);
    }

    /**
     * Função que verifica se o sapo está se movendo em direção ao objetivo ou na direção oposta
     * e registra no campo de distância percorrida, para posterior contagem de pontos.
     */
    private void updateDistanceSoFar() {
        if (frog.getPosition().y > frog.getLastPosition().y)
            distanceSoFar++;
        else if (frog.getPosition().y < frog.getLastPosition().y)
            distanceSoFar--;
    }

    /**
     * Verifica se o jogo deve ser finalizado.
     */
    private void checkGameWon() {
        if (distanceSoFar == MAX_DISTANCE)
            state = WORLD_STATE_GAME_OVER;
    }
}
