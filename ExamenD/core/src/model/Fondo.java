package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import managers.SettingManager;

public class Fondo extends Actor {

    private Texture skin;
    public Fondo(float positionX, float positionY ,String fondo) {
        super();
        skin=new Texture(fondo);
        this.skin = skin;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("pang.atlas"));

        this.setBounds(0, 0, SettingManager.SCREEN_WIDTH,SettingManager.SCREEN_HEIGHT);
        this.setX(positionX);
        this.setY(positionY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        float x = 0;
        float y =0;
        batch.draw(skin, x, y,  SettingManager.SCREEN_WIDTH,SettingManager.SCREEN_HEIGHT);

    }
}
