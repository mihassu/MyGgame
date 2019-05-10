package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrain.myggame.base.Sprite;
import ru.geekbrain.myggame.math.Rect;

public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos); // установить позицию по центру
    }
}
