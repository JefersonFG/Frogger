package com.tcp.trabalhopratico.model;

/**
 * Classe que representa um lago na tela. Contém as dimensões do lago.
 */
public class Lake extends GameObject {
    private static final float LAKE_WIDTH = 320;
    private static final float LAKE_HEIGHT = 48;

    /**
     * Construtor que recebe a posição do lago.
     * @param x Posição do lago no eixo x.
     * @param y Posição do lago no eixo y.
     */
    public Lake (float x, float y) {
        super(x, y, LAKE_WIDTH, LAKE_HEIGHT);
    }
}
