package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.base.ScaledTouchUpButton;
import ru.geekbrain.myggame.math.Rect;

public class ButtonRight extends ScaledTouchUpButton {

    private Ship ship;
    private boolean pressed;
    private int pointer;
    private static final float PRESS_SCALE = 0.9f;


    public ButtonRight(TextureRegion region, Ship ship) {
        super(region);
        setHeightProportion(0.3f);
        this.ship = ship;
    }

    @Override
    protected void action() {
        ship.keyDown(Input.Keys.RIGHT);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.02f);
        setRight(worldBounds.getRight() - 0.05f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (pressed || !isMe(touch)) {
            return false;
        }
        this.pointer = pointer;
        scale = PRESS_SCALE;
        pressed = true;
        action();
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer // если отпущен не тот палец который нажал кнопку
                || !pressed) {
            return false;
        }

        ship.keyUp(Input.Keys.RIGHT);

        pressed = false;
        scale = 1f;
        return false;
    }


}
