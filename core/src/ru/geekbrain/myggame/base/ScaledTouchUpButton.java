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
    public boolean touchDown(Vector2 touch, int pointer) {
        if (pressed || !isMe(touch)) {
            return false;
        }
        this.pointer = pointer;
        scale = PRESS_SCALE;
        pressed = true;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer // если отпущен не тот палец который нажал кнопку
                || !pressed) {
            return false;
        }

        if (isMe(touch)) { //если палец отпущен именно над кнопкой
            action();
        }

        pressed = false;
        scale = 1f;
        return false;
    }

    protected abstract void action();
}
