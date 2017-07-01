package com.tcp.trabalhopratico.model;

/**
 * Classe que representa uma motocicleta na tela. Contém as dimensões da motocicleta, sua velocidade
 * e uma implementação do método de atualização de posição na tela.
 */
public class Motorcycle extends Automobile {
    private static final float MOTORCYCLE_WIDTH = 0.8f;
    private static final float MOTORCYCLE_HEIGHT = 0.8f;
    private static final float MOTORCYCLE_VELOCITY = 3f;

    /**
     * Construtor que recebe a posição inicial da motocicleta e seta sua velocidade.
     * @param x Posição da motocicleta no eixo x.
     * @param y Posição da motocicleta no eixo y.
     */
    public Motorcycle (float x, float y) {
        super(x, y, MOTORCYCLE_WIDTH, MOTORCYCLE_HEIGHT);
        getVelocity().set(MOTORCYCLE_VELOCITY, 0);
    }

    /**
     * Método que atualiza a posição da motocicleta na tela.
     * @param deltaTime Tempo em segundos desde a última atualização.
     */
    @Override
    public void update (float deltaTime) {
        // TODO Implementar update de Motorcycle
    }
}
