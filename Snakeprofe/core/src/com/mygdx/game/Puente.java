package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Puente  {
    Cuadrado c1 ;
    Cuadrado c2;

    protected Texture img;
    int posx = (int) (Math.random() * 650);
    int posy = (int) (Math.random() * 350);

    int posx2 = (int) (Math.random() * 650);
    int posy2 = (int) (Math.random() * 350);


    public Puente() {
       c1=new Cuadrado(posx,posy,20);
       c2=new Cuadrado(posx2,posy2,20);
    img =new Texture("cabeza.png");

    }
    public void pintarse(SpriteBatch sb) {
        c1.pintarse(sb);
        c2.pintarse(sb);
    }


    public float getC1X(){
        return c1.getPosX();

    }
    public float getC1Y(){
        return c1.getPosY();

    }
    public float getC2X(){
        return c2.getPosX();

    }
    public float getC2Y(){
        return c2.getPosY();

    }
    //--------------------------------------------------------------//

    public void setC1X(int n){
        c1.setPosX(n);

    }
    public void setC1Y(int n){
        c1.setPosY(n);

    }
    public void setC2X(int n){
        c2.setPosX(n);

    }
    public void setC2Y(int n){
         c2.setPosY(n);

    }




    public void dispose() {
        c1.dispose();
    c2.dispose();}

    ;
}





