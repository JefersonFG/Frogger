package com.tcp.trabalhopratico.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Classe responsável por carregar as imagens do jogo. É inicializada no início do jogo
 * e mantém os dados disponíveis estaticamente.
 */
public class Assets {
    public static Texture background;

    private static Texture items;
    public static TextureRegion mainMenu;
    public static TextureRegion pauseMenu;
    public static TextureRegion ready;
    public static TextureRegion gameOver;
    public static TextureRegion highScoresRegion;
    public static TextureRegion logo;
    public static TextureRegion arrow;
    public static TextureRegion pause;

    public static Texture frogNormal;
    public static Texture frogHit;
    public static Texture car;
    public static Texture truck;
    public static Texture motorcycle;
    public static Texture tree;
    public static Texture road;
    public static Texture grass;
    public static Texture lake;
    public static Texture headerBackground;

    public static BitmapFont font;

    /**
     * Método interno para carregamento de arquivo.
     * @param file Nome do arquivo. Os arquivos devem estar todos na pasta android/assets.
     * @return Retorna uma textura obtida à partir do arquivo.
     */
    private static Texture loadTexture (String file) {
        return new Texture(Gdx.files.internal(file));
    }

    /**
     * Método para carregamento de todos os arquivos em texturas para uso no software. É chamado
     * no início da execução do software.
     */
    public static void load () {
        background = loadTexture("background.png");

        items = loadTexture("items.png");
        mainMenu = new TextureRegion(items, 0, 224, 300, 110);
        pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
        ready = new TextureRegion(items, 320, 224, 192, 32);
        gameOver = new TextureRegion(items, 352, 256, 160, 96);
        highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
        logo = new TextureRegion(items, 0, 352, 274, 142);
        arrow = new TextureRegion(items, 0, 64, 64, 64);
        pause = new TextureRegion(items, 64, 64, 64, 64);

        frogNormal = loadTexture("frog.png");
        frogHit = loadTexture("frog hit.png");
        car = loadTexture("car.png");
        truck = loadTexture("truck.png");
        motorcycle = loadTexture("motorcycle.png");
        tree = loadTexture("tree.png");
        road = loadTexture("road.png");
        grass = loadTexture("grass.png");
        lake = loadTexture("lake.png");
        headerBackground = loadTexture("header background.png");

        font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
    }
}
