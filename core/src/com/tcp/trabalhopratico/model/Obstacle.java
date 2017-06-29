package com.tcp.trabalhopratico.model;

/**
 * Superclasse da hierarquia de obstáculos. Obstáculos impedem que o sapo ocupe a mesma posição
 * que o obstáculo mas não causam danos.
 */
public class Obstacle extends GameObject {
    /**
     * Implementação básica do construtor que chama o da superclassex
     * @param x Posição do objeto no eixo x.
     * @param y Posição do objeto no eixo y.
     * @param width Largura do objeto.
     * @param height Altura do objeto.
     */
    public Obstacle (float x, float y, float width, float height) {
        super(x, y, width, height);
    }
}
