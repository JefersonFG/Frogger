package com.tcp.trabalhopratico.model;

/**
 * Superclasse da hierarquia de automóveis. Automóveis se movem nas estradas com o passar do tempo
 * e atropelam o sapo em caso de colisão. Contém a direção do movimento, que deve ser setada em
 * cada subclasse e a implementação do método de atualização, que executa um passo a cada iteração.
 */
public abstract class Automobile extends DynamicGameObject {
    private static final float UPDATE_STEP_SIZE = 71.5f;
    static final int DIRECTION_RIGHT = 0;
    static final int DIRECTION_LEFT = 1;
    int movementDirection;

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
     * Método de atualização da posição do automóvel na tela.
     * @param deltaTime Tempo em segundos desde a última atualização.
     */
    public void update (float deltaTime) {
        if (movementDirection == DIRECTION_RIGHT)
            getPosition().x += UPDATE_STEP_SIZE * getVelocity().x * deltaTime;
        else
            getPosition().x -= UPDATE_STEP_SIZE * getVelocity().x * deltaTime;
    }
}
