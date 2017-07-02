package com.tcp.trabalhopratico.view;

import com.badlogic.gdx.input.GestureDetector;

/**
 * Classe que implementa um detector de gestos para o aplicativo, permitindo que o usuário
 * realize swipes para mover o sapo. A classe foi obtida no seguinte endereço: https://goo.gl/oCLb5F
 */
class SimpleDirectionGestureDetector extends GestureDetector {
    /**
     * Interface que deve ser implementada para realizar os movimentos nas quatro direções.
     */
    interface DirectionListener {
        void onLeft();

        void onRight();

        void onUp();

        void onDown();
    }

    /**
     * Construtor que recebe uma implementação da interface DirectionListener.
     * @param directionListener Implementação da interface DirectionListener.
     */
    SimpleDirectionGestureDetector(DirectionListener directionListener) {
        super(new DirectionGestureListener(directionListener));
    }

    /**
     * Classe interna para determinação dos movimentos nas quatro direções.
     */
    private static class DirectionGestureListener extends GestureAdapter{
        DirectionListener directionListener;

        /**
         * Construtor que recebe uma implementação da interface DirectionListener.
         * @param directionListener Implementação da interface DirectionListener.
         */
        DirectionGestureListener(DirectionListener directionListener){
            this.directionListener = directionListener;
        }

        /**
         * Método chamado pelo GestureAdapter que verifica para qual direção foi dado o swipe e
         * invoca o método adequado da implementação da interface GestureListener.
         * @param velocityX Velocidade no eixo x do swipe.
         * @param velocityY Velocidade no eixo y do swipe.
         * @param button Requerido pelo framework (não documentado).
         * @return Requerido pelo framework (não documentado).
         */
        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            if(Math.abs(velocityX) > Math.abs(velocityY)){
                if(velocityX > 0){
                    directionListener.onRight();
                }else{
                    directionListener.onLeft();
                }
            }else{
                if(velocityY > 0){
                    directionListener.onDown();
                }else{
                    directionListener.onUp();
                }
            }
            return super.fling(velocityX, velocityY, button);
        }
    }
}