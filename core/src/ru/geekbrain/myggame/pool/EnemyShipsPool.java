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
    private ExplosionsPool explosionsPool;
//    private Sound explosionSound;

    public EnemyShipsPool(BulletPool bulletPool, Sound bulletSound, Rect worldBounds, Ship ship,
                          ExplosionsPool explosionsPool) {
        this.bulletPool = bulletPool;
//        this.enemyBulletPool = enemyBulletPool;
        this.enemyBulletSound = bulletSound;
        this.ship = ship;
        this.worldBounds = worldBounds;
        this.explosionsPool = explosionsPool;
//        this.explosionSound = explosionSound;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, enemyBulletSound, worldBounds, ship,
                explosionsPool);
    }

}
