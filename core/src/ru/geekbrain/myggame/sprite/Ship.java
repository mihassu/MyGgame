package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.base.MainShip;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.pool.BulletPool;
import ru.geekbrain.myggame.pool.ExplosionsPool;

public class Ship extends MainShip {

    private final Vector2 vUp = new Vector2(0,1);
    private final Vector2 vDown = new Vector2(0,-1);
    private final Vector2 vLeft = new Vector2(-1,0);
    private final Vector2 vRight = new Vector2(1,0);

    private boolean pressedRight;
    private boolean pressedLeft;


    public Ship(TextureAtlas atlas, BulletPool bulletPool, Sound bulletSound, ExplosionsPool explosionsPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletV.set(0, 1f); //скорость пули
        this.bulletHeight = 0.03f; //размер пули
        setHeightProportion(0.2f);
        this.damage = 1;
        this.hp = 10;
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletSound = bulletSound;
        this.reloadInterval = 0.5f; //частота пуль
        this.explosionsPool = explosionsPool;

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        pos.y = worldBounds.getBottom() + getHeight() * 2;
        pos.x = worldBounds.getRight() - worldBounds.getHalfWidth();
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }

        if (getLeft() > worldBounds.getRight()) {setRight(worldBounds.getLeft());}
        if (getRight() < worldBounds.getLeft()) {setLeft(worldBounds.getRight());}
    }

    //чтобы пуля долетала до середины корабля
    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft() ||
                bullet.getLeft() > getRight() ||
                bullet.getBottom() > pos.y ||
                bullet.getTop() < getBottom());
    }

    public boolean keyDown(int keycode) {

        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                shoot();
                break;

            case Input.Keys.RIGHT:
            case Input.Keys.D:
                moveRight();
                pressedRight = true;
                break;

            case Input.Keys.LEFT:
            case Input.Keys.A:
                moveLeft();
                pressedLeft = true;
                break;

//            case Input.Keys.DOWN:
//            case Input.Keys.S:
//                moveDown();
//                break;
        }

        return false;
    }


    public boolean keyUp(int keycode) {

        switch (keycode) {
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stopMove();
                }
                break;

            case Input.Keys.LEFT:
            case Input.Keys.A:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stopMove();
                }
                break;
        }

        return false;
    }



    @Override
    public boolean touchDown(Vector2 touch, int pointer) {

        if (touch.x > worldBounds.pos.x) {
            keyDown(Input.Keys.RIGHT);
        }
        if (touch.x < worldBounds.pos.x) {
            keyDown(Input.Keys.LEFT);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {

        if (touch.x > worldBounds.pos.x) {
            keyUp(Input.Keys.RIGHT);
        }
        if (touch.x < worldBounds.pos.x) {
            keyUp(Input.Keys.LEFT);
        }
        return false;
    }

    private void moveRight() {
        v.set(vRight);
    }

    private void moveLeft() {
        v.set(vLeft);
    } //можно v.set(vRight).rotate(180)

    private void moveUp() {
        v.set(vUp);
    }

    private void moveDown() {
        v.set(vDown);
    }

    private void stopMove() {
        v.setZero();
    }
}
