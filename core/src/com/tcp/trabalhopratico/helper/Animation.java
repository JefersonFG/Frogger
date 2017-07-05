package com.tcp.trabalhopratico.helper;

import com.tcp.trabalhopratico.model.Frog;

/**
 * Classe responsável por executar animações na tela.
 */
public class Animation {
    /**
     * Método responsável por animar o movimento do sapo, atualizando o status do sapo
     * no fim da animação.
     * @param frog Sapo a ser animado.
     * @param deltaTime Tempo desde a última atualização.
     */
    public static void animateFrog(Frog frog, float deltaTime) {
        int MOVE_STEP_SIZE = 400;

        if (frog.getLastPosition().x < frog.getPosition().x) {
            frog.getLastPosition().x += MOVE_STEP_SIZE * deltaTime;
            if (frog.getLastPosition().x > frog.getPosition().x)
                frog.getLastPosition().x = frog.getPosition().x;
        } else if (frog.getLastPosition().y < frog.getPosition().y) {
            frog.getLastPosition().y += MOVE_STEP_SIZE * deltaTime;
            if (frog.getLastPosition().y > frog.getPosition().y)
                frog.getLastPosition().y = frog.getPosition().y;
        } else if (frog.getLastPosition().x > frog.getPosition().x) {
            frog.getLastPosition().x -= MOVE_STEP_SIZE * deltaTime;
            if (frog.getLastPosition().x < frog.getPosition().x)
                frog.getLastPosition().x = frog.getPosition().x;
        } else if (frog.getLastPosition().y > frog.getPosition().y) {
            frog.getLastPosition().y -= MOVE_STEP_SIZE * deltaTime;
            if (frog.getLastPosition().y < frog.getPosition().y)
                frog.getLastPosition().y = frog.getPosition().y;
        } else {
            frog.setState(Frog.FROG_STATE_NORMAL);
        }
    }
}
