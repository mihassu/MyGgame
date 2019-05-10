package ru.geekbrain.myggame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.base.BaseScreen;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.sprite.Background;
import ru.geekbrain.myggame.sprite.ButtonExit;
import ru.geekbrain.myggame.sprite.ButtonPlay;
import ru.geekbrain.myggame.sprite.Logo;
import ru.geekbrain.myggame.sprite.Star;


public class MenuScreen extends BaseScreen{
    private Texture bg;
    private Texture log;
    private Background background;
    private Logo logo;
    private TextureAtlas atlas;
    private Star[] stars;
    private final int STARS_COUNT = 100;
    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;
    private Game game;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("Bricks23_col.jpg");
//        batch.getProjectionMatrix().idt(); //сделать матрицу единичной, т.е. переходим в координаты openGL
        background = new Background(new TextureRegion(bg));

        log = new Texture("kot3.jpg");
        logo = new Logo(new TextureRegion(log));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");

        stars = new Star[STARS_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }

        buttonExit = new ButtonExit(atlas);
        buttonPlay = new ButtonPlay(atlas, game);
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
//        logo.draw(batch);
        for (Star s: stars) {
            s.draw(batch);
        }
        buttonExit.draw(batch);
        buttonPlay.draw(batch);
        batch.end();
    }

    private void update(float delta) {
//        logo.update(delta);
        for (Star s: stars) {
            s.update(delta);
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
//        logo.resize(worldBounds);

        for (Star s: stars) {
            s.resize(worldBounds);
        }

        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        log.dispose();
        atlas.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        logo.touchDown(touch, pointer);
        buttonExit.touchDown(touch, pointer);
        buttonPlay.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch, pointer);
        buttonPlay.touchUp(touch, pointer);
        return false;
    }
}
