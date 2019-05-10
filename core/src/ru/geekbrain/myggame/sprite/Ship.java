package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrain.myggame.base.Sprite;
import ru.geekbrain.myggame.math.Rect;

public class Ship extends Sprite {

    public Ship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        setHeightProportion(0.2f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        pos.y = worldBounds.getBottom() - getHeight();
        pos.x = worldBounds.getHalfWidth();
    }

    @Override
    public void update(float delta) {

    }
}
