package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.base.Sprite;
import ru.geekbrain.myggame.math.Rect;

public class Logo extends Sprite {

    private Vector2 v;
    private Vector2 buf;
    private Vector2 touch;
    private final float LEN = 0.01f;

    public Logo(TextureRegion region) {
        super(region);
        setSize(0.5f, 0.5f);
        v = new Vector2();
        touch = new Vector2();
        buf = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
//        setHeightProportion(getHeight());
        setHeightProportion(getHeight());
        pos.set(pos.x, pos.y); // установить позицию по центру
    }

    @Override
    public void update(float delta) {
        buf.set(touch);
        if (buf.sub(pos).len() > LEN) {
            pos.add(v);
        } else {
            pos.set(touch);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int button) {
        this.touch.set(touch);
        v.set(touch.cpy().sub(pos));
        v.setLength(LEN);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int button) {
        return super.touchUp(touch, button);
    }
}
