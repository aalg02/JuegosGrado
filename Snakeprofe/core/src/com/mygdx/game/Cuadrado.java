package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cuadrado {

    public enum Direccion {ARRIBA,ABAJO,DERECHA,IZQUIERDA};

    private static final String IMAGEN_n = "cuadrado.png";
    private static final String IMAGEN_r = "cuadrado3.png";
    private static final String IMAGEN_b = "cuadrado2.png";
    private static final String IMAGEN_c = "cabeza.png";
    String[] colores=new String[]{IMAGEN_b,IMAGEN_r,IMAGEN_n};
    //Atributos
    protected Texture img;
    protected float posX,posY;
    protected int lado;
    protected int n_color;
    int posx = (int) (Math.random() * 800*0.2f);
    int posy = (int) (Math.random() * 600*0.2f);

    //Métodos
    public Cuadrado(float X, float Y, int l) {
        n_color=(int)(Math.random()*3);
        posX = X;
        posY = Y;
        lado = l;
        img = new Texture(colores[n_color]);
    }

    public Cuadrado(Cuadrado otro) {
        n_color= otro.n_color;
        posX = otro.getPosX();
        posY = otro.getPosY();
        lado = otro.getLado();
        img = new Texture(colores[n_color]);
    }
    public Cuadrado(int n,float X, float Y, int l) {
        System.out.printf(n_color+"");
        posX = X;
        posY = Y;
        lado = l;
        img= new Texture(IMAGEN_c);
    }
    public Cuadrado(int X, int Y, int l,int n) {
        posX = posx;
        posY = posy;
        lado = l;
        img= new Texture(IMAGEN_c);
    }

    //Pintarse
    public void pintarse(SpriteBatch sb) {
        sb.begin();
        sb.draw(img,posX,posY,lado,lado);
        sb.end();
    }


    //Moverse
    public void moverse(Direccion dir) {
        switch (dir) {
            case ABAJO: posY = posY - lado;
                break;
            case ARRIBA:posY = posY + lado;
                break;
            case DERECHA: posX = posX + lado;
                break;
            case IZQUIERDA: posX = posX - lado;
                break;
        }
    }

    //Colisiona
    public boolean colisiona(Cuadrado otro) {
        return (otro.getPosX()==posX && otro.getPosY()==posY);
    }
    public boolean puente(Cuadrado otro){
        boolean resultado,colisionX,colisionY;
        int HalfSquare=this.getLado()/2;
        colisionX = (Math.abs(posX - otro.getPosX()) <= (HalfSquare + HalfSquare));
        colisionY = (Math.abs(posY - otro.getPosY()) <= (HalfSquare + HalfSquare));
        resultado = colisionX && colisionY;

        return resultado;
    }

    public void color(){

    }


    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
    public void setPosX(int posicionX){this.posX=posicionX;}
    public void setPosY(int posicionY){
        this.posY=posicionY;
    }

    public int getLado() {
        return lado;
    }

    public void dispose() {
        if (img != null) img.dispose();
    }

}
