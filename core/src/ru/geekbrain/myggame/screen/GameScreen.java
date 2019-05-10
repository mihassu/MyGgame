package ru.geekbrain.myggame.screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrain.myggame.base.BaseScreen;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.sprite.Ship;
import ru.geekbrain.myggame.sprite.Star;

public class GameScreen extends BaseScreen {

    private TextureAtlas atlas;
    private Ship ship;

    @Override
    public void show() {
        super.show();

        atlas = new TextureAtlas("textures/mainAtlas.tpack");

        ship = new Ship(atlas);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void draw() {
        batch.begin();
        ship.draw(batch);
        batch.end();
    }

    private void update(float delta) {

    }


    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        ship.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        atlas.dispose();
    }
}
