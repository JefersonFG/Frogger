package com.tcp.trabalhopratico.model;

/**
 * Classe que representa uma árvore na tela. Contém as dimensões da árvore.
 */
public class Tree extends Obstacle {
    private static final float TREE_WIDTH = 64;
    private static final float TREE_HEIGHT = 48;

    /**
     * Construtor que recebe a posição da árvore.
     * @param x Posição da árvore no eixo x.
     * @param y Posição da árvore no eixo y.
     */
    public Tree (float x, float y) {
        super(x, y, TREE_WIDTH, TREE_HEIGHT);
    }
}
