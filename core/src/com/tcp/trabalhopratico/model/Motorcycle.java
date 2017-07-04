package com.tcp.trabalhopratico.model;

/**
 * Classe que representa uma motocicleta na tela. Contém as dimensões da motocicleta, sua velocidade
 * e uma implementação do método de atualização de posição na tela.
 */
public class Motorcycle extends Automobile {
    public static final float MOTORCYCLE_WIDTH = 59;
    public static final float MOTORCYCLE_HEIGHT = 48;
    private static final float MOTORCYCLE_VELOCITY = 3;

    /**
     * Construtor que recebe a posição inicial da motocicleta e seta sua velocidade.
     * @param x Posição da motocicleta no eixo x.
     * @param y Posição da motocicleta no eixo y.
     * @param lane Faixa da rodovia pela qual a motocicleta irá se mover.
     */
    public Motorcycle (float x, float y, int lane) {
        super(x, y, MOTORCYCLE_WIDTH, MOTORCYCLE_HEIGHT, lane);
        getVelocity().set(MOTORCYCLE_VELOCITY, 0);
        movementDirection = DIRECTION_LEFT;
    }
}
