package com.tcp.trabalhopratico.model;

/**
 * Classe que representa um canteiro na tela. Contém as dimensões do canteiro.
 */
public class Grass extends GameObject {
    private static final float GRASS_WIDTH = 320;
    private static final float GRASS_HEIGHT = 48;

    /**
     * Construtor que recebe a posição do canteiro.
     * @param x Posição do canteiro no eixo x.
     * @param y Posição do canteiro no eixo y.
     */
    public Grass (float x, float y) {
        super(x, y, GRASS_WIDTH, GRASS_HEIGHT);
    }
}
