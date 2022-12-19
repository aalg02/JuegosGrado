package model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import managers.AssetManager;
import managers.BolasManager;
import managers.GameManager;
import managers.ScreensManager;
import managers.SettingManager;
import managers.ShootManager;

public class Personaje extends Actor {
    private final Animation<TextureRegion> skin;
    private final Stage baseStage;
    private final ShootManager Shooters;
    private float shootTime = 0;
    private final ShootManager shooters = ShootManager.getSingleton();
    private final BolasManager bolicas = BolasManager.getSingleton();
    private Rectangle cuerpojugador;

    public Personaje(Stage baseStage) {
        super();
        Shooters = ShootManager.getSingleton();
        this.baseStage = baseStage;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetManager.ATLAS_FILE));
        skin = new Animation<TextureRegion>(0.25f, atlas.findRegions(AssetManager.JUGADOR), Animation.PlayMode.LOOP);
        this.setBounds(0, 0, SettingManager.ALLY_WIDTH, SettingManager.ALLY_HEIGHT);
        this.setX(SettingManager.positionshipX - SettingManager.ALLY_CENTER);
        this.setY(SettingManager.positionshipY);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.cuerpojugador = new Rectangle(getX(), getY(), SettingManager.ALLY_WIDTH, SettingManager.ALLY_HEIGHT);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, getX(), getY(), SettingManager.ALLY_WIDTH, SettingManager.ALLY_HEIGHT);
        shootTime += 1;
        if (shootTime >= SettingManager.tiempoEntreDisparos) {
            shootTime -= SettingManager.tiempoEntreDisparos;
            baseStage.addActor(Shooters.shootCreate(getX(), getY(), true));
        }
    }


    public boolean choque(Array<Bola> bolas) {
        for (Bola b : bolas) {
            if (Intersector.overlaps(b.getCuerpobola(), getCuerpojugador())) {
                this.setVisible(false);

                return true;

            }
        }
        return false;


    }

    public Bola choqueBola(Array<Bola> bolicas) {
        for (Bullet b : shooters.getListBullet()) {
            for (Bola bola : bolicas) {
                if (Intersector.overlaps(bola.getCuerpobola(), b.getCuerpobala())) {
                    shooters.deadShoot(b, shooters.getListBullet().indexOf(b));
                    return bola;
                }
            }
        }
        return null;
    }


    public Rectangle getCuerpojugador() {
        return cuerpojugador;
    }
}
