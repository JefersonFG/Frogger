package com.tcp.trabalhopratico.model;

/**
 * Classe que representa um lago na tela. Contém as dimensões do lago.
 */
public class Lake extends GameObject {
    private static final float LAKE_WIDTH = 0.8f;
    private static final float LAKE_HEIGHT = 0.8f;

    /**
     * Construtor que recebe a posição do lago.
     * @param x Posição da árvore no eixo x.
     * @param y Posição da árvore no eixo y.
     */
    public Lake (float x, float y) {
        super(x, y, LAKE_WIDTH, LAKE_HEIGHT);
    }
}
