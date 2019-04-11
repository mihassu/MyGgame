package ru.geekbrain.myggame.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;

    private static final Logger logger = Logger.getLogger(Screen.class.getName());


    @Override
    public void show() {
        logger.log(Level.SEVERE,"show()");
        Gdx.input.setInputProcessor(this); //назначаем процессор ввода - нащ экран
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);	//Очистка экрана и задание фонового цвета
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        logger.log(Level.SEVERE,"resize(): width - " + width + ", height - " + height);
    }

    @Override
    public void pause() {
        logger.log(Level.SEVERE,"pause()");
    }

    @Override
    public void resume() {
        logger.log(Level.SEVERE,"resume()");
    }

    @Override
    public void hide() {
        logger.log(Level.SEVERE,"hide()");
        dispose();
    }

    @Override
    public void dispose() {
        logger.log(Level.SEVERE,"dispose()");
        batch.dispose();
    }

    //Методы InputProcessor:


    @Override //кнопка нажата
    public boolean keyDown(int keycode) {
        logger.log(Level.SEVERE,"keyDown(): " + keycode);
        return false;
    }

    @Override //кнопка отпущена
    public boolean keyUp(int keycode) {
        logger.log(Level.SEVERE,"keyUp(): " + keycode);
        return false;
    }

    @Override //событие нажатия клавиши
    public boolean keyTyped(char character) {
        logger.log(Level.SEVERE,"keyTyped(): " + character);
        return false;
    }

    @Override // кнопка мыши нажата
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        logger.log(Level.SEVERE,"touchDown(): " + " x: " + screenX+ " y: " + screenY +
                " pointer: " + pointer + " button: " + button);
        return false;
    }

    @Override //кнопка мыши отпущена
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        logger.log(Level.SEVERE,"touchUp()");
        return false;
    }

    @Override //нажатие мыши и протаскивание
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        logger.log(Level.SEVERE,"touchDragged()");
        return false;
    }

    @Override //движение мыши
    public boolean mouseMoved(int screenX, int screenY) {

        return false;
    }

    @Override //колесико
    public boolean scrolled(int amount) {
        return false;
    }
}
