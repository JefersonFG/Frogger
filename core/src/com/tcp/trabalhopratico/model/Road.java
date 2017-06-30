package com.tcp.trabalhopratico.model;

/**
 * Classe que representa uma rua na tela. Contém as dimensões da rua.
 */
public class Road extends GameObject {
    private static final float ROAD_WIDTH = 0.8f;
    private static final float ROAD_HEIGHT = 0.8f;

    /**
     * Construtor que recebe a posição da rua.
     * @param x Posição da rua no eixo x.
     * @param y Posição da rua no eixo y.
     */
    public Road (float x, float y) {
        super(x, y, ROAD_WIDTH, ROAD_HEIGHT);
    }
}
