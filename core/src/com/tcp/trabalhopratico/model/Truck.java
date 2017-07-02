package com.tcp.trabalhopratico.model;

/**
 * Classe que representa um caminhão na tela. Contém as dimensões do caminhão, sua velocidade
 * e uma implementação do método de atualização de posição na tela.
 */
public class Truck extends Automobile {
    public static final float TRUCK_WIDTH = 109;
    public static final float TRUCK_HEIGHT = 48;
    private static final float TRUCK_VELOCITY = 1;

    /**
     * Construtor que recebe a posição inicial do caminhão e seta sua velocidade.
     * @param x Posição do caminhão no eixo x.
     * @param y Posição do caminhão no eixo y.
     */
    public Truck (float x, float y) {
        super(x, y, TRUCK_WIDTH, TRUCK_HEIGHT);
        getVelocity().set(TRUCK_VELOCITY, 0);
    }

    /**
     * Método que atualiza a posição do caminhão na tela.
     * @param deltaTime Tempo em segundos desde a última atualização.
     */
    @Override
    public void update (float deltaTime) {
        // TODO Implementar update de Truck
    }
}
