package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import managers.SettingManager;
import managers.ShootManager;

public class Bola extends Actor {
    protected float posX;
    protected float posY;
    protected float velX;
    protected float velY;
    protected float acelX;
    protected float acelY;
    protected float radio=72;
    protected float diametro;
    private final ShootManager shooters = ShootManager.getSingleton();
    protected Texture img;
    private Circle cuerpobola;
    Stage stage;

    public Bola(Stage stage ,float x,float y) {

        super();
        img = new Texture("circuloAzul.png");
        this.diametro=radio*2;
        this.setBounds(0, 0,diametro, diametro);
        this.setX(x);
        this.setY(y);
        this.velX=3;
        this.velY=0;
        this.acelY=0.22f;
        this.stage = stage;




        //Hay que crear las bolas (sólo es necesario instanciar una) donde nos digan
        //pero la acelY es siempre 0.2 y la velY es 0 al principio.
    }

    public Bola(Stage stage, float radio){
        super();
        img = new Texture("circuloAzul.png");
        this.radio=radio;
        this.diametro=radio*2;
        this.stage = stage;
        this.setBounds(0, 0,diametro, diametro);

    }
    //Comportamiento - Métodos


    @Override
    public void act(float delta) {
        super.act(delta);

        this.cuerpobola=new Circle(getX()+radio,getY()+radio,radio);


        //---------------------------------------------------------------------//

        if(getX()<=0){
            setVelX(Math.abs(velX));
        }else if(getX()>= SettingManager.SCREEN_WIDTH-(diametro)){
            setVelX(-velX);
        }

        //----------------------------------------------------------------------//
        if(getY()<=20){
            if(radio<=45){
                setVelY(radio * 0.4f);
            }else{
            setVelY(radio * acelY);}
        }else{
            setVelY(velY-acelY);
        }





        //Vamos a necesitar como otributos una velX que es constante, pero + o - y cambia
        //al tocar las paredes.
        //También una velY que empieza siendo 0. Pero que cuando actua el actor, cambia a
        //velY = velY - acelY
        //Hay que cambiar la velocidad si la bola llega al suelo (20 pixeles en Y). La velocidad
        //debe ser velY=radio * 0.10 - 6
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(img,getX(),getY(),radio*2,radio*2);
        moverse(velX,velY);



      //ibujamos la texture de la bola con respecto a su radio


    }

    public void moverse(float velX ,float velY) {
        setX(getX() + velX);
        setY(getY() + velY);
    }









    @Override
    public boolean remove() {
        return super.remove();
    }

    public Circle getCuerpobola() {
        return cuerpobola;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }

    public float getRadio() {
        return radio;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}
