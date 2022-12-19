package com.politecnicomalaga.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.*;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GdxSnake extends ApplicationAdapter {
	protected static final String muerte = "Perdiste.png";
	protected static final String fondo = "fondo.png";
	protected static final String manzana = "manzana.png";
	protected static final String Inicio = "PantallaInicio.png";
	protected static final String stop = "Pausa.png";
	OrthographicCamera camera;
	Viewport camerap;


	Texture perdiste, Fondo, Manzana,inicio,pausa;
	enum Pantallas {INICIAL, JUGANDO, FINAL,PAUSA};

	SpriteBatch batch;
	static Serpiente s1;
	Manzana manzanica;


	int direccion, n,s;
	float Cposx, Cposy;

	Audio a1;
	Pantallas PantallasActivas;
	boolean musicaSonando;
	PanelPuntos panel;


	float uwu=800;
	float owo=600;
	float mecago;
	float memeo;

	@Override
	public void create(){
		mecago=uwu/Gdx.graphics.getWidth();
		memeo=owo/Gdx.graphics.getHeight();
		Manzana = new Texture(manzana);
		perdiste = new Texture(muerte);
		pausa = new Texture(stop);
		Fondo = new Texture(fondo);
		inicio = new Texture(Inicio);
		batch = new SpriteBatch();
		s1 = new Serpiente();
		manzanica=new Manzana();
		direccion = 0;
		a1=new Audio();
		PantallasActivas=Pantallas.INICIAL;
		musicaSonando=false;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, uwu, owo);
		s=0;
		panel=new PanelPuntos(500,490,100);




	}

	@Override
	public void render() {
		//limpiamo pantallax
		ScreenUtils.clear(0, 0, 0, 1);
		batch.setProjectionMatrix(camera.combined);
		switch (PantallasActivas) {
			case INICIAL: pantallaI();
				break;
			case JUGANDO: pantallaP();
				break;
			case FINAL: pantallaM();
				break;
			case PAUSA: pantallaPau();
				break;
		}

	}


	@Override
	public void dispose() {
		batch.dispose();
		s1.dispose(batch);
		perdiste.dispose();
		Fondo.dispose();
		panel.dispose();


	}


	//Metodos variados


	public void DireccionTactil() {
		float posx, posy;

		if (Gdx.input.justTouched()) {
			posx = Gdx.input.getX();
			posy = Gdx.graphics.getHeight() - Gdx.input.getY();//invertir coordenadas


			Cposx = s1.PosicionCX();
			Cposy = s1.PosicionCY();

			if (Math.abs(posy - Cposy) > Math.abs(posx - Cposx)) {

				if (Cposy - posy < 0.0f) {
					s1.Direccion(Cuadrado.ARRIBA);
				} else {
					s1.Direccion(Cuadrado.ABAJO);
				}

			} else {
				if (Cposx - posx < 0.0f) {
					s1.Direccion(Cuadrado.DERECHA);
				} else {
					s1.Direccion(Cuadrado.IZQUIERDA);
				}
			}
		}

	}




	public void repetir() {


		if (Gdx.input.justTouched()) {

                musicaSonando=false;
				s1.dispose(batch);
				s1=new Serpiente();
				s1.Serpiente.get(0).posX=200;
				s1.Serpiente.get(0).posY=200;
				s1.render(batch);

				PantallasActivas=Pantallas.JUGANDO;


		}
	}

	public void manzana() {

		if (s1.manzana(manzanica)) {
			s1.crecer();
			reloadmanzana();
			panel.incrementa(1);
		}
	}
	public void reloadmanzana(){
		manzanica.dispose();
		manzanica=new Manzana();
		manzanica.pintarse(batch);
	}


	public void pantallaP(){

		//tiempo



		if(musicaSonando==false){
			a1.Audio1();
			musicaSonando=true;
		}
	
		batch.begin();
		batch.draw(Fondo, 0, 0,800, 600);
		batch.end();


		n++;
		if (n % 10 == 0) {
			s1.mover();
			if(s1.colision()||!s1.estadentro(Gdx.graphics.getWidth()-(20*mecago),(20*mecago),(20*memeo),Gdx.graphics.getHeight()-(132*memeo))){
				a1.Parar();
				musicaSonando=false;
				PantallasActivas=Pantallas.FINAL;
			}
		}


		//METODOS
		DireccionTactil();


		s1.render(batch);
		manzanica.pintarse(batch);
		manzana();
		if (n == 60) {

			if(s1.colision()||!s1.estadentro(Gdx.graphics.getWidth()-20,20,20,Gdx.graphics.getHeight()-132)){
				a1.Parar();
				musicaSonando=false;

				PantallasActivas=Pantallas.FINAL;

			}
			n = 0;
		}
		panel.pintarse(batch);


	}
	public void pantallaM(){
		panel.setData(0);
		if(musicaSonando==false){
			a1.Audio3();
			musicaSonando=true;
		}

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(perdiste, Gdx.graphics.getWidth()/4f, Gdx.graphics.getHeight()/3.5f,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2f);
		batch.end();


		repetir();
	}
	public void pantallaI() {

		if (musicaSonando == false) {
			a1.Audio2();
			musicaSonando = true;
		}

		batch.begin();
		batch.draw(inicio, 0, 0, 800, 600);
		batch.end();

		if (Gdx.input.justTouched()) {
			musicaSonando = false;
			a1.Parar();
			PantallasActivas = Pantallas.JUGANDO;


		}
	}
		public void pantallaPau () {

			if (musicaSonando == false) {
				a1.Audio2();
				musicaSonando = true;
			}

			batch.begin();
			batch.draw(pausa, 0, 0, Gdx.graphics.getWidth()/3.5f, Gdx.graphics.getHeight()/3.5f);
			batch.end();

			if (Gdx.input.justTouched()) {
				musicaSonando = false;
				a1.Parar();
				PantallasActivas = Pantallas.JUGANDO;


			}
		}





}
