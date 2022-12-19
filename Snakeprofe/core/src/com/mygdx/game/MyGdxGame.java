package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture imgInicio, imgJugando,imgFinal;
	enum Pantallas {INICIAL, JUGANDO, FINAL};
	protected PanelNumeros letrero;
	Pantallas miPantallaActiva;
	Serpiente vibora;
	Puente puente;
	boolean powerOn;

	int iPasos;
	int n,s;
	int pwTime;


	@Override
	public void create () {
		batch = new SpriteBatch();
		imgInicio = new Texture("pantallaInicio.png");
		imgJugando = new Texture("pantallaJugando.png");
		imgFinal = new Texture("pantallaFinal.png");
		miPantallaActiva = Pantallas.INICIAL;
		vibora = new Serpiente();

		powerOn=true;
		int n=0;
		int s=0;
		int pwTime=30;
		iPasos = 1;

	}

	@Override
	public void render () {

		ScreenUtils.clear(0, 0, 0, 1);
		switch (miPantallaActiva) {
			case INICIAL: pantallaInicial();
				break;
			case JUGANDO: pantallaJugando();
				break;
			case FINAL: pantallaFinal();
				break;
		}


	}

	//Métodos de trabajo de cada una de las pantallas
	private void pantallaInicial() {
		batch.begin();
		batch.draw(imgInicio,0,0);
		batch.end();

		if (Gdx.input.justTouched()) {
			miPantallaActiva = Pantallas.JUGANDO;
			vibora.dispose();
			vibora = new Serpiente();
			puente=new Puente();

		}
	}

	private void pantallaJugando() {



		n++;

		puente.pintarse(batch);

		if(n==60){
			s++;
			pwTime++;
			n=0;
			if(s==10) {
                power();
                powerOn=true;
				s=0;
			}
		}


		int pixelX, pixelY; //Los pixeles "clicados" en la pantalla
		float cabX, cabY;
		//Jugando

		//Comprobamos entrada
		if (Gdx.input.justTouched()) {
			//han tocado la pantalla
			pixelX = Gdx.input.getX();
			pixelY = Gdx.graphics.getHeight() - Gdx.input.getY();

			//Calculamos con respecto a la cabeza a donde nos dirigimos
			cabX = vibora.getCabezaX();
			cabY = vibora.getCabezaY();

			if (Math.abs(pixelX-cabX)>Math.abs(pixelY-cabY)) {
				//horizontal
				if (pixelX > cabX) {
					vibora.cambiaDir(Cuadrado.Direccion.DERECHA);
				} else {
					vibora.cambiaDir(Cuadrado.Direccion.IZQUIERDA);
				}
			} else {
				//vertical
				if (pixelY > cabY) {
					vibora.cambiaDir(Cuadrado.Direccion.ARRIBA);
				} else {
					vibora.cambiaDir(Cuadrado.Direccion.ABAJO);
				}
			}

		}
		iPasos++;
		//Simulamos el mundo
		if (iPasos % 10 == 0) {
			vibora.moverse();
       if(powerOn==true) {
		   if (vibora.entraPuente1(puente) == true) {
			   powerOn = false;
			   puente.dispose();

			   vibora.getCabeza().setPosX((int) puente.getC2X());
			   vibora.getCabeza().setPosY((int) puente.getC2Y());


			   System.out.println("puente1");


		   } else if (vibora.entraPuente2(puente) == true) {
			   powerOn = false;
			   puente.dispose();
			   vibora.getCabeza().setPosX((int) puente.getC1X());
			   vibora.getCabeza().setPosY((int) puente.getC1Y());
			   puente.dispose();


			   System.out.println("puente2");
		   }
	   }


			if ( vibora.colisiona() || !vibora.estaDentro(0,Gdx.graphics.getWidth(),0,Gdx.graphics.getHeight())) {
					miPantallaActiva = Pantallas.FINAL;
				}

		}
		if (iPasos == 29) {
			iPasos = 0;
			vibora.crecer();

			//Nos hemos estrellado???
			if (vibora.colisiona() || !vibora.estaDentro(0,Gdx.graphics.getWidth(),0,Gdx.graphics.getHeight())) {
				miPantallaActiva = Pantallas.FINAL;
			}
		}

//----------------------------------------------------------//


		//----------------------------------------------------------//



		//Pintamos
		vibora.pintarse(batch);


	}

	private void pantallaFinal() {
		batch.begin();
		batch.draw(imgFinal,0,0);
		batch.end();

		if (Gdx.input.justTouched()) {
			miPantallaActiva = Pantallas.INICIAL;
		}

	}
	public void power(){
		puente.dispose();
		puente=new Puente();
		puente.pintarse(batch);
	}





	@Override
	public void dispose () {
		batch.dispose();
		imgInicio.dispose();
		imgJugando.dispose();
		imgFinal.dispose();
		vibora.dispose();
	}
}
