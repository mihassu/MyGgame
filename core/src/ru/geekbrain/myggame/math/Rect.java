package ru.geekbrain.myggame.math;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Rect {

    public final Vector2 pos = new Vector2(); //позиция по центру
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

    public float getLeft() {
        return pos.x - halfWidth;
    }

    public float getTop() {
        return pos.y + halfHeight;
    }

    public float getRight() {
        return pos.x + halfWidth;
    }

    public float getBottom() {
        return pos.y - halfHeight;
    }

    public float getWigth() {
        return halfWidth * 2f;
    }

    public float getHeight() {
        return halfHeight * 2f;
    }



    public void setLeft(float left) {
        pos.x = left + halfWidth;
    }

    public void setTop(float top) {
        pos.y = top - halfHeight;
    }

    public void setRight(float right) {
        pos.x = right - halfWidth;
    }

    public void setBottom(float bottom) {
        pos.y = bottom + halfHeight;
    }

    public void setWidth(float width) {
        this.halfWidth = width / 2f;
    }

    public void setHeight(float height) {
        this.halfHeight = height / 2f;
    }

    public void setSize(float width, float height) {
        this.halfWidth = width / 2f;
        this.halfHeight = height / 2f;
    }
}
