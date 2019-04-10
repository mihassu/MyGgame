package ru.geekbrain.myggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MyGgame extends ApplicationAdapter {
    private SpriteBatch batch;
	private Texture img;
    private Texture background;
    private TextureRegion region;

	//занимается инициализацией проекта, срабатывает при запуске проекта.
	@Override
	public void create () {
		batch = new SpriteBatch();

		/*Класс «Texture» получает изображение из файла, размещенного в папке «assets»,
		 и загружает его в GPU. Размеры изображения должны быть степенью двойки
		 (16х16, 64х128, 128x128 и т. д.). При отрисовке текстуры указываются
		 координаты ее нижнего левого угла*/
		img = new Texture("badlogic.jpg");
		region = new TextureRegion(img, 20, 20, 50, 50); //Вырезает часть текстуры
		background = new Texture("Bricks23_col.jpg");

		//Установить фильтр для сглаживания пикселей при увеличении
		region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		Vector2 v1 = new Vector2();
		Vector2 v2 = new Vector2();

		v1.set(4,9);
		v2.set(5,13);

		System.out.println("v1 + v2 = " + v1.cpy().add(v2));//cpy() - нужен чтобы не изменился v1
		System.out.println("v1.nor() = " + v1.nor());
		System.out.println("v2.nor() = " + v2.nor());
		float scalar = v1.dot(v2);
		System.out.println("Угол между v1 и v2: " + Math.acos(scalar));


		float l = v1.len();

		Vector2 v3 = v1.cpy().add(v2);
	}

	// выполняется 60 раз в секунду и отвечает за отрисовку игры
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);	//Очистка экрана и задание фонового цвета

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.setColor(0.454f, 0.32f, 0.68f, 0.8f); //установить фон и прозразность
		batch.draw(img, 256, 0); //рисование текстуры в точке с координатами x, y (левый нижний угол текстуры).
		batch.setColor(0.7f, 0.3f, 0.5f, 0.5f);
		batch.draw(region, 0,0, 400,400);

		batch.end();
	}

	//отвечает за освобождение ресурсов после того, как они перестали быть нужны.
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		img.dispose();
	}
}
