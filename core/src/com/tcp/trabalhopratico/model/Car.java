package com.tcp.trabalhopratico.model;

/**
 * Classe que representa um carro na tela. Contém as dimensões do carro, sua velocidade
 * e uma implementação do método de atualização de posição na tela.
 */
public class Car extends Automobile {
    public static final float CAR_WIDTH = 71;
    public static final float CAR_HEIGHT = 48;
    private static final float CAR_VELOCITY = 2;

    /**
     * Construtor que recebe a posição inicial do carro e seta sua velocidade.
     * @param x Posição do carro no eixo x.
     * @param y Posição do carro no eixo y.
     */
    public Car (float x, float y) {
        super(x, y, CAR_WIDTH, CAR_HEIGHT);
        getVelocity().set(CAR_VELOCITY, 0);
    }

    /**
     * Método que atualiza a posição do carro na tela.
     * @param deltaTime Tempo em segundos desde a última atualização.
     */
    @Override
    public void update (float deltaTime) {
        // TODO Implementar update de Car
    }
}
