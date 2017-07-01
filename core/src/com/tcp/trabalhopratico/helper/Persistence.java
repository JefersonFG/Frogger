package com.tcp.trabalhopratico.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Classe que gerencia persistência de dados, em especial o ranking de pontuação do jogo.
 * A classe cria uma string padrão de pontuações para preencher a tela de rankings.
 */
public class Persistence {
    public static int[] highscores = new int[] {80, 70, 60, 50, 40};
    public final static String file = "Highscores.frogger";

    /**
     * Carrega o ranking de pontuação do jogo do disco.
     */
    public static void load () {
        try {
            FileHandle filehandle = Gdx.files.external(file);

            String[] strings = filehandle.readString().split("\n");

            for (int i = 0; i < 5; i++) {
                highscores[i] = Integer.parseInt(strings[i+1]);
            }
        } catch (Throwable e) {
            // TODO Tratar erro ao carregar a pontuação
        }
    }

    /**
     * Salva o ranking de pontuação do jogo no disco.
     */
    public static void save () {
        try {
            FileHandle filehandle = Gdx.files.external(file);

            for (int i = 0; i < 5; i++) {
                filehandle.writeString(Integer.toString(highscores[i])+"\n", true);
            }
        } catch (Throwable e) {
            // TODO Tratar erro ao salvar a pontuação
        }
    }

    /**
     * Adiciona uma pontuação no ranking e rearranja o array em ordem decrescente.
     * @param score Pontuação a ser adicionada no ranking.
     */
    public static void addScore (int score) {
        for (int i = 0; i < 5; i++) {
            if (highscores[i] < score) {
                for (int j = 4; j > i; j--)
                    highscores[j] = highscores[j - 1];
                highscores[i] = score;
                break;
            }
        }
    }
}

