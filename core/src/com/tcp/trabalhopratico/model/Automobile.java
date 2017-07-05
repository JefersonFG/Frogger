package com.tcp.trabalhopratico.model;

/**
 * Superclasse da hierarquia de automóveis. Automóveis se movem nas estradas com o passar do tempo
 * e atropelam o sapo em caso de colisão. Contém a direção do movimento, que deve ser setada em
 * cada subclasse e a implementação do método de atualização, que executa um passo a cada iteração.
 * Contém também um indicativo da faixa da rodovia pela qual o automóvel se move e se ele é capaz
 * de trocar de faixa, contendo também um método para que o automóvel troque de faixa.
 */
public abstract class Automobile extends DynamicGameObject {
    private static final float UPDATE_STEP_SIZE = 71.5f;
    public static final int DIRECTION_RIGHT = 0;
    static final int DIRECTION_LEFT = 1;
    int movementDirection;
    private int currentLane;
    private boolean canChangeLane = false;

    /**
     * Getter de movementDirection.
     * @return Direção para a qual o automõvel está se movendo.
     */
    public int getMovementDirection() {
        return movementDirection;
    }

    /**
     * Getter de currentLane.
     * @return Faixa da rodovia pela qual o automóvel está se movendo.
     */
    public int getCurrentLane() {
        return currentLane;
    }

    /**
     * Getter de canChangeLane.
     * @return Se o automóvel é capaz de trocar de faixa.
     */
    public boolean getCanChangeLane() {
        return canChangeLane;
    }

    /**
     * Setter de canChangeLane.
     * @param canChangeLane Se o automóvel deve ser capaz de trocar de faixa.
     */
    public void setCanChangeLane(boolean canChangeLane) {
        this.canChangeLane = canChangeLane;
    }

    /**
     * Implementação básica do construtor que chama o da superclasse.
     * @param x Posição do objeto no eixo x.
     * @param y Posição do objeto no eixo y.
     * @param width Largura do objeto.
     * @param height Altura do objeto.
     * @param lane Faixa da rodovia pela qual o automóvel irá se mover.
     */
    Automobile (float x, float y, float width, float height, int lane) {
        super(x, y, width, height);
        currentLane = lane;
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

    /**
     * Método que faz o automóvel trocar de faixa na rodovia.
     * @param newLane Nova faixa para qual o automóvel deve mudar.
     */
    public void changeLane (int newLane) {
        getPosition().y += (newLane - currentLane) * getBounds().height;
        currentLane = newLane;
    }
}
