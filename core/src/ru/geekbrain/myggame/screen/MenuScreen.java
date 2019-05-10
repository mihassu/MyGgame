package ru.geekbrain.myggame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.base.BaseScreen;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.sprite.Background;
import ru.geekbrain.myggame.sprite.Logo;


public class MenuScreen extends BaseScreen{
    private Texture bg;
    private Texture log;
    private Background background;
    private Logo logo;


    @Override
    public void show() {
        super.show();
        bg = new Texture("Bricks23_col.jpg");
//        batch.getProjectionMatrix().idt(); //сделать матрицу единичной, т.е. переходим в координаты openGL
        background = new Background(new TextureRegion(bg));

        log = new Texture("kot3.jpg");
        logo = new Logo(new TextureRegion(log));
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        logo.update(delta);
        batch.end();

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        log.dispose();
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
        return false;
    }
}
