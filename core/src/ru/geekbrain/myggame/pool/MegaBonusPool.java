package ru.geekbrain.myggame.pool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrain.myggame.base.SpritesPool;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.sprite.MegaBonus;

public class MegaBonusPool extends SpritesPool {

    private TextureRegion region;
    private Rect worldBounds;

    public MegaBonusPool(TextureRegion region, Rect worldBounds) {
        this.region = region;
        this.worldBounds = worldBounds;
    }

    @Override
    protected MegaBonus newObject() {
        return new MegaBonus(region, worldBounds);
    }

}
