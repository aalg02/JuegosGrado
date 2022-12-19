package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGameExamen extends ApplicationAdapter {
	OrthographicCamera camera;
	Viewport camerap;

	static public final int PANTALLA_ANCHO = 800;
	static public final int PANTALLA_ALTO = 600;
	SpriteBatch batch;
	Texture img;
	Texture imagen,fondo;
	float velmono=0.5f;
	float velfruta=-1.5f;
	int Nfruta= (int) (Math.random() * 4);
	int Posfruta= (int) (Math.random() * 4);
	Array<ObjetoVolador> frutillass;
	String[] frutas = {"banana.png", "naranja.png", "manzana.png", "ciruela.png"};
	ObjetoVolador mono;
	ObjetoVolador frutilla;
	ObjetoVolador jungla;
	PanelNumeros pn;


	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("gorilla.png"));
		mono = new ObjetoVolador(PANTALLA_ANCHO / 2 - 32, 32, 0, 0, img);
		imagen = new Texture(frutas[0]);
		fondo = new Texture("jungla.jpg");

		frutilla = new ObjetoVolador(Posfruta- 32, PANTALLA_ALTO, 0f, -1f, imagen);
		frutillass=new Array<ObjetoVolador>();
		frutillass.add(frutilla);
		pn=new PanelNumeros(0,0,50);

	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(fondo,0,0);
		batch.end();

		pantallaJuego();
		pn.pintarse(batch);
	}


	public void pantallaJuego() {

     //movimiento gorila
		if (Gdx.input.justTouched()) {
			int pixelX = Gdx.input.getX();

			if (pixelX<Gdx.graphics.getWidth() / 2) {
				velmono=-2f;
				mono.moverse(velmono,0);
			} else {
				velmono=2f;
				mono.moverse(velmono,0);
			}
		}



		//colisiones
		for(ObjetoVolador f:frutillass) {

			if (mono.colisiona(f)){
				frutillass.removeValue(f,true);
				pn.incrementa(1);
			}
		}


		//Spawn frutas
		int spawn= (int) (Math.random() * 100);
		if(spawn>97.5f){
			Nfruta= (int) (Math.random() * 4);
			Posfruta=(int) (Math.random() * PANTALLA_ANCHO);
			imagen = new Texture(frutas[Nfruta]);
			frutillass.add(new ObjetoVolador(Posfruta- 32, PANTALLA_ALTO, 0f, -1f, imagen));



		}

		limites();
		//monico pintar y mover

            mono.moverse(velmono,0);
			mono.pintarse(batch);

        //frutillas pintar y mover
			for(ObjetoVolador f:frutillass){
				f.moverse(0,velfruta);
				f.pintarse(batch);
			}


		}

	public void limites(){
		if (mono.getPosX()>Gdx.graphics.getWidth() -32||mono.getPosX()<32) {
			velmono = 0f;
			mono.moverse(0, 0);
		}
	}

		public void dispose () {
			batch.dispose();
			img.dispose();
			imagen.dispose();
			pn.dispose();
		}
	}

