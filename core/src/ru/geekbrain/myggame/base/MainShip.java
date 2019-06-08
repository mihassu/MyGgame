package ru.geekbrain.myggame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.pool.BulletPool;
import ru.geekbrain.myggame.pool.ExplosionsPool;
import ru.geekbrain.myggame.sprite.Bullet;
import ru.geekbrain.myggame.sprite.Explosion;

public class MainShip extends Sprite {

    protected Vector2 v;
    protected Vector2 v0;
    protected Rect worldBounds;

    protected BulletPool bulletPool;
    protected ExplosionsPool explosionsPool;
//    protected Sound explosionSound;
    protected TextureRegion bulletRegion;
    protected float reloadInterval; //частота пуль
    protected float reloadTimer;
    protected Sound bulletSound;
    protected Vector2 bulletV; //скорость пули
    protected float bulletHeight; //размер пули
    protected int damage; //урон пули
    protected int hp;

    protected float damageAnimateInterval = 0.05f; //интервал анимации попадания
    protected float damageAnimateTimer = damageAnimateInterval;

    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Vector2 getV() {
        return v;
    }

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

        //анимация попадания
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= damageAnimateInterval) {
            frame = 0;
        }
    }

    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, damage);
        bulletSound.play();
    }

    public void damage(int damage) {
        this.hp -= damage;
        frame = 1; //переключаем кадр
        damageAnimateTimer = 0f;

    }

    @Override
    public void destroy() {
        super.destroy();
        explode();
    }

    private void explode() {
        System.out.println(this.getClass().getName());
        if(this.getClass().getName().equals("ru.geekbrain.myggame.sprite.Ship")) {
            this.v.setZero();
        }
        Explosion explosion = explosionsPool.obtain();
        explosion.set(this.getHeight(), this.pos, this.v.cpy());

    }
}
