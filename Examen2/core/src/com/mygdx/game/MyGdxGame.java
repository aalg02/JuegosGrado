package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.event.KeyEvent;

public class MyGdxGame extends ApplicationAdapter {

	static public final int PANTALLA_ANCHO = 800;
	static public final int PANTALLA_ALTO = 600;
	SpriteBatch batch;
	Texture img;
	Texture imagen;
	Texture road,balica;
	float velmoto=0f;
	float velobstaculos1=-1.5f;
	float velfondo=-3f;
	int valor=3;
	int Nfruta= (int) (Math.random() * 4);
	int Posfruta= (int) (Math.random() * 4);
	Array<ObjetoVolador> frutillass;
	Array<Bullet> municion;

	String[] obstaculos = {"warcar1.png", "warcar2.png", "warcar3.png", "broken.png"};
	ObjetoVolador mono;
	ObjetoVolador frutilla;
	PanelNumeros pn;
	fondo roadd,roadd2;
	Bullet bala;
	float n=0;
	float s=0;
//	OrthographicCamera camera;
//	Viewport camerap;


	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("interceptor.png"));
		road=new Texture("road.png");
		balica=new Texture("bala.png");
		mono = new ObjetoVolador(64, PANTALLA_ALTO / 2 - 32, 0, 0, img);
		imagen = new Texture(obstaculos[0]);
		frutilla = new ObjetoVolador(Posfruta- 32, PANTALLA_ANCHO, -1f, 0, imagen);
		frutillass=new Array<ObjetoVolador>();
		municion=new Array<Bullet>();
		frutillass.add(frutilla);
		pn=new PanelNumeros(0,0,50);
        bala=new Bullet(mono.getPosX()+64, mono.getPosY(),3,0,balica);
		municion.add(bala);
		roadd=new fondo(0,0,-3,0,road);
		roadd2=new fondo(0,0,-3,0,road);
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, 800, 600);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);
		//batch.setProjectionMatrix(camera.combined);

		pantallaJuego();
		pn.pintarse(batch);
		pn.setData(valor);
	}


	public void pantallaJuego() {

		//fondo reciclado
		if(roadd.getPosX()+ 1000<=Gdx.graphics.getWidth()){
			roadd2.setPosX((int)roadd.getPosX() + 2000);

		}

		if(roadd2.getPosX()+ 1000<=Gdx.graphics.getWidth()) {
			roadd.setPosX((int)roadd2.getPosX() + 2000);

		}




     //movimiento gorila
		if (Gdx.input.justTouched()) {
			int pixelY = Gdx.input.getY();

			if (pixelY<Gdx.graphics.getHeight() / 2) {
				velmoto=2f;
				mono.moverse(0,velmoto);
			} else {
				velmoto=-2f;
				mono.moverse(0,velmoto);
			}
		}

		//colisiones
		limites();

		for(ObjetoVolador f:frutillass) {

			if (mono.colisiona(f)){
				frutillass.removeValue(f,true);
				valor--;
				pn.setData(valor);
			}
		}

		if (valor==0){
			pn.setData(0);
			velmoto=0;
			velfondo=0;
			velobstaculos1=0;

		}


		//fondo

		roadd.moverse(velfondo,0);
		roadd.pintarse(batch);
		roadd2.moverse(velfondo,0);
		roadd2.pintarse(batch);




		//Spawn frutas
		int spawn= (int) (Math.random() * 100);
		if(spawn>98f){
			Nfruta= (int) (Math.random() * 4);
			Posfruta=(int) (Math.random() * PANTALLA_ALTO);

			if(Nfruta==3){
				imagen = new Texture(obstaculos[Nfruta]);

				frutillass.add(new ObjetoVolador(PANTALLA_ANCHO, Posfruta- 32, velobstaculos1, 0, imagen));
			}else {

				imagen = new Texture(obstaculos[Nfruta]);
				frutillass.add(new ObjetoVolador(PANTALLA_ANCHO, Posfruta- 32, velobstaculos1, 0, imagen));
			}





		}
		//monico

            mono.moverse(0,velmoto);
			mono.pintarse(batch);


			for(ObjetoVolador f:frutillass){
				f.moverse(velobstaculos1,0);
				f.pintarse(batch);
			}


			//bala
            disparar();
			impactobala();
			for(Bullet b:municion) {
				b.pintarse(batch);
				b.moverse(3, 0);
			}

		}

		public void limites(){
			if (mono.getPosY()>Gdx.graphics.getHeight() -32||mono.getPosY()<32) {
				velmoto = 0f;
				mono.moverse(0, 0);
			}
		}

		public void disparar(){
            n++;
			if(n==60){
				s++;

				n=0;
				if(s==1) {
					municion.add( new Bullet(mono.getPosX()+64, mono.getPosY(),3,0,balica));
					s=0;
				}
			}

		}

		public void impactobala(){
			for(Bullet b:municion) {
				for(ObjetoVolador o:frutillass) {
					if (b.colisiona(o)) {
						frutillass.removeValue(o, true);
					}
				}
			}
		}



		public void dispose () {
			batch.dispose();
			img.dispose();
			imagen.dispose();
			pn.dispose();
			road.dispose();

		}
	}

