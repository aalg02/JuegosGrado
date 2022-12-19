package view;

import static managers.AssetManager.getTextSkin;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import managers.AssetManager;
import managers.ScreensManager;
import model.Fondo;

public class SplashScreen implements Screen {

    private final Stage stage;
    private final Game game;
    Music musiquita;

    public SplashScreen(final Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        SpriteBatch sb=new SpriteBatch();
        Fondo fondo=new Fondo(0,0,"pantalla_inicial.png");
        stage.addActor(fondo);
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetManager.ATLAS_FILE));

        //-------------------------------------------------------------------------------------------------------------------------//



//-------------------------------------------------------------------------------------------------------------------------//


//-------------------------------------------------------------------------------------------------------------------------//

        TextButton StartButton = new TextButton("Start", AssetManager.getTextSkin());
        StartButton.setWidth(Gdx.graphics.getWidth() /1.4f);
        StartButton.setPosition(Gdx.graphics.getWidth() / 2 - StartButton.getWidth() / 2, Gdx.graphics.getHeight() - StartButton.getHeight() * 7.2f);
        StartButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                musiquita.dispose();
                game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.GAME_SCREEN));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(StartButton);
        musiquita = Gdx.audio.newMusic(Gdx.files.internal("sounds/intro_pang.wav"));
        musiquita.setLooping(true);
        musiquita.play();

    }

    @Override
    public void show() {
        Gdx.app.log("StartScreen", "show");
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        musiquita.dispose();
    }

}
