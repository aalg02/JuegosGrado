package com.politecnicomalaga.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Manzana {

    Cuadrado c1 ;


    protected Texture img;
    int posx;
    int posy ;


    public Manzana() {
        posx = (int) (Math.random() * 600);
        posy = (int) (Math.random() * 400);
        c1=new Cuadrado(posx,posy,20);

        img =new Texture("manzana.png");
        c1.img=img;

    }

    public void pintarse(SpriteBatch sb) {
        c1.pintarse(sb);

    }


    public float getC1X(){
        return c1.getPosX();

    }
    public float getC1Y(){
        return c1.getPosY();

    }





    public void dispose() {
        c1.dispose();
       }

}
