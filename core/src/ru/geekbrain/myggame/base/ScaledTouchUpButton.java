package ru.geekbrain.myggame.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class ScaledTouchUpButton extends Sprite {

    private static final float PRESS_SCALE = 0.9f;

    private int pointer; //номер пальца который нажал кнопку
    private boolean pressed;

    public ScaledTouchUpButton(TextureRegion region) {
        super(region);
    }

    @Override
    public void setHeightProportion(float height) {
        super.setHeightProportion(height);
    }

    @Override
    public boolean touchDown(Vector2 touch, int button) {
        return super.touchDown(touch, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int button) {
        return super.touchUp(touch, button);
    }

    abstract void action();
}
