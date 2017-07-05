package com.tcp.trabalhopratico.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Classe que gerencia persistência de dados, em especial o ranking de pontuação do jogo.
 * A classe cria uma string padrão de pontuações para preencher a tela de rankings.
 */
public class Persistence {
    public static int NUMBER_OF_HIGHSCORES = 10;
    private static String SCORE_LIST_SEPARATOR = ";";
    private static String ENTRY_SEPARATOR = "-";
    private final static String scoresFile = "highscores.frogger";
    public static TreeMap<Integer, String> highscores = new TreeMap<Integer, String>(Collections.reverseOrder());

    /**
     * Carrega o ranking de pontuação do jogo do disco. Se o arquivo de pontuações não existir
     * carrega um conjunto de pontuações padrão.
     */
    public static void load () {
        try {
            if (Gdx.files.local(scoresFile).exists()) {
                FileHandle filehandle = Gdx.files.local(scoresFile);

                String[] scoreList = filehandle.readString().split(SCORE_LIST_SEPARATOR);

                for (int i = 0; i < NUMBER_OF_HIGHSCORES; i++) {
                    String[] entry = scoreList[i].split(ENTRY_SEPARATOR);
                    highscores.put(Integer.parseInt(entry[0]), entry[1]);
                }
            } else {
                String defaultScoreName = "frogger";
                highscores.put(100, defaultScoreName);
                highscores.put(95, defaultScoreName);
                highscores.put(90, defaultScoreName);
                highscores.put(85, defaultScoreName);
                highscores.put(80, defaultScoreName);
                highscores.put(75, defaultScoreName);
                highscores.put(70, defaultScoreName);
                highscores.put(65, defaultScoreName);
                highscores.put(60, defaultScoreName);
                highscores.put(55, defaultScoreName);
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
            FileHandle filehandle = Gdx.files.local(scoresFile);

            Set set = highscores.entrySet();
            Iterator iterator = set.iterator();

            String highscoresString = "";

            while (iterator.hasNext()) {
                Map.Entry mapEntry = (Map.Entry) iterator.next();
                String entryString = mapEntry.getKey().toString() + ENTRY_SEPARATOR
                        + mapEntry.getValue();
                highscoresString += entryString + SCORE_LIST_SEPARATOR;
            }

            filehandle.writeString(highscoresString, false);
        } catch (Throwable e) {
            // TODO Tratar erro ao salvar a pontuação
        }
    }

    /**
     * Função que determina se a pontuação passada está entre as maiores pontuações para ser adicionada.
     * @param score Pontuação alcançada pelo usuário.
     * @return True se for maior que o último item da lista de pontuação, false do contrário
     */
    public static boolean isHighscore(int score) {
        return score > highscores.lastEntry().getKey();
    }

    /**
     * Adiciona uma pontuação no ranking e rearranja o array em ordem decrescente.
     * @param score Pontuação a ser adicionada no ranking.
     */
    public static void addScore (int score, String name) {
        if (highscores.containsKey(score))
            return;
        highscores.pollLastEntry();
        highscores.put(score, name);
    }
}

