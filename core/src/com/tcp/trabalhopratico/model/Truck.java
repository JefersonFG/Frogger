package com.tcp.trabalhopratico.model;

/**
 * Classe que representa um caminhão na tela. Contém as dimensões do caminhão e sua velocidade.
 */
public class Truck extends Automobile {
    public static final float TRUCK_WIDTH = 134;
    public static final float TRUCK_HEIGHT = 48;
    private static final float TRUCK_VELOCITY = 1;

    /**
     * Construtor que recebe a posição inicial do caminhão e seta sua velocidade.
     * @param x Posição do caminhão no eixo x.
     * @param y Posição do caminhão no eixo y.
     * @param lane Faixa da rodovia pela qual o caminhão irá se mover.
     */
    public Truck (float x, float y, int lane) {
        super(x, y, TRUCK_WIDTH, TRUCK_HEIGHT, lane);
        getVelocity().set(TRUCK_VELOCITY, 0);
        movementDirection = DIRECTION_LEFT;
    }
}
