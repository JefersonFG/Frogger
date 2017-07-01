package com.tcp.trabalhopratico.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Superclasse da hierarquia do Model, todos os objetos do jogo devem herdar de GameObject.
 * A classe contém campos de posição no espaço 2D e de bordas do objeto.
 */
public abstract class GameObject {
    private final Vector2 position;
    private final Rectangle bounds;

    /**
     * Getter de position.
     * @return Posição do objeto.
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Getter de bounds.
     * @return Bordas do objeto.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Construtor que recebe as características do objeto.
     * @param x Posição do objeto no eixo x.
     * @param y Posição do objeto no eixo y.
     * @param width Largura do objeto.
     * @param height Altura do objeto.
     */
    public GameObject (float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
    }
}
