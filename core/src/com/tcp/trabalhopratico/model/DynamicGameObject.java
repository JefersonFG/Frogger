package com.tcp.trabalhopratico.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Superclasse da hierarquia para objetos dinâmicos, que se movem sozinhos.
 * A única nova propriedade é a velocidade do objeto.
 */
public class DynamicGameObject extends GameObject {
    private final Vector2 velocity;

    /**
     * Getter de velocity
     * @return Velocidade do objeto
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * Construtor que recebe as características do objeto.
     * @param x Posição do objeto no eixo x.
     * @param y Posição do objeto no eixo y.
     * @param width Largura do objeto.
     * @param height Altura do objeto.
     */
    public DynamicGameObject (float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
    }
}
