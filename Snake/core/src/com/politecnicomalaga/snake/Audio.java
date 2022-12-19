package com.politecnicomalaga.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Audio {
    Music sound;





    public void Audio1(){
        sound = Gdx.audio.newMusic(Gdx.files.internal("musicafondo.ogg"));
        sound.play();

    }
    public void Audio2(){
        sound=Gdx.audio.newMusic(Gdx.files.internal("musicainicio.ogg"));
        sound.play();


    }
    public void Audio3() {
        sound = Gdx.audio.newMusic(Gdx.files.internal("explosion.ogg"));
        sound.play();
    }

    public void Parar(){

         sound.pause();

    }

}
