package com.tcp.trabalhopratico.model;

/**
 * Classe que representa um carro na tela. Contém as dimensões do carro e sua velocidade.
 */
public class Car extends Automobile {
    public static final float CAR_WIDTH = 69;
    public static final float CAR_HEIGHT = 48;
    private static final float CAR_VELOCITY = 2;

    /**
     * Construtor que recebe a posição inicial do carro e seta sua velocidade.
     * @param x Posição do carro no eixo x.
     * @param y Posição do carro no eixo y.
     * @param lane Faixa da rodovia pela qual o carro irá se mover.
     */
    public Car (float x, float y, int lane) {
        super(x, y, CAR_WIDTH, CAR_HEIGHT, lane);
        getVelocity().set(CAR_VELOCITY, 0);
        movementDirection = DIRECTION_RIGHT;
    }
}
