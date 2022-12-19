package com.politecnicomalaga.snake;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Serpiente {


    //ESTADO ATRIBUTOS
    SpriteBatch Sb;
    protected ArrayList<Cuadrado> Serpiente;
    int idir;
    Cuadrado c1;


   //COMPORTAMIENTO  -CONSTRUCTOR - METODOS

    public Serpiente(){
        //CREO EL CUADRADO
        //inicializo  el arraylist
        //añado mi cuadrado a el arraylist
        idir=Cuadrado.DERECHA;
        Serpiente=new ArrayList<>();
        c1=new Cuadrado(200,200,20);
        Serpiente.add(c1);
        c1=new Cuadrado(180,200,20);
        Serpiente.add(c1);
        c1=new Cuadrado(160,200,20);
        Serpiente.add(c1);

    }



    public void crecer(){
            Cuadrado newc=new Cuadrado(Serpiente.get(0));
            newc.moverse(idir);
             Serpiente.add(0,newc);

    }

    public void mover(){
        this.crecer();
        Cuadrado basura=Serpiente.remove(Serpiente.size()-1);

        basura.dispose();

    }





    public void render(SpriteBatch Sb){

        for(Cuadrado cuadradito:Serpiente){
            cuadradito.pintarse(Sb);
        }


    }
    public void dispose(SpriteBatch Sb ){

        for(Cuadrado cuadradito:Serpiente){
            cuadradito.pintarse(Sb);
        }


    }
    public boolean manzana(Manzana manzanica){
        Cuadrado cabeza = Serpiente.get(0);
        if(cabeza.puente(manzanica.c1)){
            return true;
        }
        return false;
    }

    public void Direccion(int nuevaDir){
        boolean bNoasignar=false;
        bNoasignar=(idir==Cuadrado.ARRIBA&& nuevaDir==Cuadrado.ABAJO);
        bNoasignar= bNoasignar||(idir==Cuadrado.ABAJO&& nuevaDir==Cuadrado.ARRIBA);
        bNoasignar= bNoasignar||(idir==Cuadrado.IZQUIERDA && nuevaDir==Cuadrado.DERECHA);
        bNoasignar= bNoasignar||(idir==Cuadrado.DERECHA && nuevaDir==Cuadrado.IZQUIERDA);

        if(bNoasignar==false){
            idir=nuevaDir;
        }



    }


    public void morir(){
         dispose(Sb);
        Serpiente s1=new Serpiente();
        s1.Serpiente.get(0).posX=200;
        s1.Serpiente.get(0).posY=200;
        s1.render(Sb);
    }


    public boolean colision(){
        Cuadrado cabeza=Serpiente.get(0);
        for (int n = 4; n<Serpiente.size() ; n++) {
            if (Serpiente.get(n).colisiona(cabeza)) {
               return true;

            }
        }

        return false;
    }


    public boolean estadentro(float ancho, float anchoI, float altoI, float alto){
        return(this.PosicionCX()<=ancho-Serpiente.get(0).getLado()&&
                this.PosicionCY()<=alto-Serpiente.get(0).getLado()&&
                this.PosicionCX()>=anchoI && this.PosicionCY()>=altoI);
    }

    public float PosicionCX(){
        return Serpiente.get(0).getPosX();

    }

    public float PosicionCY(){
        return Serpiente.get(0).getPosY();

    }

    public Cuadrado getSerpiente(int n) {
        return Serpiente.get(n);
    }
}