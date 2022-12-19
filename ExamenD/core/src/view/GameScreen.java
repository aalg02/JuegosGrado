package view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

import managers.BolasManager;
import managers.ScreensManager;
import managers.SettingManager;
import managers.ShootManager;
import model.Bola;
import model.Bullet;
import model.Fondo;
import model.Personaje;

public class GameScreen implements Screen {
    protected Texture img;
    private final Stage stage;
    private final Game game;
    Bola bola;
    Personaje personaje;
    SpriteBatch batch;
    int velx, vely;
    float radio;
    Array<Bola> bolicas;
    Music musiquita;
    Music explosion;


    public GameScreen(Game aGame) {


        game = aGame;
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Fondo fondo = new Fondo(0, 0, "fondo.jpg");
        stage.addActor(fondo);
        //Esta orden se puede poner también en el show()
        Gdx.input.setInputProcessor(stage);
        bola = new Bola(stage, 500, 500);

        bolicas = new Array<Bola>();
        velx = 1;
        vely = 1;


        //We add the battalion, "the empire"
        personaje = new Personaje(stage);
        bolicas.add(bola);

        //We add the main player
        stage.addActor(personaje);
        stage.addActor(bolicas.get(0));

        radio = bolicas.get(0).getRadio();

        personaje.addListener(new InputListener() {
            public void clicked(InputEvent event, float x, float y, int pointer, int button) {
                //return true;
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                //if (heroShip.getX()<event.getStageX() && heroShip.getX()+ SettingsManager.ALLY_WIDTH>event.getStageX() && heroShip.getY()<event.getStageY() && heroShip.getY()+SettingsManager.ALLY_HEIGHT>event.getStageY()){}
                if (event.getStageX() > 0 && 1024 - SettingManager.ALLY_WIDTH > event.getStageX()) {
                    personaje.setX(event.getStageX());
                }
            }
        });
        personaje.setTouchable(Touchable.enabled);

        musiquita = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        musiquita.setLooping(true);
        musiquita.play();
    }


    @Override
    public void show() {


        Gdx.app.log("MainScreen", "show");

    }

    public void render(float delta) {
        //jave 8

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();

        //-------------------------------------------------------------------------//
        if(personaje.choque(bolicas)){
            musiquita.dispose();
            game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.GAMEOVERSCREEN));
        }

        //-------------------------------------------------------------------------//
        Bola colisiona = personaje.choqueBola(bolicas);
        if (colisiona != null) {
            explosion = Gdx.audio.newMusic(Gdx.files.internal("sounds/explotar.wav"));
            explosion.play();
            if(colisiona.getRadio()<=20){
                colisiona.remove();
            }else {

                dividir(colisiona);
            }
        }


        stage.draw();


    }


    //-------------------------------------------------------------------//


    public void dividir(Bola colisiona) {
        Bola aux = new Bola(stage, colisiona.getX(), colisiona.getY());

        colisiona.setVelX(-colisiona.getVelX());
        aux.setVelX(-colisiona.getVelX());
        colisiona.setRadio(colisiona.getRadio() / 2);
        aux.setRadio(colisiona.getRadio());
        //-------------------------//

        stage.addActor(aux);
        bolicas.add(aux);

    }


    //-----------------------------------------------------------------------//


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
        explosion.dispose();
    }

}
