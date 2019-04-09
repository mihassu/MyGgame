package ru.geekbrain.myggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGgame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion region;

	//занимается инициализацией проекта, срабатывает при запуске проекта.
	@Override
	public void create () {
		batch = new SpriteBatch();

		/*Класс «Texture» получает изображение из файла, размещенного в папке «assets»,
		 и загружает его в GPU. Размеры изображения должны быть степенью двойки
		 (16х16, 64х128, 128x128 и т. д.). При отрисовке текстуры указываются
		 координаты ее нижнего левого угла*/
		img = new Texture("Bricks23_col.jpg");
		region = new TextureRegion(img, 20, 20, 50, 50); //Вырезает часть текстуры

		//Установить фильтр для сглаживания пикселей при увеличении
		region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
	}

	// выполняется 60 раз в секунду и отвечает за отрисовку игры
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);	//Очистка экрана и задание фонового цвета

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();
		batch.setColor(0.454f, 0.32f, 0.68f, 0.8f); //установить фон и прозразность
		batch.draw(img, 256, 0); //рисование текстуры в точке с координатами x, y (левый нижний угол текстуры).
//		batch.draw(img, 0,256);
//		batch.draw(img, 0,512);
//
//		batch.draw(img, 256,0);
//		batch.draw(img, 256,256);
//		batch.draw(img, 256,512);
//
//		batch.draw(img, 512,0);
//		batch.draw(img, 512,256);
//		batch.draw(img, 512,512);
//
//		batch.draw(img, 768,0);
//		batch.draw(img, 768,256);
//		batch.draw(img, 768,512);
//
//		batch.draw(img, 1024,0);
//		batch.draw(img, 1024,256);
//		batch.draw(img, 1024,512);

		batch.setColor(0.7f, 0.3f, 0.5f, 0.5f);
		batch.draw(region, 0,0, 400,400);

		batch.end();
	}

	//отвечает за освобождение ресурсов после того, как они перестали быть нужны.
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
