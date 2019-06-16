package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.base.Sprite;
import ru.geekbrain.myggame.math.Rect;

public class MegaBonus extends Sprite {

    private Vector2 v;
    private Rect worldBounds;


    public MegaBonus(TextureRegion region, Rect worldBounds) {
        super(region);
        setHeightProportion(0.2f);
        this.v = new Vector2(0f, -0.6f);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if(isOutside(worldBounds)) {
            destroy();
        }
    }

}
