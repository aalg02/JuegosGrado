package managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {
    //Constantes
    public static final String NUMBERS_SPRITES = "numbers/digito";
    public static final String NUMBERS_EXT_SPRITES = ".png";
    public static final String JUGADOR = "andando";
    public static final String ENEMY_SPRITES = "enemy";
    public static final String ALLY_SHOT = "disparo";
    public static final String ENEMY_SHOT = "disparo";
    public static final String EXPLOSION_SPRITES = "explosion";
    public static final String BUTTON = "button";
    public static final String TITLE_LABEL = "SPACE-INVADER";
    public static final String ATLAS_FILE = "pang.atlas";

    public static final String BIG_FONT = "big-black";
    public static final String FONDO = "fondo.jpg";
    private static Skin textSkin;

    public static Skin getTextSkin() {
        if (textSkin == null) {
            textSkin = new Skin(Gdx.files.internal("glassy-ui.json"));
        }
        return textSkin;
    }
}
