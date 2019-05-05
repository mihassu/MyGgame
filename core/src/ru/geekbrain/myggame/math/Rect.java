package ru.geekbrain.myggame.math;

import com.badlogic.gdx.math.Vector2;

public class Rect {

    public final Vector2 pos = new Vector2();
    protected float halfWidth;
    protected float halfHeight;

    public Rect() {

    }

    public Rect(Rect from) {
        this(from.pos.x, from.pos.y, from.getHalfWidth(), from.getHalfHeight());
    }

    public Rect(float x, float y, float halfWidth, float halfHeight) {
        pos.set(x, y);
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
    }

    public float getHalfWidth() {
        return halfWidth;
    }

    public float getHalfHeight() {
        return halfHeight;
    }
}
