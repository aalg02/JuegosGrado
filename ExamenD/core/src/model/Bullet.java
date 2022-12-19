package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;



import managers.AssetManager;
import managers.GameManager;
import managers.SettingManager;


public class Bullet extends Actor {

    private final Animation<TextureRegion> skin;
    private boolean ally;
    private Rectangle cuerpobala;

    public Bullet(float positionX, float positionY, boolean ally) {
        super();
        this.ally = ally;

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetManager.ATLAS_FILE));
        if(ally){
            skin = new Animation<TextureRegion>(0.25f, atlas.findRegions(AssetManager.ALLY_SHOT), Animation.PlayMode.LOOP);
        }else{
            skin = new Animation<TextureRegion>(0.25f, atlas.findRegions(AssetManager.ENEMY_SHOT), Animation.PlayMode.LOOP);
        }

        this.setBounds(0, 0, SettingManager.sizeBulletWIDTH, SettingManager.sizeBulletHEIGHT);
        this.setX(positionX);
        this.setY(positionY);

    }
    public boolean getAlly(){
        return ally;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.cuerpobala=new Rectangle(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        float x = getX() + SettingManager.ALLY_CENTER - SettingManager.allyBulletCenter;
        float y = getY() + SettingManager.ALLY_HEIGHT;
        if (getY() < SettingManager.SCREEN_HEIGHT) {
            if(this.getAlly()){
                this.setY(getY() + SettingManager.speedBullet);
                batch.draw(currentFrame, x, y, SettingManager.sizeBulletWIDTH, SettingManager.sizeBulletHEIGHT);
            }else{
                this.setY(getY() - SettingManager.speedBulletEnemy);
                batch.draw(currentFrame, x, y, SettingManager.sizeBulletWIDTH, SettingManager.sizeBulletHEIGHT);
            }

        }
    }

    public Rectangle getCuerpobala() {
        return cuerpobala;
    }
}