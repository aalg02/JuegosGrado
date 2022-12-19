package managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import view.GameScreen;
import view.GameoverScreen;
import view.SplashScreen;

public class ScreensManager extends ScreenAdapter {
    private static ScreensManager singleton;
    private Game game;

    public static ScreensManager getSingleton() {
        if (singleton == null) {
            singleton = new ScreensManager();
        }
        return singleton;
    }

    public enum SCREENS {
        GAME_SCREEN,START_SCREEN,GAMEOVERSCREEN
    }

    public Screen getScreen(Game game, SCREENS screenToGet) {
        Screen newScreen = null;
        switch (screenToGet) {
            case GAME_SCREEN:
                newScreen = new GameScreen(game);
                break;

            case START_SCREEN:
                newScreen = new SplashScreen(game);
                break;
            case GAMEOVERSCREEN:
                newScreen = new GameoverScreen(game);
                break;
        }
        return newScreen;
    }
}
