package ru.geekbrain.myggame.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import java.util.logging.Level;
import java.util.logging.Logger;

import ru.geekbrain.myggame.math.MatrixUtil;
import ru.geekbrain.myggame.math.Rect;

public class BaseScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;
    private Vector2 touch;

    protected Rect worldBounds; //игровой мир
    private Rect screenBounds; //в пикселях
    private Rect glBounds; //прямоуголиник в координатах openGL размером 2fx2f

    private Matrix4 worldToGL;
    private Matrix3 screenToWorld;

    private static final Logger logger = Logger.getLogger(Screen.class.getName());


    @Override
    public void show() {
        logger.log(Level.SEVERE,"show()");
        Gdx.input.setInputProcessor(this); //назначаем процессор ввода - нащ экран
        batch = new SpriteBatch();
        worldBounds = new Rect();
        screenBounds = new Rect();
        glBounds = new Rect(0, 0, 1f, 1f);
        worldToGL = new Matrix4();
        screenToWorld = new Matrix3();
        touch = new Vector2();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);	//Очистка экрана и задание фонового цвета
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        logger.log(Level.SEVERE,"resize(): width - " + width + ", height - " + height);
        screenBounds.setSize(width, height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);

        float aspect = (float) width / height;
        worldBounds.setHeight(2f);
        worldBounds.setWidth(2f * aspect);

        MatrixUtil.calcTransitionMatrix(worldToGL, worldBounds, glBounds); //считаем матрицу
        batch.setProjectionMatrix(worldToGL); //передаем ее в batch. Батчер сам выполняет умножение на эту матрицу

        MatrixUtil.calcTransitionMatrix(screenToWorld, screenBounds, worldBounds);
        resize(worldBounds);
    }

    public void resize(Rect worldBounds) {

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
        logger.log(Level.SEVERE," x: " + screenX+ "px, y: " + screenY + "px" +
                " pointer: " + pointer + " button: " + button);
        touch.set(screenX, screenBounds.getHeight() - screenY);
        touch.mul(screenToWorld);
        touchDown(touch, pointer);
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        logger.log(Level.SEVERE, "x: " + touch.x + " y: " + touch.y);
        return false;
    }

    @Override //кнопка мыши отпущена
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenBounds.getHeight() - screenY);
        touch.mul(screenToWorld);
        touchUp(touch, pointer);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        logger.log(Level.SEVERE, "x: " + touch.x + " y: " + touch.y);
        return false;
    }

    @Override //нажатие мыши и протаскивание
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        logger.log(Level.SEVERE,"touchDragged()");

        touch.set(screenX, screenBounds.getHeight() - screenY);
        touch.mul(screenToWorld);
        touchDragged(touch, pointer);
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
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
