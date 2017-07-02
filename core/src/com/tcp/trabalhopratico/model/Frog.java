package com.tcp.trabalhopratico.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Classe que representa o sapo. Contém constantes para suas dimensões e seus possíveis estados,
 * seu número de vidas e estado atual e métodos para realizar e desfazer um movimento e reagir
 * a colisões com automóveis e obstáculos.
 */
public class Frog extends GameObject {
    // TODO Refatorar constantes no Model (e possívelmente para todo o programa)
    private static final float FROG_WIDTH = 64;
    private static final float FROG_HEIGHT = 48;
    private static final int FROG_MAX_LIVES = 3;
    public static final int FROG_STATE_NORMAL = 0;
    public static final int FROG_STATE_HIT = 1;
    private final Vector2 lastPosition;
    private int state;
    private int lives;

    /**
     * Getter de lastPosition.
     * @return Posição anterior do sapo ou posição atual caso o movimento não tenha tido problemas.
     */
    public Vector2 getLastPosition() {
        return lastPosition;
    }

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
        lastPosition = new Vector2(x, y);
    }

    /**
     * Método que movimenta o sapo para cima.
     */
    public void moveUp() {
        // TODO Implementar movimento para cima do sapo
    }

    /**
     * Método que movimenta o sapo para baixo.
     */
    public void moveDown() {
        // TODO Implementar movimento para baixo do sapo
    }

    /**
     * Método que movimenta o sapo para a direita.
     */
    public void moveRight() {
        // TODO Implementar movimento para a direita do sapo
    }

    /**
     * Método que movimenta o sapo para a esquerda.
     */
    public void moveLeft() {
        // TODO Implementar movimento para a esquerda do sapo
    }

    /**
     * Método que confirma o movimento do sapo caso não tenha ocorrido uma colisão com um obstáculo,
     * atualizando a última posição do sapo como sendo a atual.
     */
    public void confirmMove() {
        //
    }

    /**
     * Método que desfaz o movimento do sapo em caso de colisão com um obstáculo.
     */
    public void undoMove() {
        // TODO Implementar desfazer movimento do sapo
    }

    /**
     * Método que atualiza o estado do sapo em caso de colisão com um automóvel.
     */
    public void hitAutomobile() {
        // TODO Implementar colisão com automóvel
    }
}
