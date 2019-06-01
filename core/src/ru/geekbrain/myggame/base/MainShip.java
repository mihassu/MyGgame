package ru.geekbrain.myggame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.pool.BulletPool;
import ru.geekbrain.myggame.sprite.Bullet;

public class MainShip extends Sprite {

    protected Vector2 v;
    protected Vector2 v0;
    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected float reloadInterval; //частота пуль
    protected float reloadTimer;
    protected Sound bulletSound;
    protected Vector2 bulletV; //скорость пули
    protected float bulletHeight; //размер пули
    protected int damage; //урон пули
    protected int hp;


    public MainShip(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.bulletV = new Vector2();

    }

    public MainShip() {
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.bulletV = new Vector2();

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
    }

    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, damage);
        bulletSound.play();
    }
}
