package com.politecnicomalaga.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Cuadrado {
    //constante
    protected static final String IMG_Cuadrado ="serpiente.png";
    protected static final int ARRIBA =0;
    protected static final int ABAJO =1;
    protected static final int DERECHA =2;
    protected static final int IZQUIERDA =3;



    //Estado
    protected Texture img ;
    protected float posX,posY,lado;
    protected final static float VEL=6.5f;
    protected  float velx,vely;


    //
    public Cuadrado(float newX,float newY,float newLado){
        posX=newX;
        posY=newY;
        img =new Texture(IMG_Cuadrado);
        lado=newLado;

    }


    public Cuadrado(Cuadrado otro){
        posX=otro.getPosX();
        posY=otro.getPosY();
        img =new Texture(IMG_Cuadrado);
        lado= otro.getLado();

    }

    //pintar


    public void pintarse(SpriteBatch sb){
        sb.begin();
        sb.draw(img,posX,posY,lado,lado);
        sb.end();
    }


    //movimiento
     public void moverse(int dir){
         switch (dir){
             case ARRIBA:
                 posY=posY+lado;

                 break;
             case ABAJO:
                 posY=posY-lado;

                 break;
             case DERECHA:
                 posX=posX+lado;

                 break;
             case IZQUIERDA:
                 posX=posX-lado;

                 break;
         }

    }



     //para cuando muere
    public void dispose(){
        if(img!=null){
            img.dispose();
        }
    }


    //no sabemos si chocan unos con otros

public boolean colisiona(Cuadrado otro){

        return(posX==otro.getPosX()&&posY==otro.getPosY());
}
    public boolean puente(Cuadrado otro){
        boolean resultado,colisionX,colisionY;
        int HalfSquare=(int)this.getLado()/2;
        colisionX = (Math.abs(posX - otro.getPosX()) <= (HalfSquare + HalfSquare));
        colisionY = (Math.abs(posY - otro.getPosY()) <= (HalfSquare + HalfSquare));
        resultado = colisionX && colisionY;

        return resultado;
    }
//getters-----------------------------------------------------------
    public Texture getImg() {
        return img;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getLado() {
        return lado;
    }
}
