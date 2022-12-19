package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Serpiente {

    //Atributos
    ArrayList<Cuadrado> miCuerpo;
    Cuadrado.Direccion miDireccion;
    Texture img;
    Cuadrado cabeza1= new Cuadrado(200,200,20,0);


    //Métodos
    public Serpiente() {

        miCuerpo = new ArrayList<Cuadrado>();
        miDireccion = Cuadrado.Direccion.ARRIBA;
        Cuadrado cabeza = new Cuadrado(200,200,20);

        miCuerpo.add(cabeza);

    }

    //moverse
    public void moverse() {
        this.crecer();
        //cabeza(getCabezaY(),getCabezaX());
        Cuadrado elemento = miCuerpo.remove(miCuerpo.size()-1);
        elemento.dispose();
    }


    //crecer
    public void crecer() {
        //cabeza(getCabezaY(),getCabezaX());
        Cuadrado nuevaCabeza, cabeza;
        cabeza = miCuerpo.get(0);
        nuevaCabeza = new Cuadrado(cabeza);
        nuevaCabeza.moverse(miDireccion);

        miCuerpo.add(0,nuevaCabeza);
    }

    public void pintarse(SpriteBatch sb) {

        for (Cuadrado c: miCuerpo) {
            cabeza1.pintarse(sb);
            cabeza(getCabezaY(),getCabezaX());
            c.pintarse(sb);
        }
    }

    public boolean colisiona() {
        Cuadrado cabeza = miCuerpo.get(0);
        for (int i=4;i<miCuerpo.size();i++) {
            if (cabeza.colisiona(miCuerpo.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean estaDentro(int limiteMinX, int limiteMaxX, int limiteMinY, int limiteMaxY) {
        Cuadrado cabeza = miCuerpo.get(0);
        return (cabeza.getPosX()>limiteMinX && cabeza.getPosX()<limiteMaxX-cabeza.getLado() &&
                cabeza.getPosY()>limiteMinY && cabeza.getPosY()<limiteMaxY-cabeza.getLado());
    }

    public void cabeza(float posy,float posx){
        cabeza1.posX=posx;
        cabeza1.posY=posy;
    }

    public boolean entraPuente1(Puente puente){
        Cuadrado cabeza = miCuerpo.get(0);
        if(cabeza.puente(puente.c1)){
            return true;
        }
        return false;
    }
    public boolean entraPuente2(Puente puente){
        Cuadrado cabeza = miCuerpo.get(0);
        if(cabeza.puente(puente.c2)){
            return true;
        }
        return false;
    }

    public Cuadrado getCabeza(){
        return miCuerpo.get(0);
    }



    public float getCabezaX() {
        return miCuerpo.get(0).getPosX();
    }

    public float getCabezaY() {
        return miCuerpo.get(0).getPosY();
    }

    public void cambiaDir(Cuadrado.Direccion nuevaDir) {
        miDireccion = nuevaDir;
    }

    public void dispose() {
        for (Cuadrado c: miCuerpo) {
            c.dispose();
        }
    }

}
