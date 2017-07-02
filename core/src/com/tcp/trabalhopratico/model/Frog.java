package com.tcp.trabalhopratico.model;

/**
 * Classe que representa o sapo. Contém constantes para suas dimensões e seus possíveis estados,
 * seu número de vidas e estado atual e métodos para realizar e desfazer um movimento e reagir
 * a colisões com automóveis e obstáculos.
 */
public class Frog extends GameObject {
    private static final float FROG_WIDTH = 64;
    private static final float FROG_HEIGHT = 48;
    private static final int FROG_MAX_LIVES = 3;
    public static final int FROG_STATE_NORMAL = 0;
    public static final int FROG_STATE_HIT = 1;
    private int state;
    private int lives;

    /**
     * Getter de state.
     * @return Estado atual do sapo.
     */
    public int getState() {
        return state;
    }

    /**
     * Setter de state.
     * @param state Novo estado do sapo.
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Getter de lives.
     * @return Número de vidas restantes do sapo.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Setter de lives.
     * @param lives Novo número de vidas restantes do sapo.
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Construtor que recebe a posição do sapo e seta número de vidas e estado inicial.
     * @param x Posição do sapo no eixo x.
     * @param y Posição do sapo no eixo y.
     */
    public Frog (float x, float y) {
        super(x, y, FROG_WIDTH, FROG_HEIGHT);
        setLives(FROG_MAX_LIVES);
        setState(FROG_STATE_NORMAL);
    }

    /**
     * Método de movimento do sapo.
     * @param direction Direção para onde mover.
     */
    public void move (int direction) {
        // TODO Implementar movimento do sapo
    }

    /**
     * Método que desfaz o movimento do sapo.
     * @param previousMoveDirection Direção que o sapo tomou no movimento a ser desfeito.
     */
    public void undoMove (int previousMoveDirection) {
        // TODO Implementar desfazer movimento do sapo
    }

    /**
     * Método que atualiza o estado do sapo em caso de colisão com um automóvel.
     */
    public void hitAutomobile() {
        // TODO Implementar colisão com automóvel
    }

    /**
     * Método que atualiza a posição do sapo em caso de colisão com um obstáculo.
     */
    public void hitObstacle() {
        // TODO Implementar colisão com obstáculo
    }
}
