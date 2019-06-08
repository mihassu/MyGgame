package ru.geekbrain.myggame.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrain.myggame.base.MainShip;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.pool.BulletPool;
import ru.geekbrain.myggame.pool.ExplosionsPool;

public class EnemyShip extends MainShip {

    private Ship ship;

    private enum State {DESCENT, FIGHT} //2 состояния: корабль за экраном и на экране
    private State state;
    private final Vector2 descentV = new Vector2(0, -0.5f); //скорость врага за экраном

    public EnemyShip(BulletPool bulletPool, Sound enemyBulletSound, Rect worldBounds, Ship ship,
                     ExplosionsPool explosionsPool) {
        this.bulletPool = bulletPool;
        this.bulletSound = enemyBulletSound;
        this.ship = ship;
        this.worldBounds = worldBounds;
        this.explosionsPool = explosionsPool;
    }


    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
    }

    //Стандартные параметры задаем в конструкторе, а те котторые настраиваются - здесь
    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int bulletDamage,
            float reloadInterval,
            float height,
            int hp
    ){
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0f, bulletVY);
        this.damage = bulletDamage;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
        this.hp = hp;
//        this.v.set(v0);
        this.v.set(descentV); //сначала присваиваем кораблю бОльшую скорость, пока он не выехал
        reloadTimer = reloadInterval; //чтобы корабль сразу стрелял
        state = State.DESCENT; //сначала присваиваем состояние "за экраном"
        bulletSound.setVolume(1, 0f);
    }


    @Override
    public void update(float delta) {
        super.update(delta);

        //проверка что корабль вышел на экран
        if(state == State.DESCENT) {
            if (getTop() < worldBounds.getTop()) {
                state = State.FIGHT;
                v.set(v0); //присваиваем его нормальную скорость
            }
        }
        //стрельба врагов (только когда корабль вышел на экран)
        if (state == State.FIGHT) {
            reloadTimer += delta;
            if (reloadTimer >= reloadInterval) {
                reloadTimer = 0f;
                shoot();
            }
        }

        //корабли врагов отталкиваются от краев экрана
        if(getRight() > worldBounds.getRight() || getLeft() < worldBounds.getLeft()) {
            v.x = -v.x;
//            v.rotate(180);
        }

        //уничтожить корабль врагов когда он вышел за границы экрана
        if(isOutside(worldBounds)) {
            hide();
//            destroy();
        }
    }

    //чтобы пуля долетала до середины корабля
    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft() ||
                bullet.getLeft() > getRight() ||
                bullet.getBottom() > getTop() ||
                bullet.getTop() < pos.y);
    }
}
