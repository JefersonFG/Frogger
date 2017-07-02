package com.tcp.trabalhopratico.model;

/**
 * Superclasse da hierarquia de automóveis. Automóveis se movem nas estradas com o passar do tempo
 * e atropelam o sapo em caso de colisão.
 */
public abstract class Automobile extends DynamicGameObject {
    /**
     * Implementação básica do construtor que chama o da superclasse.
     * @param x Posição do objeto no eixo x.
     * @param y Posição do objeto no eixo y.
     * @param width Largura do objeto.
     * @param height Altura do objeto.
     */
    public Automobile (float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    /**
     * Método abstrato de atualização da posição do automóvel na tela.
     * @param deltaTime Tempo em segundos desde a última atualização.
     */
    public abstract void update (float deltaTime);
}
