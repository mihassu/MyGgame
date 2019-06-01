package ru.geekbrain.myggame.pool;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrain.myggame.base.SpritesPool;
import ru.geekbrain.myggame.math.Rect;
import ru.geekbrain.myggame.sprite.EnemyShip;
import ru.geekbrain.myggame.sprite.Ship;

public class EnemyShipsPool extends SpritesPool<EnemyShip> {

    private BulletPool bulletPool;
//    private EnemyBulletPool enemyBulletPool;
    private Sound enemyBulletSound;
    private Ship ship;
    private Rect worldBounds;

    public EnemyShipsPool(BulletPool bulletPool, Sound bulletSound, Rect worldBounds, Ship ship) {
        this.bulletPool = bulletPool;
//        this.enemyBulletPool = enemyBulletPool;
        this.enemyBulletSound = bulletSound;
        this.ship = ship;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, enemyBulletSound, worldBounds, ship);
    }

}
